package au.usyd.elec5619.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


@Controller
public class HelloController {

    /*@RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String printHello(ModelMap model,HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "/WEB-INF/view/jsp/signup.jsp";

    }*/
    /*
    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model,HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*response.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            return "redirect:/vue/main.html";
        }else{
            Cookie cookie = SimpleCookieHandler.getCookieByName(cookies,"loginStatus");
            if (cookie!=null && cookie.getValue()=="200"){
                return "hello";
            }else{
                return "redirect:/vue/main.html";
            }
        }
        return "signup";
    }*/
/*
    @RequestMapping(value="/signup",method = RequestMethod.GET)
    protected String toRegistration(HttpServletRequest request) throws UnsupportedEncodingException {
        return "/login/signup";
    }*/
}

