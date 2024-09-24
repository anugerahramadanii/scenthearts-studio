package com.scentheartsstudio.scentheartsstudio.services;

import com.scentheartsstudio.scentheartsstudio.dto.CostRequestDTO;
import com.scentheartsstudio.scentheartsstudio.dto.CostResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculateShippingCost {
	@Autowired
	private RajaOngkirService ros;

	public Double calculateShippingCost(String origin, String destination, int weight, String courier) {
		CostRequestDTO costRequestDTO = new CostRequestDTO();
		costRequestDTO.setOrigin(origin);
		costRequestDTO.setDestination(destination);
		costRequestDTO.setWeight(weight);
		costRequestDTO.setCourier(courier);

		// Mengirim permintaan ke Raja Ongkir API
		CostResponseDTO costResponse = ros.getCost(costRequestDTO);

		return (double) costResponse.getRajaongkir().getResults().get(0).getCosts().get(0).getCost().get(0).getValue();
	}
}
