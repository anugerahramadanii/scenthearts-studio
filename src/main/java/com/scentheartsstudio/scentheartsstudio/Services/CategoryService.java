package com.scentheartsstudio.scentheartsstudio.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scentheartsstudio.scentheartsstudio.DTO.InterCategoryDTO;
import com.scentheartsstudio.scentheartsstudio.Repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository cr;

    public List<InterCategoryDTO> getAllCategories() {
        return cr.getAllCategories();
    }

}
