package com.scentheartsstudio.scentheartsstudio.restcontrollers;

import com.scentheartsstudio.scentheartsstudio.dto.InterProfileDTO;
import com.scentheartsstudio.scentheartsstudio.dto.PostProfileDTO;
import com.scentheartsstudio.scentheartsstudio.services.ProfileService;
import com.scentheartsstudio.scentheartsstudio.services.UploadImageProfileService;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import com.scentheartsstudio.scentheartsstudio.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api")
public class ProfileRestController {

	@Autowired
	private ProfileService ps;

	@Autowired
	private UploadImageProfileService uis;

	@GetMapping("/profile")
	public Resp<InterProfileDTO> getProfileByUserId(@RequestParam("user_id") Long user_id) {
		Resp<InterProfileDTO> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");
		response.setData(ps.getProfileByUserId(user_id));
		return response;
	}

	@PostMapping("/profile/profile_data")
	public Resp<InterProfileDTO> getProfileByUserId(@RequestBody PostProfileDTO postProfileDTO) {
		Resp<InterProfileDTO> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");
		ps.updateProfile(postProfileDTO);

		return response;
	}

	@PostMapping("/profile/change_email")
	public Resp<String> changeEmail(@RequestBody PostProfileDTO postProfileDTO) {
		Resp<String> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");

		try {
			ps.changeEmail(postProfileDTO);
		} catch (CustomException e) {
			response.setCode(e.getCode());
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@PostMapping("/profile/change_password")
	public Resp<String> changePassword(@RequestBody PostProfileDTO postProfileDTO) {
		Resp<String> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");

		try {
			ps.changePassword(postProfileDTO);
		} catch (CustomException e) {
			response.setCode(e.getCode());
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@PostMapping("/profile/profile_picture")
	public Resp<String> uploadProfilePicture(@RequestParam("user_id") Long userId,
	                                         @RequestParam("file") MultipartFile file) {
		Resp<String> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");
		try {
			uis.uploadImageProfile(userId, file);
		}catch (CustomException e){
			response.setCode(e.getCode());
			response.setMessage(e.getMessage());
		}catch (IOException e) {
			e.printStackTrace();
			response.setCode(455);
			response.setMessage("Failed to upload image");
	}
	return response;
}


}
