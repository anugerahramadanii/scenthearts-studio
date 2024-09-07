package com.scentheartsstudio.scentheartsstudio.restcontrollers;

import com.scentheartsstudio.scentheartsstudio.dto.*;
import com.scentheartsstudio.scentheartsstudio.services.RajaOngkirService;
import com.scentheartsstudio.scentheartsstudio.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class RajaOngkirRestController {
	@Autowired
	private RajaOngkirService ros;

	@GetMapping("/province")
	public RajaOngkirResp<ProvinceDTO> getProvince(@RequestParam(value = "provinceId", required = false) String provinceId)
			throws CustomExceptionRO {
		RajaOngkirResp<ProvinceDTO> response = new RajaOngkirResp<>();
		RajaOngkirResp.RajaOngkir<ProvinceDTO> rajaOngkir = new RajaOngkirResp.RajaOngkir<>();
		RajaOngkirResp.Query query = new RajaOngkirResp.Query();
		query.setId(provinceId);
		rajaOngkir.setQuery(query);
		try {
			ProvinceDTO provinceData = ros.getProvince(provinceId);
			rajaOngkir.setResults(provinceData);

			RajaOngkirResp.Status status = new RajaOngkirResp.Status();
			status.setCode(200);
			status.setDescription("OK");
			rajaOngkir.setStatus(status);
		} catch (CustomExceptionRO e){
			RajaOngkirResp.Status status = new RajaOngkirResp.Status();
			status.setCode(e.getCode());
			status.setDescription(e.getDescription());
			rajaOngkir.setStatus(status);
			rajaOngkir.setResults(null);
		}
		response.setRajaongkir(rajaOngkir);
		return response;
	}

	@GetMapping("/city")
	public RajaOngkirResp<CityDTO> getCity(@RequestParam(value = "cityId", required = false) String cityId,
	                                       @RequestParam(value = "provinceId", required = false) String provinceId)
			throws CustomExceptionRO {
		RajaOngkirResp<CityDTO> response = new RajaOngkirResp<>();
		RajaOngkirResp.RajaOngkir<CityDTO> rajaOngkir = new RajaOngkirResp.RajaOngkir<>();
		RajaOngkirResp.Query query = new RajaOngkirResp.Query();
		query.setId(cityId);
		query.setProvince(provinceId);
		rajaOngkir.setQuery(query);
		try {
			CityDTO cityData = ros.getCity(cityId, provinceId);
			rajaOngkir.setResults(cityData);

			RajaOngkirResp.Status status = new RajaOngkirResp.Status();
			status.setCode(200);
			status.setDescription("OK");
			rajaOngkir.setStatus(status);
		} catch (CustomExceptionRO e){
			RajaOngkirResp.Status status = new RajaOngkirResp.Status();
			status.setCode(e.getCode());
			status.setDescription(e.getDescription());
			rajaOngkir.setStatus(status);
			rajaOngkir.setResults(null);
		}
		response.setRajaongkir(rajaOngkir);
		return response;
	}

	@PostMapping("/cost")
	public Resp<CostResponseDTO> getCost(@RequestBody CostRequestDTO costRequestDTO){
		Resp<CostResponseDTO> response = new Resp<>();
		response.setCode(200);
		response.setMessage("OK");
		response.setData(ros.getCost(costRequestDTO));
		return response;
	}


//	@PostMapping("/cost")
//	public RajaOngkirCostResp<CostDTO> getCost(@RequestParam("origin") String origin,
//	                                           @RequestParam("destination") String destination,
//	                                           @RequestParam("weight") int weight,
//	                                           @RequestParam("courier") String courier) {
//		RajaOngkirCostResp<CostDTO> response = new RajaOngkirCostResp<>();
//		RajaOngkirCostResp.RajaOngkir<CostDTO> rajaOngkir = new RajaOngkirCostResp.RajaOngkir<>();
//		RajaOngkirCostResp.Query query = new RajaOngkirCostResp.Query();
//		query.setOrigin(origin);
//		query.setDestination(destination);
//		query.setWeight(weight);
//		query.setCourier(courier);
//		rajaOngkir.setQuery(query);
//
//		try {
//			CostDTO costData = ros.getCost(origin, destination, weight, courier);
//			RajaOngkirCostResp.Status status = new RajaOngkirCostResp.Status();
//			status.setCode(200);
//			status.setDescription("OK");
//			rajaOngkir.setStatus(status);
//			rajaOngkir.setResults(costData);
//		} catch (CustomExceptionRO e) {
//			RajaOngkirCostResp.Status status = new RajaOngkirCostResp.Status();
//			status.setCode(e.getCode());
//			status.setDescription(e.getDescription());
//			rajaOngkir.setStatus(status);
//			rajaOngkir.setResults(null);
//		}
//
//		response.setRajaongkir(rajaOngkir);
//		return response;
//	}
}
