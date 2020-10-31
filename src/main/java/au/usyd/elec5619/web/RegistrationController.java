package au.usyd.elec5619.web;

import au.usyd.elec5619.domain.Login;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.service.DatabaseRegistrationManager;
import au.usyd.elec5619.service.RegistrationManager;
import au.usyd.elec5619.service.UserManager;
import au.usyd.elec5619.utils.DigestUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import au.usyd.elec5619.utils.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationManager registrationmanager;
    private int saltLength = 16;

    @RequestMapping(value="/registration",method = RequestMethod.POST)
    @ResponseBody
    protected String registrationHandler(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String name=request.getParameter("Username");
        String password=request.getParameter("Password");
        String repeatPassword=request.getParameter("ConfirmPassword");
        String email=request.getParameter("Email");
        String salt = randomString(saltLength);
        password = DigestUtils.sha256Digest(salt+password);

        User user = new User(name,salt,password,email);
        try{
            registrationmanager.registration(user);
        }catch (Exception e){
            return "fail";
        }
        return "success";
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
