package com.scentheartsstudio.scentheartsstudio.Services;

import java.util.Date;
import java.util.List;

import com.scentheartsstudio.scentheartsstudio.DTO.PostCategoryDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.CategoryEntity;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
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

    public void insertCategory(PostCategoryDTO postCategoryDTO) throws CustomException {
        Boolean isNameExists = cr.isNameExists(postCategoryDTO.getName());
        if (isNameExists) {
            throw new CustomException(453, "Category Name Already Exists");
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(postCategoryDTO.getName());
        categoryEntity.setActive(postCategoryDTO.getActive());
        categoryEntity.setCreated_by(1L);
        categoryEntity.setCreated_on(new Date());
        cr.save(categoryEntity);
    }

    public void updateCategory(PostCategoryDTO postCategoryDTO) throws CustomException {
        CategoryEntity categoryEntity = cr.getReferenceById(postCategoryDTO.getId());

        Boolean isNameExistUpdate = cr.isNameExistsUpdate(postCategoryDTO.getName(), categoryEntity.getName());
        if(isNameExistUpdate){
            throw new CustomException(453, "Name " + postCategoryDTO.getName() + " Category Name Already Exists");
        }

        categoryEntity.setId(postCategoryDTO.getId());
        categoryEntity.setName(postCategoryDTO.getName());
        categoryEntity.setActive(postCategoryDTO.getActive());
        categoryEntity.setModified_by(1L);
        categoryEntity.setModified_on(new Date());

        cr.save(categoryEntity);
    }

    public void deleteCategory(PostCategoryDTO postCategoryDTO) {
        CategoryEntity categoryEntity= cr.getReferenceById(postCategoryDTO.getId());

        categoryEntity.setIs_delete(true);
        categoryEntity.setDeleted_by(1L);
        categoryEntity.setDeleted_on(new Date());
        cr.delete(categoryEntity);
    }

}
