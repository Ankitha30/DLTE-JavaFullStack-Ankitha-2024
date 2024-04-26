package com.thymeleaf.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String dashboard( ){
        return "dashboard";
    }

    @GetMapping("/view")
    public String viewAll(){

        return "viewall";
    }

    @GetMapping("/block")
    public String blockCard(){
        return "blockcard";
    }

}
