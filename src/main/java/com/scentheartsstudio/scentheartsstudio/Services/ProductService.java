package com.scentheartsstudio.scentheartsstudio.Services;

import com.scentheartsstudio.scentheartsstudio.DTO.InterProductDTO;
import com.scentheartsstudio.scentheartsstudio.DTO.PostProductDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.ProductEntity;
import com.scentheartsstudio.scentheartsstudio.Repositories.ProductRepository;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class ProductService {

	private static final List<String> ALLOWED_MIME_TYPES = Arrays.asList("image/jpg", "image/jpeg", "image/png");
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 2;

	// Variable to store image sequence number
	private int imageSequenceNumber = 1;

	@Autowired
	private ProductRepository pr;

	public List<InterProductDTO> getAllProduct(){
		return pr.getAllProducts();
	}

	public List<String> insertProduct(PostProductDTO postProductDTO, MultipartFile[] files) throws CustomException, IOException {
		Boolean isNameExists = pr.isNameExist(postProductDTO.getName());
		if (isNameExists){
			throw new CustomException(452, "Product" + postProductDTO.getName() + " already exists");
		}

		ProductEntity productEntity = new ProductEntity();
		productEntity.setName(postProductDTO.getName());
		productEntity.setCategory_id(postProductDTO.getCategory_id());
		productEntity.setDescription(postProductDTO.getDescription());
		productEntity.setStock(postProductDTO.getStock());
		productEntity.setReal_price(postProductDTO.getReal_price());
		productEntity.setDiscount_price(postProductDTO.getDiscount_price());
		productEntity.setActive(postProductDTO.getActive());
		productEntity.setCreated_by(1L);
		productEntity.setCreated_on(new Date());
		pr.save(productEntity);

		Long productId = productEntity.getId();

		List<String> images = uploadImageProduct(productId, files);
		productEntity.setImage_path(String.join(",", images));

		pr.save(productEntity);

		// Reset image sequence number after uploading images for a product
		imageSequenceNumber = 1;

		return images;
	}

	public List<String> updateProduct(PostProductDTO postProductDTO, MultipartFile[] files) throws CustomException, IOException {
		ProductEntity productEntity = pr.getReferenceById(postProductDTO.getId());

		Boolean isNameExistsUpdate = pr.isNameExistUpdate(postProductDTO.getName(), productEntity.getName());
		if (isNameExistsUpdate){
			throw new CustomException(453, "Product Name " + postProductDTO.getName() + " already exists");
		}

		productEntity.setName(postProductDTO.getName());
		productEntity.setCategory_id(postProductDTO.getCategory_id());
		productEntity.setDescription(postProductDTO.getDescription());
		productEntity.setStock(postProductDTO.getStock());
		productEntity.setReal_price(postProductDTO.getReal_price());
		productEntity.setDiscount_price(postProductDTO.getDiscount_price());
		productEntity.setActive(postProductDTO.getActive());
		productEntity.setCreated_by(1L);
		productEntity.setCreated_on(new Date());

		pr.save(productEntity);

		Long productId = productEntity.getId();

		List<String> images = uploadImageProduct(productId, files);
		productEntity.setImage_path(String.join(",", images));

		pr.save(productEntity);

		// Reset image sequence number after uploading images for a product
		imageSequenceNumber = 1;

		return images;
	}

	public void deleteProduct(PostProductDTO postProductDTO){
		ProductEntity productEntity = pr.getReferenceById(postProductDTO.getId());
		productEntity.setIs_delete(true);
		productEntity.setDeleted_by(1L);
		productEntity.setDeleted_on(new Date());

		pr.delete(productEntity);
	}

	public List<String> uploadImageProduct(Long productId, MultipartFile[] files) throws CustomException, IOException {
		List<String> imageUrls = new ArrayList<>();

		for (MultipartFile file : files){
			String mimeType = file.getContentType();

			if (!ALLOWED_MIME_TYPES.contains(mimeType.toLowerCase())) {
				throw new CustomException(415, "Only JPG, JPEG, and PNG files are allowed");
			}

			if (file.getSize() > MAX_FILE_SIZE) {
				throw new CustomException(413, "File size must be less than 2MB");
			}

			String basePath = new FileSystemResource("").getFile().getAbsolutePath();
			String uploadPaths = basePath + File.separator + "uploads" + File.separator + "products" + File.separator;

			File uploadPathDir = new File(uploadPaths);
			if (!uploadPathDir.exists()) {
				uploadPathDir.mkdirs();
			}
			// Generate file name and path
			String fileName = "ImageProduct_" + productId + "_" + imageSequenceNumber + " .jpg".trim();
			Path newPath = Path.of(uploadPaths + fileName);

			// save file to destination path
			Files.copy(file.getInputStream(), newPath, StandardCopyOption.REPLACE_EXISTING);

			//construct the result URL
			String resultUpload = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/images/").path(fileName).toUriString();

			imageUrls.add(resultUpload);

			// Increment image sequence number
			imageSequenceNumber++;
		}
		return imageUrls;
	}


}
