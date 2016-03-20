package ch.keepcalm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by marcelwidmer on 20/03/16.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    String index(){
        return "index";
    }


}
