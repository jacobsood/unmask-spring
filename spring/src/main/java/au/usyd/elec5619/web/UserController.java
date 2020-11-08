package au.usyd.elec5619.web;

import au.usyd.elec5619.domain.Article;

import au.usyd.elec5619.domain.User;


import au.usyd.elec5619.service.Idatabase.ArticleManager;
import au.usyd.elec5619.service.Idatabase.FavouriteManager;
import au.usyd.elec5619.service.Idatabase.UserManager;
import au.usyd.elec5619.utils.DigestUtils;
import au.usyd.elec5619.utils.SimpleCookieHandler;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

import com.alibaba.fastjson.JSON;

@Controller
public class UserController {
    @Resource(name = "FavouriteManager")
    private FavouriteManager favouriteManager;
    @Resource(name = "UserManager")
    private UserManager userManager;
    @Resource(name = "ArticleManager")
    private ArticleManager articleManager;

    @RequestMapping(value="/likeArticle",method = RequestMethod.POST)
    @ResponseBody
    protected String likeHandler(String articleID, String username, HttpServletRequest request, HttpServletResponse response) {
        Article article = articleManager.getArticleById(Long.parseLong(articleID));
        if (favouriteManager.addFavourite(username,article)){
            return "true";
        }else{
            return "false";
        }
    }

    @RequestMapping(value="/checkLikeStatus",method = RequestMethod.POST)
    @ResponseBody
    protected String likeStatusHandler(String articleID, String username, HttpServletRequest request, HttpServletResponse response) throws SQLIntegrityConstraintViolationException {
        Article article = articleManager.getArticleById(Long.parseLong(articleID));
        if (favouriteManager.checkLikeStatus(username,article)){
            return "true";
        }else{
            return "false";
        }
    }


    @RequestMapping(value="/likelist",method = RequestMethod.POST)
    @ResponseBody
    protected String likeListHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        Cookie username = SimpleCookieHandler.getCookieByName(cookies, "username");
        Set<Article> articleList = favouriteManager.getFavouriteArticlesByUsername(username.getValue(),5);
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
                Article.class,
                "id","title","source","text","audioUrl"
                // only return these column
        );

        String json = JSON.toJSONString(articleList,filter);
        try{
            return json;
        }catch (Exception e){
            return "false";
        }
    }

    @RequestMapping(value="/detailedLikeList",method = RequestMethod.POST)
    @ResponseBody
    protected String DetailedLikeListHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        Cookie username = SimpleCookieHandler.getCookieByName(cookies, "username");
        Set<Article> articleList = favouriteManager.getFavouriteArticlesByUsername(username.getValue());
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
                Article.class,
                "id","title","source","text","audioUrl"
                // only return these column
        );

        String json = JSON.toJSONString(articleList,filter);
        try{
            return json;
        }catch (Exception e){
            return "false";
        }
    }


    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String signupHandler(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "/WEB-INF/view/jsp/signup.jsp";

    }
    @RequestMapping(value = "/signout",method = RequestMethod.GET)
    public String signoutHandler(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        Cookie statusCookie = SimpleCookieHandler.getCookieByName(cookies,"loginStatus");
        if (statusCookie!=null){

            Cookie usernameCookie = SimpleCookieHandler.getCookieByName(cookies, "username");
            Cookie userEmailCookie = SimpleCookieHandler.getCookieByName(cookies,"userEmail");

            statusCookie.setMaxAge(0);
            usernameCookie.setMaxAge(0);
            userEmailCookie.setMaxAge(0);

            response.addCookie(statusCookie);
            response.addCookie(usernameCookie);
            response.addCookie(userEmailCookie);
        }
        // return to the homepage
        return "redirect:http://localhost:8081";
        //return "/vue/Homepage Component/HomepageComponent.html";

    }

    @RequestMapping(value="/checkOldPassword",method = RequestMethod.POST)
    @ResponseBody
    protected String checkOldPasswordHandler(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // encoding with utf-8
        request.setCharacterEncoding("utf-8");
        // get user by username
        User user = this.userManager.getUserByUsername(username);
        // check if user exist
        if (user == null){
            return "false";
        }else{
            String salt = user.getSalt();
            String oldPassword = user.getPassword();
            String encryptedPWD = DigestUtils.sha256Digest(salt+password);
            if (encryptedPWD.equals(oldPassword)){
                return "true";
            }else{
                return "false";
            }
        }
    }

    @RequestMapping(value="/setNewPassword",method = RequestMethod.POST)
    @ResponseBody
    protected String resetPasswordHandler(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // encoding with utf-8
        request.setCharacterEncoding("utf-8");
        // get user by username
        User user = this.userManager.getUserByUsername(username);
        // check if user exist
        if (user == null){
            return "false";
        }else{
            String newSalt = DigestUtils.randomString();
            String encryptedPWD = DigestUtils.sha256Digest(newSalt+password);
            user.setSalt(newSalt);
            user.setPassword(encryptedPWD);
            try{
                this.userManager.setNewPassword(user);
            }catch (Exception e){
                System.out.println(e);
                return "false";
            }
            return "true";

        }
    }
    
    

}
