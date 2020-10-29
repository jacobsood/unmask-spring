package au.usyd.elec5619.web;

import au.usyd.elec5619.domain.Login;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.service.DatabaseRegistrationManager;
import au.usyd.elec5619.service.RegistrationManager;
import au.usyd.elec5619.service.UserManager;
import au.usyd.elec5619.utils.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import au.usyd.elec5619.utils.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Controller
public class RegistrationController {

    @Resource(name = "RegistrationManager")
    private RegistrationManager registrationmanager;

    @Resource(name = "UserManager")
    private UserManager userManager;

    private int saltLength = 16;

    @RequestMapping(value="/registration",method = RequestMethod.POST)
    protected String registrationHandler(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");

        String name=request.getParameter("username");
        String password=request.getParameter("password");
        String email=request.getParameter("yourEmail");
        String salt = randomString(saltLength);
        password = DigestUtils.sha256Digest(salt+password);

        User user = new User(name,salt,password,email);
        try{
            registrationmanager.registration(user);
        }catch (Exception e){
            return "fail";
        }
        return "login";
    }

    @RequestMapping("/checkusername")
    @ResponseBody
    protected String checkUsername(String username, HttpServletResponse response){
        User user = userManager.getUserByUsername(username);
        if(user!=null){
            // if exist return false
            return "false";
        }
            return "true";
        }

    @RequestMapping("/checkEmail")
    @ResponseBody
    protected String checkEmail(String yourEmail, HttpServletResponse response){
        boolean isExist = userManager.checkEmailIsExist(yourEmail);
        if(isExist==true){
            // not exist
            return "false";
        }
        return "true";
    }


    private String randomString(int n) {
        String base = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ~!@#$%^&*()_+=<>/,./;'[]{}|\\\\";
        int length = base.length();

        char[] buff = new char[n];
        Random r = new Random();
        for(int i = 0; i < n; i++) {
            buff[i] = base.charAt(r.nextInt(length));
        }
        return new String(buff);
    }
}
