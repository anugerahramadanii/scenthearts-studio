package com.scentheartsstudio.scentheartsstudio.restcontrollers;

import com.scentheartsstudio.scentheartsstudio.dto.InterProductSizeDTO;
import com.scentheartsstudio.scentheartsstudio.dto.PostProductSizeDTO;
import com.scentheartsstudio.scentheartsstudio.services.ProductSizeService;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import com.scentheartsstudio.scentheartsstudio.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductSizeRestController {

	@Autowired
	private ProductSizeService pss;

	@GetMapping("/productSize")
	public Resp<List<InterProductSizeDTO>> getAllSizes(){
		Resp<List<InterProductSizeDTO>> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");

		List<InterProductSizeDTO> data = pss.getAllSizes();
		response.setData(data);
		return response;
	}

	@PostMapping("/productSize/insert")
	public Resp<String> insertProductSize(@RequestBody PostProductSizeDTO postProductSizeDTO){
		Resp<String> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");
		try {
			pss.insertProductSize(postProductSizeDTO);
		} catch (CustomException e) {
			response.setCode(e.getCode());
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@PutMapping("/productSize/update")
	public Resp<String> updateProductSize(@RequestBody PostProductSizeDTO postProductSizeDTO){
		Resp<String> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");
		try {
			pss.updateProductSize(postProductSizeDTO);
		} catch (CustomException e) {
			response.setCode(e.getCode());
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@DeleteMapping("/productSize/delete")
	public Resp<String> deleteProductSize(@RequestBody PostProductSizeDTO postProductSizeDTO){
		pss.deleteProductSize(postProductSizeDTO);
		Resp<String> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");
		return response;
	}
}
