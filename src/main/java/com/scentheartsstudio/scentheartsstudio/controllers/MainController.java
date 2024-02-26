package com.scentheartsstudio.scentheartsstudio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("")
    public String loadHomePage() {
        return "index/index.html";
    }

    @RequestMapping("/about")
    public String loadAboutUs() {
        return "about/about-us.html";
    }

    @RequestMapping("/login")
    public String loadLogin() {
        return "login/login.html";
    }

    @RequestMapping("/register")
    public String loadRegister() {
        return "login/register.html";
    }

    @RequestMapping("/categories/rings")
    public String loadRings() {
        return "categories/ring.html";
    }
}
