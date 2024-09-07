package com.scentheartsstudio.scentheartsstudio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResponseDTO {
	private RajaOngkir rajaongkir;

	@Getter
	@Setter
	public static class RajaOngkir {
		private Query query;
		private Status status;
		private Result results;
	}

	@Getter
	@Setter
	public static class Query {
		private String province;
		private String id;
	}

	@Getter
	@Setter
	public static class Status {
		private int code;
		private String description;
	}

	@Getter
	@Setter
	public static class Result {
		private String city_id;
		private String province_id;
		private String province;
		private String type;
		private String city_name;
		private String postal_code;
	}
}
