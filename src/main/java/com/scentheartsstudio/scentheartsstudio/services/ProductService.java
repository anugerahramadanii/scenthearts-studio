package com.scentheartsstudio.scentheartsstudio.services;

import com.scentheartsstudio.scentheartsstudio.dto.InterProductDTO;
import com.scentheartsstudio.scentheartsstudio.dto.PostProductDTO;
import com.scentheartsstudio.scentheartsstudio.entities.ProductEntity;
import com.scentheartsstudio.scentheartsstudio.repositories.ProductRepository;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

	@Autowired
	private ProductRepository pr;

	public List<InterProductDTO> getAllProducts(){
		return pr.getAllProducts();
	}

	public List<InterProductDTO> getAllProductsByCategory(Long category_id){
		return pr.getAllProductsByCategoryId(category_id);
	}

	public void insertProduct(PostProductDTO postProductDTO) throws CustomException{
		Boolean isNameExists = pr.isNameExists(postProductDTO.getProduct_name());
		if (isNameExists){
			throw new CustomException(452, "Product " + postProductDTO.getProduct_name() + " already exists");
		}

		ProductEntity productEntity = new ProductEntity();
		productEntity.setName(postProductDTO.getProduct_name());
		productEntity.setCategory_id(postProductDTO.getCategory_id());
		productEntity.setDescription(postProductDTO.getDescription());
		productEntity.setPrice(postProductDTO.getPrice());
		productEntity.setActive(postProductDTO.getActive());
		productEntity.setCreated_by(postProductDTO.getUser_id());
		productEntity.setCreated_on(new Date());
		pr.save(productEntity);
	}

	public void updateProduct(PostProductDTO postProductDTO) throws CustomException {
		ProductEntity productEntity = pr.getReferenceById(postProductDTO.getId());

		Boolean isNameExistsUpdate = pr.isNameExistsUpdate(postProductDTO.getProduct_name(), productEntity.getName());
		if (isNameExistsUpdate){
			throw new CustomException(452, "Product " + postProductDTO.getProduct_name() + " already exists");
		}

		productEntity.setId(postProductDTO.getId());
		productEntity.setName(postProductDTO.getProduct_name());
		productEntity.setCategory_id(postProductDTO.getCategory_id());
		productEntity.setDescription(postProductDTO.getDescription());
		productEntity.setPrice(postProductDTO.getPrice());
		productEntity.setActive(postProductDTO.getActive());
		productEntity.setModified_by(postProductDTO.getUser_id());
		productEntity.setModified_on(new Date());
		pr.save(productEntity);
	}

	public void deleteProduct(PostProductDTO postProductDTO) throws IOException {
		ProductEntity productEntity = pr.getReferenceById(postProductDTO.getId());
		String imagePath = productEntity.getImage_path();
		productEntity.setIs_delete(true);
		productEntity.setDeleted_by(postProductDTO.getUser_id());
		productEntity.setDeleted_on(new Date());
		pr.delete(productEntity);

		if (imagePath != null && !imagePath.isEmpty()) {
			String basePath = new FileSystemResource("").getFile().getAbsolutePath();
			String[] ImagePaths = imagePath.split(",");
				for (String path : ImagePaths){
					String locImagePath = basePath + File.separator + "uploads" + File.separator + "products" + File.separator
							+ new File(path).getName();
					Files.deleteIfExists(Path.of(locImagePath));
				}

		}
	}
}
