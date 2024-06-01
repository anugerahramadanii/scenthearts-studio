package com.scentheartsstudio.scentheartsstudio.RestControllers;

import com.scentheartsstudio.scentheartsstudio.DTO.PostCategoryDTO;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import org.springframework.web.bind.annotation.*;

import com.scentheartsstudio.scentheartsstudio.DTO.InterCategoryDTO;
import com.scentheartsstudio.scentheartsstudio.Services.CategoryService;
import com.scentheartsstudio.scentheartsstudio.utils.Resp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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

    @PostMapping("category/insert")
    public Resp<String> insertCategory(@RequestBody PostCategoryDTO postCategoryDTO) {
        try {
            cs.insertCategory(postCategoryDTO);
            Resp<String> response = new Resp<>();
            response.setCode(200);
            response.setMessage("OK");
            return response;
        } catch (CustomException e) {
            Resp<String> response = new Resp<>();
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
            return response;
        }
    }

    @PutMapping("category/update")
    public Resp<String> updateCategory(@RequestBody PostCategoryDTO postCategoryDTO) {
        try {
            cs.updateCategory(postCategoryDTO);
            Resp<String> response = new Resp<>();
            response.setCode(200);
            response.setMessage("OK");
            return response;
        } catch (CustomException e) {
            Resp<String> response = new Resp<>();
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
            return response;
        }
    }

    @DeleteMapping("category/delete")
    public Resp<String> deleteCategory(@RequestBody PostCategoryDTO postCategoryDTO) {
        cs.deleteCategory(postCategoryDTO);
        Resp<String> response = new Resp<>();
        response.setCode(200);
        response.setMessage("OK");
        return response;
    }

}
