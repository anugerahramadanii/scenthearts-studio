package com.scentheartsstudio.scentheartsstudio.services;

import com.scentheartsstudio.scentheartsstudio.dto.*;
import com.scentheartsstudio.scentheartsstudio.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CheckoutService {
	@Autowired
	private RajaOngkirService ros;

	@Autowired
	private MidtransService ms;

	public CheckoutResponseDTO checkout(CheckoutRequestDTO checkoutRequestDTO) {
		// Calculate Shipping cost using Raja Ongkir
		CostRequestDTO costRequestDTO = new CostRequestDTO();
		costRequestDTO.setOrigin(checkoutRequestDTO.getOrigin());
		costRequestDTO.setDestination(checkoutRequestDTO.getDestination());
		costRequestDTO.setWeight(checkoutRequestDTO.getTotalWeight());
		costRequestDTO.setCourier(checkoutRequestDTO.getCourier());

		CostResponseDTO shippingCostResponse = ros.getCost(costRequestDTO);
		int shippingCost = shippingCostResponse.getRajaongkir()
				.getResults().get(0)
				.getCosts().get(0)
				.getCost().get(0)
				.getValue();

		// Create transaction with Midtrans
		MidtransRequestDTO midtransRequestDTO = new MidtransRequestDTO();
		MidtransRequestDTO.TransactionDetails transactionDetails = new MidtransRequestDTO.TransactionDetails();

		// Set item details and calculate gross_amount
		int grossAmount = 0;
		for (MidtransRequestDTO.ItemDetails item : checkoutRequestDTO.getItemDetails()) {
			grossAmount += item.getPrice() * item.getQuantity();
		}
		System.out.println(grossAmount);

		transactionDetails.setOrder_id(Utilities.generateOrderNumber(checkoutRequestDTO.getUserId()));
		transactionDetails.setGross_amount(grossAmount + shippingCost);

		midtransRequestDTO.setTransaction_details(transactionDetails);
		midtransRequestDTO.setItem_details(checkoutRequestDTO.getItemDetails());
		midtransRequestDTO.setCustomer_details(checkoutRequestDTO.getCustomerDetails());

		MidtransResponseDTO midtransResponseDTO = ms.createTransaction(midtransRequestDTO);

		// return checkout response
		CheckoutResponseDTO checkoutResponseDTO = new CheckoutResponseDTO();
		checkoutResponseDTO.setShippingCost(shippingCost);
		checkoutResponseDTO.setPaymentToken(midtransResponseDTO.getToken());
		checkoutResponseDTO.setRedirectUrl(midtransResponseDTO.getRedirect_url());

		return checkoutResponseDTO;
	}
}
