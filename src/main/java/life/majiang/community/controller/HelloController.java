package life.majiang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ Author:刘国虎
 * @ Company:XGLAB
 * @ Time: 2019/8/14 21:39
 */
@Controller
public class HelloController {

    @GetMapping("/")
    public String hello( ){
        return "index";
    }
}
