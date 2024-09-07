package com.scentheartsstudio.scentheartsstudio.dto;

import lombok.Data;

import java.util.List;

@Data
public class MidtransRequestDTO {

	private TransactionDetails transaction_details;
	private List<ItemDetails> item_details;
	private CustomerDetails customer_details;
	private List<String> enabled_payments;
	private BcaVa bca_va;
	private BniVa bni_va;
	private BriVa bri_va;
	private Gopay gopay;
	private Expiry expiry;
	private PageExpiry page_expiry;
	private Recurring recurring;

	// Define nested classes for the fields
	@Data
	public static class TransactionDetails {
		private String order_id;
		private int gross_amount;
	}

	@Data
	public static class ItemDetails {
		private String id;
		private int price;
		private int quantity;
		private String name;
		private String brand;
		private String category;
		private String merchant_name;
		private String url;
	}

	@Data
	public static class CustomerDetails {
		private String first_name;
		private String last_name;
		private String email;
		private String phone;
		private Address billing_address;
		private Address shipping_address;

		@Data
		public static class Address {
			private String first_name;
			private String last_name;
			private String email;
			private String phone;
			private String address;
			private String city;
			private String postal_code;
			private String country_code;
		}
	}

	@Data
	public static class BcaVa {
		private String va_number;
		private String sub_company_code;
		private FreeText free_text;

		@Data
		public static class FreeText {
			private List<LanguageText> inquiry;
			private List<LanguageText> payment;

			@Data
			public static class LanguageText {
				private String en;
				private String id;
			}
		}
	}

	@Data
	public static class BniVa {
		private String va_number;
	}

	@Data
	public static class BriVa {
		private String va_number;
	}

	@Data
	public static class Gopay {
		private boolean enable_callback;
		private String callback_url;
	}

	@Data
	public static class Expiry {
		private String start_time;
		private String unit;
		private int duration;
	}

	@Data
	public static class PageExpiry {
		private int duration;
		private String unit;
	}

	@Data
	public static class Recurring {
		private boolean required;
		private String start_time;
		private String interval_unit;
	}
}
