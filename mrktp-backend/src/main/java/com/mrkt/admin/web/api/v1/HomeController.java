package com.mrkt.admin.web.api.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController{

    @RequestMapping(value = "/")
    public String index()
    {

        return "redirect:swagger-ui.html";
    }
}
