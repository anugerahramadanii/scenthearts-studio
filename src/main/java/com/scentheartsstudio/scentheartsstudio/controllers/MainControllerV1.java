//package com.scentheartsstudio.scentheartsstudio.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class MainControllerV1 {
//    @RequestMapping("")
//    public String loadHomePage() {
//        return "index/index.html";
//    }
//
//    @RequestMapping("/about")
//    public String loadAboutUs() {
//        return "about/about-us.html";
//    }
//
//    @RequestMapping("/login")
//    public String loadLogin() {
//        return "login/login.html";
//    }
//
//    @RequestMapping("/register")
//    public String loadRegister() {
//        return "login/register.html";
//    }
////
////     @RequestMapping("/categories/allproducts")
////     public String loadAllProducts() {
////     return "categories/sidebar-all-products.html";
////     }
//
//    @RequestMapping("/categories/allproducts")
//    public String loadAllProducts(Model model) {
//        String templatePath = "all-products.html";
//        model.addAttribute("contentCategories", templatePath);
//        return "categories/sidebar-all-products.html";
//    }
//
//    @RequestMapping("/categories/rings")
//    public String loadAllRings(Model model) {
//        String templatePath = "rings.html";
//        model.addAttribute("contentCategories", templatePath);
//        return "categories/sidebar-all-products.html";
//    }
//}
