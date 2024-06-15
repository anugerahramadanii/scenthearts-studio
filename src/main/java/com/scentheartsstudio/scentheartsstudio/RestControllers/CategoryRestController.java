package com.scentheartsstudio.scentheartsstudio.RestControllers;

import com.scentheartsstudio.scentheartsstudio.DTO.PostCategoryDTO;
import com.scentheartsstudio.scentheartsstudio.Services.UploadImageCategoryService;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import org.springframework.web.bind.annotation.*;

import com.scentheartsstudio.scentheartsstudio.DTO.InterCategoryDTO;
import com.scentheartsstudio.scentheartsstudio.Services.CategoryService;
import com.scentheartsstudio.scentheartsstudio.utils.Resp;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class CategoryRestController {

    @Autowired
    private CategoryService cs;

    @Autowired
    private UploadImageCategoryService uics;

    @GetMapping("/category")
    public Resp<List<InterCategoryDTO>> getAllCategories() {
        Resp<List<InterCategoryDTO>> response = new Resp<>();
        response.setCode(200);
        response.setMessage("OK");

        List<InterCategoryDTO> data = cs.getAllCategories();
        response.setData(data);
        return response;
    }

//    @PostMapping("category/insert")
//    public Resp<String> insertCategory(@RequestBody PostCategoryDTO postCategoryDTO) {
//        Resp<String> response = new Resp<>();
//        response.setCode(200);
//        response.setMessage("OK");
//        try {
//            cs.insertCategory(postCategoryDTO);
//        } catch (CustomException e) {
//            response.setCode(e.getCode());
//            response.setMessage(e.getMessage());
//        }
//        return response;
//    }

    @PostMapping("category/insert")
    public Resp<String> insertCategory(@ModelAttribute PostCategoryDTO postCategoryDTO,
                                       @RequestParam("file") MultipartFile file) {
        Resp<String> response = new Resp<>();
        response.setCode(200);
        response.setMessage("OK");
        try {
            cs.insertCategory(postCategoryDTO, file);
        } catch (CustomException e) {
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            response.setCode(455);
            response.setMessage("Failed to upload image");
        }
        return response;
    }

    @PutMapping("category/update")
    public Resp<String> updateCategory(@ModelAttribute PostCategoryDTO postCategoryDTO,
                                       @RequestParam("file") MultipartFile file) {
        Resp<String> response = new Resp<>();
        response.setCode(200);
        response.setMessage("OK");
        try {
            cs.updateCategory(postCategoryDTO,file);
        } catch (CustomException e) {
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }catch (IOException e) {
            e.printStackTrace();
            response.setCode(455);
            response.setMessage("Failed to upload image!!");
        }
        return response;
    }

    @DeleteMapping("category/delete")
    public Resp<String> deleteCategory(@RequestBody PostCategoryDTO postCategoryDTO) {
        cs.deleteCategory(postCategoryDTO);
        Resp<String> response = new Resp<>();
        response.setCode(200);
        response.setMessage("OK");
        return response;
    }

//    @PostMapping("category/image")
//    public Resp<String> uploadImage(@RequestParam("categoryId") Long categoryId
//            , @RequestParam("user_id") Long userId
//            ,@RequestParam("file") MultipartFile file) {
//        Resp<String>response = new Resp<>();
//        response.setCode(200);
//        response.setMessage("OK");
//        try {
//            uics.uploadImage(categoryId,userId,file);
//        }catch (CustomException e) {
//            response.setCode(e.getCode());
//            response.setMessage(e.getMessage());
//        } catch (IOException e) {
//            response.setCode(455);
//            response.setMessage("Failed to upload images!");
//        }
//        return response;
//    }
}
