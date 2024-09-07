package com.scentheartsstudio.scentheartsstudio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CostRequestDTO {
	private String origin;
	private String destination;
	private int weight;
	private String courier;
}
