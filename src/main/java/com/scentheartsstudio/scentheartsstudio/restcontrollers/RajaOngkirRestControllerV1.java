//package com.scentheartsstudio.scentheartsstudio.restcontrollers;
//
//import com.scentheartsstudio.scentheartsstudio.dto.CityDTO;
//import com.scentheartsstudio.scentheartsstudio.dto.CostRequestDTO;
//import com.scentheartsstudio.scentheartsstudio.dto.CostResponseDTO;
//import com.scentheartsstudio.scentheartsstudio.dto.ProvinceDTO;
//import com.scentheartsstudio.scentheartsstudio.services.RajaOngkirService;
//import com.scentheartsstudio.scentheartsstudio.utils.Resp;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api")
//public class RajaOngkirRestControllerV1 {
//	@Autowired
//	private RajaOngkirService ros;
//
//	@GetMapping("/province")
//	public Resp<ProvinceDTO> getProvince(@RequestParam(value = "provinceId", required = false) String provinceId){
//		Resp<ProvinceDTO> response = new Resp<>();
//		response.setCode(200);
//		response.setMessage("OK");
//		response.setData(ros.getProvince(provinceId));
//		return response;
//	}
//
//	@GetMapping("/city")
//	public Resp<CityDTO> getCity(@RequestParam(value = "cityId", required = false) String cityId,
//	                             @RequestParam(value = "provinceId", required = false) String provinceId){
//		Resp<CityDTO> response = new Resp<>();
//		response.setCode(200);
//		response.setMessage("OK");
//		response.setData(ros.getCity(cityId, provinceId));
//		return response;
//	}
//
//	@PostMapping("/cost")
//	public Resp<CostResponseDTO> getCost(@RequestBody CostRequestDTO costRequestDTO){
//		Resp<CostResponseDTO> response = new Resp<>();
//		response.setCode(200);
//		response.setMessage("OK");
//		response.setData(ros.getCost(costRequestDTO));
//		return response;
//	}
//}
