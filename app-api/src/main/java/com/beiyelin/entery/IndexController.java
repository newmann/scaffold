package com.beiyelin.entery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xinsheng.hu on 2016/10/2.
 */
@Controller
@RequestMapping("/public")
public class IndexController {

    @RequestMapping("/index")
    public String Index(){
        return "index";
    }

}
