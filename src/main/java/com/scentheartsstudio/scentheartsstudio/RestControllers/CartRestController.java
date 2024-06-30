package com.scentheartsstudio.scentheartsstudio.RestControllers;

import com.scentheartsstudio.scentheartsstudio.DTO.InterCartDTO;
import com.scentheartsstudio.scentheartsstudio.DTO.PostCartDTO;
import com.scentheartsstudio.scentheartsstudio.DTO.PostProductDTO;
import com.scentheartsstudio.scentheartsstudio.Services.CartService;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import com.scentheartsstudio.scentheartsstudio.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CartRestController {

	@Autowired
	private CartService cs;

	@GetMapping("cart")
	public Resp<List<InterCartDTO>> getAllCart(@RequestParam("userId") Long user_id){
		Resp<List<InterCartDTO>> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");

		List<InterCartDTO> data = cs.getAllCartByUserId(user_id);
		response.setData(data);

		return response;
	}

	@PostMapping("cart/addProductToCart")
	public Resp<String> addProductToCart(@RequestBody PostCartDTO postCartDTO) throws CustomException {
		Resp<String> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");
		try {
			cs.addProductToCart(postCartDTO);
		} catch (CustomException e){
			response.setCode(e.getCode());
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@PatchMapping("cart/updateProductQuantity")
	public Resp<String> updateProductQuantity(@RequestBody PostCartDTO postCartDTO){
		Resp<String> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");
		try {
			cs.updateProductQuantity(postCartDTO);
		}catch (CustomException e) {
			response.setCode(e.getCode());
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@DeleteMapping("cart/delete")
	public Resp<String> deleteProduct(@RequestBody PostCartDTO postCartDTO) throws CustomException {
		Resp<String> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");
		try{
			cs.deleteProductInCart(postCartDTO);
		} catch (CustomException e){
			response.setCode(e.getCode());
			response.setMessage(e.getMessage());
		}
		return response;
	}
}
