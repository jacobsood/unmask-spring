package au.usyd.elec5619.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;

import au.usyd.elec5619.service.UserManager;
import au.usyd.elec5619.utils.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import au.usyd.elec5619.domain.Login;
import au.usyd.elec5619.domain.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

	@Resource(name = "UserManager")
	private UserManager userManager;

	@RequestMapping(value="/Login",method = RequestMethod.POST)
	protected String loginHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		PrintWriter out=response.getWriter();

		User user = this.userManager.getUserByUsername(username);
		if (user == null){
			return "/login/signup";
		}
		// the password in the database is encrypted by salt
		String salt = user.getSalt();
		String encryptedPWD = DigestUtils.sha256Digest(salt+password);
		// checkinfo

		if( user.getUsername().equals(username) && user.getPassword().equals(encryptedPWD)){
			// if info is correct
			Login loginInfo = new Login(username,encryptedPWD);
			request.getSession().setAttribute("loginInfo", loginInfo);
			response.sendRedirect("/vue/main.html");
		}
		return "/login/signup";

	}



}
