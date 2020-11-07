package au.usyd.elec5619.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;


import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.service.Idatabase.ArticleManager;
import au.usyd.elec5619.service.Idatabase.UserManager;
import au.usyd.elec5619.utils.DigestUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import au.usyd.elec5619.domain.User;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

	@Resource(name = "UserManager")
	private UserManager userManager;
	@Resource(name = "ArticleManager")
	private ArticleManager articleManager;

	@RequestMapping(value="/login",method = RequestMethod.POST)
	@ResponseBody
	protected String loginHandler(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// encoding with utf-8
		request.setCharacterEncoding("utf-8");
		JSONObject object = new JSONObject();

		// get user by username
		User user = this.userManager.getUserByUsername(username);
		// check if user exist
		if (user == null){
			object.put("loginStatus","100"); // user not exist: 100
		}else{
			// the password in the database is encrypted by salt
			String salt = user.getSalt();
			String encryptedPWD = DigestUtils.sha256Digest(salt+password);

			// checkinfo
			if( user.getUsername().equals(username) && user.getPassword().equals(encryptedPWD)){
				// if info is correct
				object.put("loginStatus","200"); // login success: 200
				object.put("username", username);
				object.put("userEmail", user.getEmail());
				object.put("userId", user.getId());
				object.put("maxInactiveInterval",1800);//1800s
			}else{
				// password incorrect
				object.put("loginStatus","101"); // wrong password: 101
			}
		}

		return object.toJSONString();
	}



}
