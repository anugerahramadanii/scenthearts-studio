package com.scentheartsstudio.scentheartsstudio.Services;

import com.scentheartsstudio.scentheartsstudio.Entities.CategoryEntity;
import com.scentheartsstudio.scentheartsstudio.Entities.UserEntity;
import com.scentheartsstudio.scentheartsstudio.Repositories.CategoryRepository;
import com.scentheartsstudio.scentheartsstudio.Repositories.UserRepository;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UploadImageCategoryService {

	@Autowired
	private CategoryRepository cr;

	@Autowired
	private UserRepository ur;

	private static final List<String> ALLOWED_MIME_TYPES = Arrays.asList("image/jpg", "image/jpeg", "image/png");
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 2;

	public String uploadImage(Long categoryId, Long userId, MultipartFile file) throws CustomException, IOException {
//		CategoryEntity categoryEntity = cr.getReferenceById(categoryId);
		CategoryEntity categoryEntity = cr.findById(categoryId)
				.orElseThrow(() -> new CustomException(404, "Category with id " + categoryId + " not found"));

		//        MIME Type (Multipurpose Internet Mail Extensions)
		String mimeType = file.getContentType();
		if (file.isEmpty()){
			throw new CustomException(400, "File is empty");
		}

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
		String fileName = "ImageCategory_" + categoryId + ".jpg";
		Path newPath = Path.of(uploadPaths + fileName);

		// save file to destination path
		Files.copy(file.getInputStream(), newPath, StandardCopyOption.REPLACE_EXISTING);

		//construct the result URL
		String resultUpload = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/images/").path(fileName).toUriString();

		// get userId
		UserEntity userEntity = ur.getReferenceById(userId);
		//data category image id
		categoryEntity.setImage_path(resultUpload);
		categoryEntity.setModified_by(userId);
		categoryEntity.setModified_on(new Date());

		cr.save(categoryEntity);
		return resultUpload;
	}

}
