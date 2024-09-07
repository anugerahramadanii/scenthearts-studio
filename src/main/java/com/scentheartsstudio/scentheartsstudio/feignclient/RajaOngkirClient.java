package com.scentheartsstudio.scentheartsstudio.feignclient;

import com.scentheartsstudio.scentheartsstudio.dto.*;
import com.scentheartsstudio.scentheartsstudio.utils.RajaOngkirResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "rajaOngkirClient", url = "${rajaongkir.api.url}")
public interface RajaOngkirClient {

	@GetMapping("/province")
	RajaOngkirResp<ProvinceDTO> getProvince(@RequestHeader("key") String apiKey,
	                           @RequestParam(value = "id", required = false) String provinceId);

	@GetMapping("/city")
	RajaOngkirResp<CityDTO> getCity(@RequestHeader("key") String apiKey,
	                        @RequestParam(value = "id", required = false) String cityId,
	                        @RequestParam(value = "province", required = false) String provinceId);

	@PostMapping(value = "/cost", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	CostResponseDTO getCost(@RequestHeader("key") String apiKey,
	                        @RequestBody CostRequestDTO costRequestDTO);
}
