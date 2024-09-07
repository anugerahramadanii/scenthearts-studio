package com.scentheartsstudio.scentheartsstudio.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class RajaOngkirCostResp<T> {
	private RajaOngkir<T> rajaongkir;

	@Getter
	@Setter
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class RajaOngkir<T> {
		private Query query;
		private Status status;
		private OriginDetails origin_details;
		private DestinationDetails destination_details;
		private T results;
	}

	@Getter
	@Setter
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Query {
		private String origin;
		private String destination;
		private int weight;
		private String courier;
	}

	@Getter
	@Setter
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Status {
		private int code;
		private String description;
	}

	@Getter
	@Setter
	public static class OriginDetails {
		private String city_id;
		private String province_id;
		private String province;
		private String type;
		private String city_name;
		private String postal_code;
	}

	@Getter
	@Setter
	public static class DestinationDetails {
		private String city_id;
		private String province_id;
		private String province;
		private String type;
		private String city_name;
		private String postal_code;
	}
}
