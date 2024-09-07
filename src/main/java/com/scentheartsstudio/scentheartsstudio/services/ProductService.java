package com.scentheartsstudio.scentheartsstudio.services;

import com.scentheartsstudio.scentheartsstudio.dto.InterProductDTO;
import com.scentheartsstudio.scentheartsstudio.dto.PostProductDTO;
import com.scentheartsstudio.scentheartsstudio.dto.ProductSizeDTO;
import com.scentheartsstudio.scentheartsstudio.entities.ProductEntity;
import com.scentheartsstudio.scentheartsstudio.entities.ProductSizeEntity;
import com.scentheartsstudio.scentheartsstudio.repositories.ProductRepository;
import com.scentheartsstudio.scentheartsstudio.repositories.ProductSizeRepository;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class ProductService {

	@Autowired
	private ProductRepository pr;

	@Autowired
	private ProductSizeRepository psr;

	public List<InterProductDTO> getAllProducts(){
		return pr.getAllProducts();
	}

	public List<InterProductDTO> getAllProductsByCategory(Long category_id){
		return pr.getAllProductsByCategoryId(category_id);
	}

	public void insertProduct(PostProductDTO postProductDTO) throws CustomException {
		Boolean isNameProductAndSizeExists = pr.isNameProductAndSizeExists(postProductDTO.getProduct_name(),
				postProductDTO.getSizes().get(0).getName());

		if (isNameProductAndSizeExists){
			throw new CustomException(451, "Product " + postProductDTO.getProduct_name() + " with size " +
					postProductDTO.getSizes().get(0).getName() + " already exists");
		}

		ProductEntity productEntity = new ProductEntity();
		productEntity.setName(postProductDTO.getProduct_name());
		productEntity.setCategory_id(postProductDTO.getCategory_id());
		productEntity.setDescription(postProductDTO.getDescription());
		productEntity.setPrice(postProductDTO.getPrice());
		productEntity.setActive(postProductDTO.getActive());
		productEntity.setCreated_by(1L);
		productEntity.setCreated_on(new Date());
		pr.save(productEntity);

		List<ProductSizeEntity> arrProductSize = new ArrayList<>();
		for (ProductSizeDTO data : postProductDTO.getSizes()){
			ProductSizeEntity productSizeEntity = new ProductSizeEntity();
			productSizeEntity.setName(data.getName());
			productSizeEntity.setQuantity(data.getQuantity());
			productSizeEntity.setActive(data.getActive());
			productSizeEntity.setCreated_by(postProductDTO.getUser_id());
			productSizeEntity.setCreated_on(new Date());
			productSizeEntity.setProduct_id(productEntity.getId());
			arrProductSize.add(productSizeEntity);
 		}
		psr.saveAll(arrProductSize);
	}

	public void updateProduct(PostProductDTO postProductDTO) throws CustomException {
		ProductEntity productEntity = pr.getReferenceById(postProductDTO.getId());
		// check is product name exists
		Boolean isNameProductExistsUpdate = pr.isNameProductExistsUpdate(postProductDTO.getProduct_name(), productEntity.getName());
		if (isNameProductExistsUpdate){
			throw new CustomException(452, "Product " + postProductDTO.getProduct_name() + " already exists");
		}

		productEntity.setName(postProductDTO.getProduct_name());
		productEntity.setCategory_id(postProductDTO.getCategory_id());
		productEntity.setDescription(postProductDTO.getDescription());
		productEntity.setPrice(postProductDTO.getPrice());
		productEntity.setActive(postProductDTO.getActive());
		productEntity.setModified_by(postProductDTO.getUser_id());
		productEntity.setModified_on(new Date());
		pr.save(productEntity);

		// List to collect updated ProductSize entities
		List<ProductSizeEntity> arrProductSize = new ArrayList<>();

		for (ProductSizeDTO data : postProductDTO.getSizes()){
			ProductSizeEntity productSizeEntity = psr.getReferenceById(data.getId());
			// check is product size name exists
			Boolean isNameProductSizeExistsUpdate = psr.isNameProductSizeExistsUpdate(data.getProduct_id(), data.getName(), productSizeEntity.getName());
			if (isNameProductSizeExistsUpdate){
				throw new CustomException(453, "Product Size " + data.getName() + " already exists");
			}
			productSizeEntity.setName(data.getName());
			productSizeEntity.setQuantity(data.getQuantity());
			productSizeEntity.setActive(data.getActive());
			productSizeEntity.setModified_by(postProductDTO.getUser_id());
			productSizeEntity.setModified_on(new Date());
			arrProductSize.add(productSizeEntity);
		}
		psr.saveAll(arrProductSize);
	}

	public void deleteProduct(PostProductDTO postProductDTO) throws IOException {
		ProductEntity productEntity = pr.getReferenceById(postProductDTO.getId());
		String imagePath = productEntity.getImage_path();
		productEntity.setIs_delete(true);
		productEntity.setDeleted_by(postProductDTO.getUser_id());
		productEntity.setDeleted_on(new Date());
		pr.delete(productEntity);

		List<ProductSizeEntity> arrProductSize = psr.getAllSizesByProductId(productEntity.getId());

		for (ProductSizeEntity productSize : arrProductSize){
			productSize.setIs_delete(true);
			productSize.setDeleted_by(postProductDTO.getUser_id());
			productSize.setDeleted_on(new Date());
		}
		psr.saveAll(arrProductSize);

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
