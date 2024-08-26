package com.scentheartsstudio.scentheartsstudio.RestControllers;

import com.scentheartsstudio.scentheartsstudio.DTO.InterProductDTO;
import com.scentheartsstudio.scentheartsstudio.DTO.PostProductDTO;
import com.scentheartsstudio.scentheartsstudio.Services.ProductService;
import com.scentheartsstudio.scentheartsstudio.Services.UploadImageProductService;
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

	@Autowired
	private UploadImageProductService uips;

	@GetMapping("product")
	public Resp<List<InterProductDTO>> getAllProducts(){
		Resp<List<InterProductDTO>> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");

		List<InterProductDTO> data = ps.getAllProducts();
		response.setData(data);
		return response;
	}

	@GetMapping("product/byCategoryId")
	public Resp<List<InterProductDTO>> getAllProductsByCategoryId(@RequestParam("categoryId") Long category_id){
		Resp<List<InterProductDTO>> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");

		List<InterProductDTO> data = ps.getAllProductsByCategory(category_id);
		response.setData(data);
		return response;
	}

	@PostMapping("product/insert")
	public Resp<String> insertProduct(@RequestBody PostProductDTO postProductDTO){
		Resp<String> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");
		try{
			ps.insertProduct(postProductDTO);
		}catch (CustomException e){
			response.setCode(e.getCode());
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@PutMapping("product/update")
	public Resp<String> updateProduct(@RequestBody PostProductDTO postProductDTO){
		Resp<String> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");
		try{
			ps.updateProduct(postProductDTO);
		}catch (CustomException e){
			response.setCode(e.getCode());
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@DeleteMapping("product/delete")
	public Resp<String> deleteProduct(@RequestBody PostProductDTO postProductDTO) throws IOException {
		ps.deleteProduct(postProductDTO);
		Resp<String> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");
		return response;
	}

	@PostMapping("product/image")
	public Resp<List<String>> uploadImage(@RequestParam("productId") Long productId
			,@RequestParam("user_id") Long userId
			,@RequestParam("files") MultipartFile[] files) {
		Resp<List<String>> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");
		try {
			uips.uploadImageProduct(productId,userId,files);
		}catch (CustomException e) {
			response.setCode(e.getCode());
			response.setMessage(e.getMessage());
		} catch (IOException e) {
			response.setCode(455);
			response.setMessage("Failed to upload images!");
		}
		return response;
	}

	//	@GetMapping("product/productSize")
	//	public Resp<List<InterProductSizeDTO>> getProductSizeByProductId(@RequestParam("productId") Long product_id){
	//		Resp<List<InterProductSizeDTO>> response = new Resp<>();
	//		response.setCode(200);
	//		response.setMessage("OK");
	//
	//		List<InterProductSizeDTO> data = ps.getProductSizeByProductId(product_id);
	//		response.setData(data);
	//		return response;
	//	}

	//	@PutMapping("product/update")
	//	public Resp<List<String>> updateProduct(@ModelAttribute PostProductDTO postProductDTO,
	//	                                        @RequestParam("files") MultipartFile[] files){
	//		Resp<List<String>> response = new Resp<>();
	//		response.setCode(200);
	//		response.setMessage("OK");
	//		try{
	//			List<String> data = ps.insertProduct(postProductDTO, files);
	//			response.setData(data);
	//		}catch (CustomException e){
	//			response.setCode(e.getCode());
	//			response.setMessage(e.getMessage());
	//		} catch (IOException e) {
	//			e.printStackTrace();
	//			response.setCode(455);
	//			response.setMessage("Upload Image Failed!!");
	//		}
	//		return response;
	//	}
}
