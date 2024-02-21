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

    @RequestMapping("/category")
    public String redirectToCategoryPage() {
        return "redirect:/category"; // Mengarahkan ke halaman kategori
    }

    @RequestMapping("/products")
    public String redirectToProductsPage() {
        return "redirect:/products"; // Mengarahkan ke halaman produk
    }

    @RequestMapping("/contact")
    public String redirectToContactPage() {
        return "redirect:/contact"; // Mengarahkan ke halaman kontak
    }
}
