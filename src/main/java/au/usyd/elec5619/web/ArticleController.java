package au.usyd.elec5619.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;    
import org.springframework.web.bind.annotation.PathVariable;    
import org.springframework.beans.factory.annotation.Autowired;

import au.usyd.elec5619.service.DatabaseArticleManager;
import java.util.List;

@Controller
public class ArticleController {
    // Inject DAO from the XML file
    // @Autowired
    // DatabaseArticleManager articleManager;
}