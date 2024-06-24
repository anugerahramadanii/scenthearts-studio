package com.scentheartsstudio.scentheartsstudio.Services;

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
public class UploadImageProductService {
	private static final List<String> ALLOWED_MIME_TYPES = Arrays.asList("image/jpg", "image/jpeg", "image/png");
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 2;

	@Autowired
	private ProductRepository pr;

	public List<String> uploadImageProduct(Long productId,Long userId, MultipartFile[] files) throws CustomException, IOException {

//		ProductEntity productEntity = pr.getReferenceById(productId);
//		if (productEntity.getId() == null){
//			throw new CustomException(404, "Product ID not found");
//		}
		ProductEntity productEntity = pr.findById(productId)
				.orElseThrow(() -> new CustomException(404, "Product with id " + productId + " not found"));

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

			File uploadDir = new File(uploadPaths);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			// Generate file name and path
			String fileName = "ImageProduct_" + productId + "_" + UUID.randomUUID() + ".jpg";
			Path newPath = Path.of(uploadPaths + fileName);

			// save file to destination path
			Files.copy(file.getInputStream(), newPath, StandardCopyOption.REPLACE_EXISTING);

			//construct the result URL
			String resultUpload = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/images/").path(fileName).toUriString();

			imageUrls.add(resultUpload);
		}

		if(productEntity.getImage_path() != null && !productEntity.getImage_path().isEmpty()){
			imageUrls.addAll(Arrays.asList(productEntity.getImage_path().split(",")));
		}
		productEntity.setImage_path(String.join(",", imageUrls));
		productEntity.setModified_by(userId);
		productEntity.setModified_on(new Date());

		pr.save(productEntity);

		return imageUrls;
	}
}
