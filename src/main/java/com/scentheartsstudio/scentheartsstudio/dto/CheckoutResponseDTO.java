package com.scentheartsstudio.scentheartsstudio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutResponseDTO {
	private int shippingCost;
	private String paymentToken;
	private String redirectUrl;
}
