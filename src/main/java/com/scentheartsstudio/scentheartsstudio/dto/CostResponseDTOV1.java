//package com.scentheartsstudio.scentheartsstudio.dto;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.List;
//
//@Getter
//@Setter
//public class CostResponseDTOV1 {
//	private RajaOngkir rajaongkir;
//
//	@Getter
//	@Setter
//	public static class RajaOngkir {
//		private Query query;
//		private Status status;
//		private OriginDetails origin_details;
//		private DestinationDetails destination_details;
//		private List<Result> results;
//
//
//	}
//
//	@Getter
//	@Setter
//	public static class Query {
//		private String origin;
//		private String destination;
//		private int weight;
//		private String courier;
//	}
//
//	@Getter
//	@Setter
//	public static class Status {
//		private int code;
//		private String description;
//	}
//
//	@Getter
//	@Setter
//	public static class OriginDetails {
//		private String city_id;
//		private String province_id;
//		private String province;
//		private String type;
//		private String city_name;
//		private String postal_code;
//	}
//
//	@Getter
//	@Setter
//	public static class DestinationDetails {
//		private String city_id;
//		private String province_id;
//		private String province;
//		private String type;
//		private String city_name;
//		private String postal_code;
//	}
//
//	@Getter
//	@Setter
//	public static class Result {
//		private String code;
//		private String name;
//		private List<Cost> costs;
//	}
//
//	@Getter
//	@Setter
//	public static class Cost {
//		private String service;
//		private String description;
//		private List<CostDetail> cost;
//	}
//
//	@Getter
//	@Setter
//	public static class CostDetail {
//		private int value;
//		private String etd;
//		private String note;
//	}
//}
