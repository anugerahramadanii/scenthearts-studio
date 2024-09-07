package com.scentheartsstudio.scentheartsstudio.services;

import com.scentheartsstudio.scentheartsstudio.dto.*;
import com.scentheartsstudio.scentheartsstudio.feignclient.RajaOngkirClient;
import com.scentheartsstudio.scentheartsstudio.utils.CustomExceptionRO;
import com.scentheartsstudio.scentheartsstudio.utils.RajaOngkirResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RajaOngkirService {
	private static final Logger logger = LoggerFactory.getLogger(RajaOngkirService.class);

	@Autowired
	private RajaOngkirClient rajaOngkirClient;

	@Value("${rajaongkir.api.key}")
	private String apiKey;

	public ProvinceDTO getProvince(String provinceId) throws CustomExceptionRO {
		logger.info("Fetching province data for provinceId: {}", provinceId);
		RajaOngkirResp<ProvinceDTO> response = rajaOngkirClient.getProvince(apiKey, provinceId);
		int statusCode = response.getRajaongkir().getStatus().getCode();
		String statusDescription = response.getRajaongkir().getStatus().getDescription();

		if (statusCode != 200){
			throw new CustomExceptionRO(statusCode, statusDescription);
		}
		return response.getRajaongkir().getResults();
	}

	public CityDTO getCity(String cityId, String provinceId) throws CustomExceptionRO {
		RajaOngkirResp<CityDTO> response = rajaOngkirClient.getCity(apiKey, cityId, provinceId);
		int statusCode = response.getRajaongkir().getStatus().getCode();
		String statusDescription = response.getRajaongkir().getStatus().getDescription();

		if (statusCode != 200){
			throw new CustomExceptionRO(statusCode, statusDescription);
		}
		return response.getRajaongkir().getResults();
	}


	public CostResponseDTO getCost(CostRequestDTO costRequestDTO) {
		return rajaOngkirClient.getCost(apiKey, costRequestDTO);
	}
}
