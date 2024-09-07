//package com.scentheartsstudio.scentheartsstudio.feignclient;
//
//import com.scentheartsstudio.scentheartsstudio.dto.CityDTO;
//import com.scentheartsstudio.scentheartsstudio.dto.CostRequestDTO;
//import com.scentheartsstudio.scentheartsstudio.dto.CostResponseDTO;
//import com.scentheartsstudio.scentheartsstudio.dto.ProvinceDTO;
//import com.scentheartsstudio.scentheartsstudio.utils.RajaOngkirResp;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//
//@FeignClient(name = "rajaOngkirClient", url = "${rajaongkir.api.url}")
//public interface RajaOngkirClientV1 {
//
//	@RequestMapping(value = "/province", method = RequestMethod.GET)
//	RajaOngkirResp<ProvinceDTO> getProvince(@RequestHeader("key") String apiKey,
//	                                        @RequestParam(value = "id", required = false) String provinceId);
//
//	@RequestMapping(value = "/city", method = RequestMethod.GET)
//	RajaOngkirResp<CityDTO> getCity(@RequestHeader("key") String apiKey,
//	                      @RequestParam(value = "id", required = false) String cityId,
//	                      @RequestParam(value = "province", required = false) String provinceId);
//
//	@RequestMapping(value = "/cost", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = RequestMethod.POST)
//	CostResponseDTO getCost(
//			@RequestHeader("key") String apiKey,
//			@RequestBody CostRequestDTO costRequestDTO
//			);
//}
