package com.amaydanskiy.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class ApplicationController {

    @RequestMapping(method = RequestMethod.GET)
    public String showDevelopers(RedirectAttributes redirectAttributes){
        return "redirect:/developers?page=0&size=2";
    }
}
