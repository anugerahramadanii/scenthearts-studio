package com.scentheartsstudio.scentheartsstudio.Services;

import com.scentheartsstudio.scentheartsstudio.DTO.InterProductDTO;
import com.scentheartsstudio.scentheartsstudio.DTO.PostProductDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.ProductEntity;
import com.scentheartsstudio.scentheartsstudio.Entities.ProductSizeEntity;
import com.scentheartsstudio.scentheartsstudio.Repositories.ProductRepository;
import com.scentheartsstudio.scentheartsstudio.Repositories.ProductSizeRepository;
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

	public void insertProduct(PostProductDTO postProductDTO) throws CustomException{
		Boolean isNameExists = pr.isNameExists(postProductDTO.getProduct_name(), postProductDTO.getProduct_size_name());
		if (isNameExists){
			throw new CustomException(451, "Product " + postProductDTO.getProduct_name() + " with size " +
					postProductDTO.getProduct_size_name() + " already exists");
		}

		Boolean isProductSizeIdExists = psr.isProductSizeIdExists(postProductDTO.getProduct_size_id());
		if (!isProductSizeIdExists){
			throw new CustomException(452, "Product Size Id " + postProductDTO.getProduct_size_id() + " not found!!");
		}

		ProductEntity productEntity = new ProductEntity();
		productEntity.setName(postProductDTO.getProduct_name());
		productEntity.setCategory_id(postProductDTO.getCategory_id());
		productEntity.setProduct_size_id(postProductDTO.getProduct_size_id());
		productEntity.setQuantity(postProductDTO.getQuantity());
		productEntity.setDescription(postProductDTO.getDescription());
		productEntity.setReal_price(postProductDTO.getReal_price());
		productEntity.setDiscount_rate(postProductDTO.getDiscount_rate());
		productEntity.setDiscount_price(calculateDiscountPrice(postProductDTO.getReal_price(), postProductDTO.getDiscount_rate()));
		productEntity.setActive(postProductDTO.getActive());
		productEntity.setCreated_by(1L);
		productEntity.setCreated_on(new Date());
		pr.save(productEntity);
	}

	public void updateProduct(PostProductDTO postProductDTO) throws CustomException {
		ProductEntity productEntity = pr.getReferenceById(postProductDTO.getId());
		ProductSizeEntity productSizeEntity = psr.getReferenceById(postProductDTO.getProduct_size_id());
		Boolean isNameExistsUpdate = pr.isNameExistsUpdate(postProductDTO.getProduct_name(), productEntity.getName(),
				postProductDTO.getProduct_size_name(), productSizeEntity.getName());
		if (isNameExistsUpdate){
			throw new CustomException(453, "Product " + postProductDTO.getProduct_name() + " with size " +
					postProductDTO.getProduct_size_name() + " already exists");
		}

		Boolean isProductSizeIdExists = psr.isProductSizeIdExists(postProductDTO.getProduct_size_id());
		if (!isProductSizeIdExists){
			throw new CustomException(454, "Product Size Id " + postProductDTO.getProduct_size_id() + " not found!!");
		}

		productEntity.setName(postProductDTO.getProduct_name());
		productEntity.setCategory_id(postProductDTO.getCategory_id());
		productEntity.setProduct_size_id(postProductDTO.getProduct_size_id());
		productEntity.setQuantity(postProductDTO.getQuantity());
		productEntity.setDescription(postProductDTO.getDescription());
		productEntity.setReal_price(postProductDTO.getReal_price());
		productEntity.setDiscount_price(calculateDiscountPrice(postProductDTO.getReal_price(), postProductDTO.getDiscount_rate()));
		productEntity.setActive(postProductDTO.getActive());
		productEntity.setCreated_by(1L);
		productEntity.setCreated_on(new Date());
		pr.save(productEntity);
	}

	public void deleteProduct(PostProductDTO postProductDTO) throws IOException {
		ProductEntity productEntity = pr.getReferenceById(postProductDTO.getId());
		String imagePath = productEntity.getImage_path();
		productEntity.setIs_delete(true);
		productEntity.setDeleted_by(1L);
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

	public Double calculateDiscountPrice(Double realPrice, Double discountRate) {
		if (realPrice != null && discountRate != null) {
			return realPrice * (1 - (discountRate / 100));
		} else {
			return realPrice;
		}
	}

}
