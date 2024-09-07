//package com.scentheartsstudio.scentheartsstudio.services;
//
//import com.scentheartsstudio.scentheartsstudio.dto.CityDTO;
//import com.scentheartsstudio.scentheartsstudio.dto.CostRequestDTO;
//import com.scentheartsstudio.scentheartsstudio.dto.CostResponseDTO;
//import com.scentheartsstudio.scentheartsstudio.dto.ProvinceDTO;
//import com.scentheartsstudio.scentheartsstudio.feignclient.RajaOngkirClient;
//import com.scentheartsstudio.scentheartsstudio.utils.RajaOngkirResp;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class RajaOngkirServiceV1 {
//
//	@Autowired
//	private RajaOngkirClient rajaOngkirClient;
//
//	@Value("${rajaongkir.api.key}")
//	private String apiKey;
//
//	public ProvinceDTO getProvince(String provinceId){
//		RajaOngkirResp<ProvinceDTO> response = rajaOngkirClient.getProvince(apiKey, provinceId);
//		return response.getRajaongkir().getResults();
//	}
//
//	public CityDTO getCity(String cityId, String provinceId){
//		RajaOngkirResp<CityDTO> response = rajaOngkirClient.getCity(apiKey, cityId, provinceId);
//		return response.getRajaongkir().getResults();
//	}
//
//	public CostResponseDTO getCost(CostRequestDTO costRequestDTO) {
//		return rajaOngkirClient.getCost(apiKey, costRequestDTO);
//	}
//}
