package au.usyd.elec5619.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/") 
    public String home(ModelMap model) {
        return "/vue/main.html";
    }

    @GetMapping("login") 
    public String login(ModelMap model) {
        return "/WEB-INF/view/hello.jsp";
    }
}
