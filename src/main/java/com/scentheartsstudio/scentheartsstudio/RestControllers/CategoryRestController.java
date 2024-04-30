package com.scentheartsstudio.scentheartsstudio.RestControllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scentheartsstudio.scentheartsstudio.DTO.InterCategoryDTO;
import com.scentheartsstudio.scentheartsstudio.Services.CategoryService;
import com.scentheartsstudio.scentheartsstudio.utils.Resp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class CategoryRestController {

    @Autowired
    private CategoryService cs;

    @GetMapping("/category")
    public Resp<List<InterCategoryDTO>> getAllCategories() {
        Resp<List<InterCategoryDTO>> response = new Resp<>();
        response.setCode(200);
        response.setMessage("OK");

        List<InterCategoryDTO> data = cs.getAllCategories();
        response.setData(data);
        return response;
    }

}
