package com.scentheartsstudio.scentheartsstudio.Services;

import com.scentheartsstudio.scentheartsstudio.Entities.BiodataEntity;
import com.scentheartsstudio.scentheartsstudio.Entities.UserEntity;
import com.scentheartsstudio.scentheartsstudio.Repositories.BiodataRepository;
import com.scentheartsstudio.scentheartsstudio.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	public String uploadImageProfile(Long userId, MultipartFile file) throws IOException {
		String path =new FileSystemResource("").getFile().getAbsolutePath();
		path += "\\uploads\\";

		String fileName = "profile-user-" + userId + ".jpg";
		Path newPath = Paths.get(path + fileName);

		Files.copy(file.getInputStream(), newPath, StandardCopyOption.REPLACE_EXISTING);

		String resultUpload = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/images").path(fileName).toUriString();

		UserEntity userEntity = ur.getReferenceById(userId);

		BiodataEntity biodataEntity = br.getReferenceById(userEntity.getBiodata_id());
		biodataEntity.setImage_path(resultUpload);
		biodataEntity.setModified_by(userId);
		biodataEntity.setModified_on(new Date());
		 br.save(biodataEntity);
		return resultUpload;
	}
}
