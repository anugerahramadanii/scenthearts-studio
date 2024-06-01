package com.scentheartsstudio.scentheartsstudio.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImageConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // file location
        String path = new FileSystemResource("").getFile().getAbsolutePath();
        path += "\\uploads\\";

        // Tambah fungsi jika membuka localhost:8080/images/...
        // nanti membuka file dari folder upload
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///" + path);

        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
