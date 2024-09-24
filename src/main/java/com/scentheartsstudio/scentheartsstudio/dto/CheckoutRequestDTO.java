package com.scentheartsstudio.scentheartsstudio.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CheckoutRequestDTO {
	private String origin;
	private String destination;
	private int totalWeight;
	private String courier;
	private String orderId;
	private int subtotal;
	private List<MidtransRequestDTO.ItemDetails> itemDetails;
	private MidtransRequestDTO.CustomerDetails customerDetails;
	private Long userId;
}
