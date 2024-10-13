package com.scentheartsstudio.scentheartsstudio.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

import com.scentheartsstudio.scentheartsstudio.dto.PostCategoryDTO;
import com.scentheartsstudio.scentheartsstudio.entities.CategoryEntity;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import com.scentheartsstudio.scentheartsstudio.utils.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
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

	// get all categories with pagination, sorting asc, and search
	public Paging<List<InterCategoryDTO>> getAllCategories(String keyword, Integer page, String sortBy, String sortOrder) {
		Integer limit = 5;
		Integer offset = (page - 1) * limit;
		Integer totalData = cr.getTotalData();
		Integer totalPage = (int) Math.ceil((double) totalData / limit);

		List<InterCategoryDTO> dataList = cr.searchPaginateCategories(keyword, offset, limit);
		if (sortBy.equalsIgnoreCase("NAME") && sortOrder.equalsIgnoreCase("ASC")) {
			dataList = cr.searchPaginateCategoriesAsc(keyword, offset, limit);
		} else if (sortBy.equalsIgnoreCase("NAME") && sortOrder.equalsIgnoreCase("DESC")) {
			dataList = cr.searchPaginateCategoriesDesc(keyword, offset, limit);
		}

		Paging<List<InterCategoryDTO>> paging = new Paging<>();
		paging.setPage(page);
		paging.setTotal_data(totalData);
		paging.setTotal_page(totalPage);
		paging.setList(dataList);

		return paging;
	}


	public void insertCategory(PostCategoryDTO postCategoryDTO) throws CustomException {
		Boolean isNameExists = cr.isNameExists(postCategoryDTO.getName());
		if (isNameExists) {
			throw new CustomException(452, "Category Name " + postCategoryDTO.getName() + " Already Exists");
		}

		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setName(postCategoryDTO.getName());
		categoryEntity.setUrl(postCategoryDTO.getUrl());
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
		if (isNameExistUpdate) {
			throw new CustomException(453, "Category Name " + postCategoryDTO.getName() + "  Already Exists");
		}

		categoryEntity.setId(postCategoryDTO.getId());
		categoryEntity.setName(postCategoryDTO.getName());
		categoryEntity.setUrl(postCategoryDTO.getUrl());
		categoryEntity.setActive(postCategoryDTO.getActive());
		categoryEntity.setModified_by(1L);
		categoryEntity.setModified_on(new Date());

		cr.save(categoryEntity);
	}

	public void deleteCategory(PostCategoryDTO postCategoryDTO) throws IOException {
		CategoryEntity categoryEntity = cr.getReferenceById(postCategoryDTO.getId());

		String imagePath = categoryEntity.getImage_path();
		categoryEntity.setIs_delete(true);
		categoryEntity.setDeleted_by(1L);
		categoryEntity.setDeleted_on(new Date());

		cr.delete(categoryEntity);

		if (imagePath != null) {
			String basePath = new FileSystemResource("").getFile().getAbsolutePath();
			String fullImagePath = basePath + File.separator + "uploads" + File.separator + "categories" + File.separator + new File(imagePath).getName();
			Files.delete(Path.of(fullImagePath));
		}
	}
}

