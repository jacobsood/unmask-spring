package au.usyd.elec5619.web;


import au.usyd.elec5619.domain.Article;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.service.Idatabase.ArticleManager;
import au.usyd.elec5619.service.Idatabase.HistoryManager;
import au.usyd.elec5619.service.Idatabase.UserManager;
import au.usyd.elec5619.utils.SimpleCookieHandler;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Controller
public class HistoryController {

	@Resource(name = "HistoryManager")
	private HistoryManager historyManager;
	@Resource(name = "ArticleManager")
	private ArticleManager articleManager;
	@Resource(name = "UserManager")
	private UserManager userManager;

	@RequestMapping(value="/historylist",method = RequestMethod.GET)
	@ResponseBody
	protected String historyHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie[] cookies = request.getCookies();
		Cookie username = SimpleCookieHandler.getCookieByName(cookies, "username");
		Set<Article> articleList = historyManager.getHistoryByUsername(username.getValue(),5);
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
				Article.class,
				"id","title","source","text","audioUrl"
				// only return these column
		);
		String json = JSON.toJSONString(articleList,filter);
		System.out.println(json);
		try{
			return json;
		}catch (Exception e){
			return "false";
		}

	}
	@RequestMapping(value="/addHistory",method = RequestMethod.POST)
	@ResponseBody
	protected void addHistory(long article_id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie[] cookies = request.getCookies();
		Cookie username = SimpleCookieHandler.getCookieByName(cookies, "username");
		User user = userManager.getUserByUsername(username.getValue());
		Article article = articleManager.getArticleById(article_id);
		historyManager.addHistoryByUser(user,article);
	}



	@RequestMapping(value="/detailedHistoryList",method = RequestMethod.POST)
	@ResponseBody
	protected String detailedHistoryHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie[] cookies = request.getCookies();
		Cookie username = SimpleCookieHandler.getCookieByName(cookies, "username");
		Set<Article> articleList = historyManager.getHistoryByUsername(username.getValue());
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
				Article.class,
				"id","title","source","text","audioUrl"
				// only return these column
		);
		String json = JSON.toJSONString(articleList,filter);
		System.out.println(json);
		try{
			return json;
		}catch (Exception e){
			return "false";
		}

	}



}
