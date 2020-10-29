package au.usyd.elec5619.web;

import au.usyd.elec5619.domain.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequestMapping
@Controller
public class HelloController {
    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model,HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session=request.getSession();
        Login login=(Login) session.getAttribute("loginInfo");
        if(login==null){
            return "/login/signup";
            //response.sendRedirect("/vue/login/index.jsp");
            //return "redirect:/vue/login/index.jsp";
            //return "success";
        }else{
            //return "index";
            return "redirect:/vue/main.html";
        }

    }
}

