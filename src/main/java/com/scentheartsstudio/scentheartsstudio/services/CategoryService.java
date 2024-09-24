package com.scentheartsstudio.scentheartsstudio.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.scentheartsstudio.scentheartsstudio.dto.PostCategoryDTO;
import com.scentheartsstudio.scentheartsstudio.entities.CategoryEntity;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scentheartsstudio.scentheartsstudio.dto.InterCategoryDTO;
import com.scentheartsstudio.scentheartsstudio.repositories.CategoryRepository;

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
            throw new CustomException(452, "Category Name " + postCategoryDTO.getName() + " Already Exists");
        }

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(postCategoryDTO.getName());
        categoryEntity.setActive(postCategoryDTO.getActive());
        categoryEntity.setCreated_by(postCategoryDTO.getUser_id());
        categoryEntity.setCreated_on(new Date());

        cr.save(categoryEntity);
//        Long categoryId = categoryEntity.getId();
//
//        String image = uploadImageCategory(categoryId, file);
//        categoryEntity.setImage_path(image);
//        cr.save(categoryEntity);

//        imageSequenceNumber++;
    }

    public void updateCategory(PostCategoryDTO postCategoryDTO) throws CustomException {
        CategoryEntity categoryEntity = cr.getReferenceById(postCategoryDTO.getId());

        Boolean isNameExistUpdate = cr.isNameExistsUpdate(postCategoryDTO.getName(), categoryEntity.getName());
        if(isNameExistUpdate){
            throw new CustomException(453, "Category Name " + postCategoryDTO.getName() + "  Already Exists");
        }

        categoryEntity.setId(postCategoryDTO.getId());
        categoryEntity.setName(postCategoryDTO.getName());
        categoryEntity.setActive(postCategoryDTO.getActive());
        categoryEntity.setModified_by(1L);
        categoryEntity.setModified_on(new Date());

        cr.save(categoryEntity);
    }

    public void deleteCategory(PostCategoryDTO postCategoryDTO) throws IOException {
        CategoryEntity categoryEntity= cr.getReferenceById(postCategoryDTO.getId());

        categoryEntity.setIs_delete(true);
        categoryEntity.setDeleted_by(1L);
        categoryEntity.setDeleted_on(new Date());

        cr.delete(categoryEntity);
    }

//    public void deleteCategory(PostCategoryDTO postCategoryDTO) throws IOException {
//        CategoryEntity categoryEntity= cr.getReferenceById(postCategoryDTO.getId());
//
//        String imagePath = categoryEntity.getImage_path();
//        categoryEntity.setIs_delete(true);
//        categoryEntity.setDeleted_by(1L);
//        categoryEntity.setDeleted_on(new Date());
//
//        cr.delete(categoryEntity);
//
//        if (imagePath != null) {
//            String basePath = new FileSystemResource("").getFile().getAbsolutePath();
//            String fullImagePath = basePath + File.separator + "uploads" + File.separator + "categories" + File.separator
//                        + new File(imagePath).getName();
//            Files.delete(Path.of(fullImagePath));
//        }
//    }
}
