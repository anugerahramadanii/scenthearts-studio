package com.scentheartsstudio.scentheartsstudio.Services;

import com.scentheartsstudio.scentheartsstudio.Entities.BiodataEntity;
import com.scentheartsstudio.scentheartsstudio.Entities.UserEntity;
import com.scentheartsstudio.scentheartsstudio.Repositories.BiodataRepository;
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
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UploadImageProfileService {

	private static final List<String> ALLOWED_MIME_TYPES = Arrays.asList("image/jpg", "image/jpeg", "image/png");
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 2;

	@Autowired
	private BiodataRepository br;

	@Autowired
	private UserRepository ur;

	public String uploadImageProfile(Long userId, MultipartFile file) throws IOException, CustomException {

		String mimeType = file.getContentType();

		if (mimeType == null || !ALLOWED_MIME_TYPES.contains(mimeType.toLowerCase())){
			throw new CustomException(415, "Only JPG, JPEG, and PNG files are allowed");
		}
		if (file.getSize() > MAX_FILE_SIZE){
			throw new CustomException(413, "File size must be less than 2MB");
		}

//		String fileExtension = "";
//		if (mimeType.contains("image/jpg")){
//			fileExtension = ".jpg";
//		} else if(mimeType.contains("image/jpeg")){
//			fileExtension = ".jpeg";
//		} else if(mimeType.contains("image/png")) {
//			fileExtension = ".png";
//		}

		String basePath = new FileSystemResource("").getFile().getAbsolutePath();
		String uploadPaths = basePath + File.separator + "uploads" + File.separator + "profile" + File.separator;

		String fileName = "profile-user-" + userId + " .jpg" ;
		Path newPath = Paths.get(uploadPaths + fileName);

		// save file to destination path
		Files.copy(file.getInputStream(), newPath, StandardCopyOption.REPLACE_EXISTING);

		// construct the result URL
		String resultUpload = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/images/").path(fileName).toUriString();

		//update new image based on user_id
		UserEntity userEntity = ur.getReferenceById(userId);
		BiodataEntity biodataEntity = br.getReferenceById(userEntity.getBiodata_id());
		biodataEntity.setImage_path(resultUpload);
		biodataEntity.setModified_by(userId);
		biodataEntity.setModified_on(new Date());
		
		br.save(biodataEntity);
		return resultUpload;
	}
}
