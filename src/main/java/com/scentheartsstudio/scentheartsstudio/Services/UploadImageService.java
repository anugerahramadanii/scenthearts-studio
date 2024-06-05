package com.scentheartsstudio.scentheartsstudio.Services;

import com.scentheartsstudio.scentheartsstudio.Entities.BiodataEntity;
import com.scentheartsstudio.scentheartsstudio.Entities.UserEntity;
import com.scentheartsstudio.scentheartsstudio.Repositories.BiodataRepository;
import com.scentheartsstudio.scentheartsstudio.Repositories.UserRepository;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Service
public class UploadImageService {

	@Autowired
	private BiodataRepository br;

	@Autowired
	private UserRepository ur;

	public String uploadImageProfile(Long userId, MultipartFile file) throws CustomException, IOException  {
		// define limit size image upload
		long maxFileSize = 2 * 1024 * 1024; // 2MB in bytes
		if (file.getSize() > maxFileSize){
			throw new CustomException(453, "File size must be less than 2MB");
		}

		String basePath = new FileSystemResource("").getFile().getAbsolutePath();
		String uploadPaths = basePath + File.separator + "uploads" + File.separator + "profile" + File.separator;

		// check directory file exist or no
//		File dir = new File(uploadPaths);
//		if(!dir.exists()){
//			dir.mkdirs();
//		}

		String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
		if(fileExtension == null || !(fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("png") || fileExtension.equalsIgnoreCase("jpeg"))){
//				return "Images file must be JPG/JPEG or PNG";
			throw new CustomException(421, "Images file must be JPG/JPEG or PNG");
		}

		String fileName = "profile-user-" + userId + " .jpg" ;
		Path newPath = Paths.get(uploadPaths + fileName);

		// save file to destination path
		Files.copy(file.getInputStream(), newPath, StandardCopyOption.REPLACE_EXISTING);

		// construct the result URL
		String resultUpload = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/images/").path(fileName).toUriString();

		UserEntity userEntity = ur.getReferenceById(userId);
		BiodataEntity biodataEntity = br.getReferenceById(userEntity.getBiodata_id());
		biodataEntity.setImage_path(resultUpload);
		biodataEntity.setModified_by(userId);
		biodataEntity.setModified_on(new Date());
		 br.save(biodataEntity);
		return resultUpload;
	}
}
