package com.scentheartsstudio.scentheartsstudio.RestControllers;

import com.scentheartsstudio.scentheartsstudio.DTO.InterProfileDTO;
import com.scentheartsstudio.scentheartsstudio.DTO.PostProfileDTO;
import com.scentheartsstudio.scentheartsstudio.Services.ProfileService;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import com.scentheartsstudio.scentheartsstudio.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class ProfileRestController {

	@Autowired
	private ProfileService ps;

	@GetMapping("/profile")
	public Resp<InterProfileDTO> getProfileByUserId(@RequestParam("user_id") Long user_id){
		Resp<InterProfileDTO> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");
		response.setData(ps.getProfileByUserId(user_id));
		return response;
	}

	@PostMapping("/profile/profile_data")
	public Resp<InterProfileDTO> getProfileByUserId(@RequestBody PostProfileDTO postProfileDTO){
		Resp<InterProfileDTO>response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");
		ps.updateProfile(postProfileDTO);

		return response;
	}

	@PostMapping("profile/change_email")
	public Resp<String> changeEmail(@RequestBody PostProfileDTO postProfileDTO){
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
}
