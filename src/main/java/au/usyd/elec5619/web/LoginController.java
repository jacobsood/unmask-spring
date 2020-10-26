package au.usyd.elec5619.web;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;

import au.usyd.elec5619.service.DatabaseUserManager;
import au.usyd.elec5619.service.UserManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import au.usyd.elec5619.domain.Login;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.utils.DigestUtils;
@Controller
public class LoginController {
	private UserManager userManager = new DatabaseUserManager();

	@RequestMapping(value="/Login",method = RequestMethod.POST)
	@ResponseBody
	protected String doGet(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("username");
		String pwd=request.getParameter("password");

		Login login = new Login(name,pwd);
		User user = userManager.getUserByUsername(name);

		// the password in the database is encrypted by salt
		String realPwd = user.getPassword();
		// get salt
		String salt = user.getSalt();
		String encryptedPWD = DigestUtils.digest(salt,pwd);

		if (realPwd.equals(encryptedPWD)){
			return "Yes";
		}else{
			return "No";
		}
	}

}
