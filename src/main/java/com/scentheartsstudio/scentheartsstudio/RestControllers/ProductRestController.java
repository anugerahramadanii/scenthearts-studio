package com.scentheartsstudio.scentheartsstudio.RestControllers;

import com.scentheartsstudio.scentheartsstudio.DTO.InterProductDTO;
import com.scentheartsstudio.scentheartsstudio.Services.ProductService;
import com.scentheartsstudio.scentheartsstudio.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {

	@Autowired
	private ProductService ps;

	@GetMapping("product")
	public Resp<List<InterProductDTO>> getAllProducts(){
		Resp<List<InterProductDTO>> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");

		List<InterProductDTO> data = ps.getAllProduct();
		response.setData(data);
		return response;
	}
}
