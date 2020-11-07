package au.usyd.elec5619.web;

import au.usyd.elec5619.utils.SimpleCookieHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ApplicationController {
    @GetMapping("/") 
    public String home(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        //return "/vue/Homepage Component/HomepageComponent.html";
        //return "/vue/main.html";
        response.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = request.getCookies();
        if (cookies==null){
            //return "redirect:/vue/main.html";
            return "/";
        }else{
            Cookie cookie = SimpleCookieHandler.getCookieByName(cookies,"loginStatus");
            if (cookie!=null && cookie.getValue().equals("200")){
                //return "hello";
                return "/vue/Homepage Component/HomepageComponent.html";
            }else{
                return "/vue/Homepage Component/HomepageComponent.html";
            }
        }
        //return "/WEB-INF/view/jsp/signup.jsp";
    }
}
