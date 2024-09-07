package com.scentheartsstudio.scentheartsstudio.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class RajaOngkirResp<T> {
	private RajaOngkir<T> rajaongkir;

	@Getter
	@Setter
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class RajaOngkir<T> {
		private Query query;
		private Status status;
		private T results;
	}

	@Getter
	@Setter
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Query {
		private String id;
		private String province;
	}

	@Getter
	@Setter
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Status {
		private int code;
		private String description;
	}
}
