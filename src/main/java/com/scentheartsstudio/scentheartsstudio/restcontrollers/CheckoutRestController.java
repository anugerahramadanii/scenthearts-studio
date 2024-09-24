//package com.scentheartsstudio.scentheartsstudio.restcontrollers;
//
//import com.scentheartsstudio.scentheartsstudio.dto.CheckoutRequestDTO;
//import com.scentheartsstudio.scentheartsstudio.dto.CheckoutResponseDTO;
//import com.scentheartsstudio.scentheartsstudio.services.CheckoutService;
//import com.scentheartsstudio.scentheartsstudio.utils.Resp;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api")
//public class CheckoutRestController {
//
//	@Autowired
//	private CheckoutService cs;
//
//	@PostMapping("/checkout")
//	public Resp<CheckoutResponseDTO> checkout (@RequestBody CheckoutRequestDTO checkoutRequestDTO){
//		Resp<CheckoutResponseDTO> response = new Resp<>();
//		response.setCode(200);
//		response.setMessage("OK");
//		response.setData(cs.checkout(checkoutRequestDTO));
//		return response;
//	}
//}
