package com.scentheartsstudio.scentheartsstudio.enums;

public enum OrderStatusEnum {
	PENDING,         // Order has been placed but not yet processed
	PROCESSING,      // Order is being processed
	SHIPPED,         // Order has been shipped but not yet delivered
	DELIVERED,       // Order has been delivered to the customer
	CANCELLED,       // Order has been cancelled
	RETURNED,        // Order has been returned by the customer
	FAILED,          // Order processing failed
	REFUNDED         // Payment has been refunded to the customer
}
