package com.scentheartsstudio.scentheartsstudio.restcontrollers;

import com.scentheartsstudio.scentheartsstudio.dto.MidtransRequestDTO;
import com.scentheartsstudio.scentheartsstudio.dto.MidtransResponseDTO;
import com.scentheartsstudio.scentheartsstudio.services.MidtransService;
import com.scentheartsstudio.scentheartsstudio.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MidtransRestController {

	@Autowired
	private MidtransService ms;

	@PostMapping("/payment")
	public Resp<MidtransResponseDTO> createTransaction(@RequestBody MidtransRequestDTO midtransRequestDTO){
		Resp<MidtransResponseDTO> response = new Resp<>();
		try{
			response.setCode(200);
			response.setMessage("OK");
			response.setData(ms.createTransaction(midtransRequestDTO));
		} catch (Exception e){
			response.setCode(500);
			response.setMessage(e.getMessage());
		}
		return response;
	}
}
