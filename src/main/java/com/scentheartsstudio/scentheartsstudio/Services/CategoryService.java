package com.scentheartsstudio.scentheartsstudio.Services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.scentheartsstudio.scentheartsstudio.DTO.PostCategoryDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.CategoryEntity;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import com.scentheartsstudio.scentheartsstudio.DTO.InterCategoryDTO;
import com.scentheartsstudio.scentheartsstudio.Repositories.CategoryRepository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class CategoryService {

    private static final List<String> ALLOWED_MIME_TYPES = Arrays.asList("image/jpg", "image/jpeg", "image/png");
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 2;

    @Autowired
    private CategoryRepository cr;

    public List<InterCategoryDTO> getAllCategories() {
        return cr.getAllCategories();
    }

    public void insertCategory(PostCategoryDTO postCategoryDTO, MultipartFile file) throws CustomException, IOException {
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

        Long categoryId = categoryEntity.getId();

        String image = uploadImageCategory(categoryId, file);
        categoryEntity.setImage_path(image);

        cr.save(categoryEntity);
    }

    public void updateCategory(PostCategoryDTO postCategoryDTO, MultipartFile file) throws CustomException, IOException {
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

        String image = uploadImageCategory(postCategoryDTO.getId(), file);
        categoryEntity.setImage_path(image);

//        if (file != null && !file.isEmpty()) {
//            String image = uploadImageCategory(postCategoryDTO.getId(), file);
//            categoryEntity.setImage_path(image);
//        }

        cr.save(categoryEntity);
    }

    public void deleteCategory(PostCategoryDTO postCategoryDTO) {
        CategoryEntity categoryEntity= cr.getReferenceById(postCategoryDTO.getId());
        categoryEntity.setIs_delete(true);
        categoryEntity.setDeleted_by(1L);
        categoryEntity.setDeleted_on(new Date());
        cr.delete(categoryEntity);
    }

    public String uploadImageCategory(Long categoryId,MultipartFile file) throws CustomException, IOException {
        //MIME Type (Multipurpose Internet Mail Extensions)
        String mimeType = file.getContentType();

        if (!ALLOWED_MIME_TYPES.contains(mimeType.toLowerCase())){
            throw new CustomException(415, "Only JPG, JPEG, and PNG files are allowed");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new CustomException(413, "File size must be less than 2MB");
        }

        String basePath = new FileSystemResource("").getFile().getAbsolutePath();
        String uploadPaths = basePath + File.separator + "uploads" + File.separator + "categories" + File.separator;

        // Ensure the upload directory exists
        File uploadDir = new File(uploadPaths);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Generate file name and path
        String fileName = "ImageCategory_" + categoryId + " .jpg".trim();
        Path newPath = Path.of(uploadPaths + fileName);

        // save file to destination path
        Files.copy(file.getInputStream(), newPath, StandardCopyOption.REPLACE_EXISTING);

        //construct the result URL
        String resultUpload = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/images/").path(fileName).toUriString();

        return resultUpload;
    }

}
