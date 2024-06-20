package com.scentheartsstudio.scentheartsstudio.RestControllers;

import com.scentheartsstudio.scentheartsstudio.DTO.InterProductDTO;
import com.scentheartsstudio.scentheartsstudio.DTO.PostCategoryDTO;
import com.scentheartsstudio.scentheartsstudio.DTO.PostProductDTO;
import com.scentheartsstudio.scentheartsstudio.Services.ProductService;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import com.scentheartsstudio.scentheartsstudio.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

	@PostMapping("product/insert")
	public Resp<List<String>> insertProduct(@ModelAttribute PostProductDTO postProductDTO,
	                                        @RequestParam("files") MultipartFile[] files){
		Resp<List<String>> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");
		try{
		List<String> data = ps.insertProduct(postProductDTO, files);
			response.setData(data);
		}catch (CustomException e){
			response.setCode(e.getCode());
			response.setMessage(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			response.setCode(455);
			response.setMessage("Upload Image Failed!!");
		}
		return response;
	}

	@PutMapping("product/update")
	public Resp<List<String>> updateProduct(@ModelAttribute PostProductDTO postProductDTO,
	                                        @RequestParam("files") MultipartFile[] files){
		Resp<List<String>> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");
		try{
			List<String> data = ps.insertProduct(postProductDTO, files);
			response.setData(data);
		}catch (CustomException e){
			response.setCode(e.getCode());
			response.setMessage(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			response.setCode(455);
			response.setMessage("Upload Image Failed!!");
		}
		return response;
	}

	@DeleteMapping("product/delete")
	public Resp<String> deleteProduct(@RequestBody PostProductDTO postProductDTO){
		ps.deleteProduct(postProductDTO);
		Resp<String> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");

		return response;
	}

}
