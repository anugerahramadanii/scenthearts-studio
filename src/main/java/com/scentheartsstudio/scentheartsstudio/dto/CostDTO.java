package com.scentheartsstudio.scentheartsstudio.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CostDTO {
	private String code;
	private String name;
	private List<Costs> costs;

	@Getter
	@Setter
	public static class Costs {
		private String service;
		private String description;
		private List<CostDetail> cost;

		@Getter
		@Setter
		public static class CostDetail {
			private int value;
			private String etd;
			private String note;
		}
	}
}
