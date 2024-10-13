package com.scentheartsstudio.scentheartsstudio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @GetMapping("/scenthearts-studio")
    public String loadHomePage() {
        return "user/index";
    }

    @GetMapping("/register")
    public String loadRegister(HttpServletRequest servlet) {
        Long userId = (Long) servlet.getSession().getAttribute("user_id");
        if (userId != null) {
            return "redirect:/scenthearts-studio";
        } else {
            return "auth/register";
        }
    }

    @GetMapping("/login")
    public String loadLogin(HttpServletRequest servlet) {
        Long userId = (Long) servlet.getSession().getAttribute("user_id");
        if (userId != null) {
            return "redirect:/scenthearts-studio";
        } else{
            return "auth/login";
        }
    }

    @PostMapping("/login/success")
    public String loadLoginSuccess(
        HttpServletRequest servlet, 
        @RequestParam("user_id") Long user_id, 
        @RequestParam("token") String token,
        @RequestParam("role") String role) {
        
        // save data to session (temp data)
        servlet.getSession().setAttribute("user_id", user_id);
        servlet.getSession().setAttribute("token", token);
        servlet.getSession().setAttribute("role", role);

        if (role.equalsIgnoreCase("admin")) {
            return "redirect:/admin-dashboard";
        } else {
            return "redirect:/scenthearts-studio";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest servlet) {
        servlet.getSession().setAttribute("user_id", null);
        servlet.getSession().setAttribute("token", null);
        servlet.getSession().setAttribute("role", null);

        return "redirect:/scenthearts-studio";
    }
    
    

    @GetMapping("/forgot-password")
    public String loadForgotPassword() {
        return "auth/forgot-password";
    }
    
    @GetMapping("/products")
    public String loadProducts() {
        return "user/shop-grid";
    }

    @GetMapping("/product-detail")
    public String loadDetailProduct() {
        return "user/shop-details";
    }
    
    @GetMapping("/checkout")
    public String loadCheckout() {
        return "user/checkout";
    }

    @GetMapping("/shopping-cart")
    public String loadShoppingCart() {
        return "user/shoping-cart";
    }

    @GetMapping("/blog")
    public String loadBlog() {
        return "user/blog";
    }

    @GetMapping("/blog-details")
    public String loadBlogDetail() {
        return "user/blog-details";
    }

    @GetMapping("/contact")
    public String loadContact() {
        return "user/contact";
    }

    // ADMIN
    @GetMapping("/admin-dashboard")
    public String loadAdminHomePage() {
        return "admin/admin-index";
    }

    // @GetMapping("/admin-profile")
    // public String loadAdminProfile(HttpServletRequest request, Model model){
    //     String uri = request.getRequestURI();
    //     model.addAttribute("currentUri", uri);
    //     if (uri.equals("/admin-profile-account")) {
    //         return "admin/admin-profile/admin-profile-account";
    //     } else if (uri.equals("/admin-profile-change-password")) {
    //         return "admin/admin-profile/admin-profile-change-password";
    //     }
    //     return "admin/admin-profile";
    // }

    @GetMapping("/admin-profile")
    public String loadAdminProfile() {
        return "admin/admin-profile";
    }

    @GetMapping("/admin-profile-account")
    public String loadAdminProfileAccount(HttpServletRequest request, Model model){
        model.addAttribute("currentUri", request.getRequestURI());
        return "admin/admin-profile/admin-profile-account";
    }

    @GetMapping("/admin-profile-change-password")
    public String loadAdminProfileChangePassword(HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
        return "admin/admin-profile/admin-profile-change-password";
    }

    @GetMapping("/admin-products")
    public String loadAdminProducts() {
        return "admin/admin-products/index-products.html";
    }
    @GetMapping("/admin-category-products")
    public String loadAdminCategoryProducts() {
        return "admin/admin-category-products/index-category-products.html";
    }
}
