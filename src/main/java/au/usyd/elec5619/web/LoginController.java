package au.usyd.elec5619.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;

import au.usyd.elec5619.service.Idatabase.UserManager;
import au.usyd.elec5619.utils.DigestUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import au.usyd.elec5619.domain.Login;
import au.usyd.elec5619.domain.User;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

	@Autowired
	private UserManager userManager;

	@RequestMapping(value="/login",method = RequestMethod.POST)
	@ResponseBody
	protected void loginHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		User user = this.userManager.getUserByUsername(username);
		if (user == null){
			response.getWriter().print("register");
		}
		// the password in the database is encrypted by salt
		String salt = user.getSalt();
		String encryptedPWD = DigestUtils.sha256Digest(salt+password);
		// checkinfo

		if( user.getUsername().equals(username) && user.getPassword().equals(encryptedPWD)){
			// if info is correct
			Login loginInfo = new Login(username,encryptedPWD);
			request.getSession().setAttribute("loginInfo", loginInfo);
			response.getWriter().print("login success");
		}
		else{
			response.getWriter().print("login fail");
		}
	}



}
