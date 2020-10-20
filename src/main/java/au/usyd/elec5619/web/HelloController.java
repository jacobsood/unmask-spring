package au.usyd.elec5619.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jacob on 20/10/20
 */

@RequestMapping
@Controller
public class HelloController {
    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        // "message" is an attribute in "hello.jsp"
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "hello";
    }
}
