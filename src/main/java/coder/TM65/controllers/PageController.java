package coder.TM65.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping(value = "/")
    public String index(Model model){
        model.addAttribute("title","Restaurant Home");
        return "index";
    }

    @GetMapping(value = "/error")
    public String error(){
        return "tables/all";
    }




}
