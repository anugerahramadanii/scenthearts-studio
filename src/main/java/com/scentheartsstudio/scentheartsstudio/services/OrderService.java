package com.scentheartsstudio.scentheartsstudio.services;

import com.scentheartsstudio.scentheartsstudio.dto.*;
import com.scentheartsstudio.scentheartsstudio.entities.OrderEntity;
import com.scentheartsstudio.scentheartsstudio.entities.OrderItemEntity;
import com.scentheartsstudio.scentheartsstudio.enums.OrderStatusEnum;
import com.scentheartsstudio.scentheartsstudio.repositories.*;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import com.scentheartsstudio.scentheartsstudio.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
	//method create order
	//check if user exists
	// if exist create order
	//create order details
	// update product stock

	@Autowired
	private CartRepository cr;

	@Autowired
	private ProductRepository pr;

	@Autowired
	private ProductSizeRepository psr;

	@Autowired
	private OrderRepository or;

	@Autowired
	private OrderItemRepository oir;

	@Autowired
	private RajaOngkirService ros;


	@Transactional
	public void order(OrderDTO orderDTO) throws CustomException {
		// Generate order number
		String orderNumber = Utilities.generateOrderNumber(orderDTO.getUser_id());

		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setUser_id(orderDTO.getUser_id());
		orderEntity.setShipping_address(orderDTO.getShipping_address());
		orderEntity.setOrder_number(orderNumber);
		orderEntity.setStatus(OrderStatusEnum.PENDING);
		orderEntity.setOrder_date(new Date());
		orderEntity.setCreated_by(orderDTO.getUser_id());
		orderEntity.setCreated_on(new Date());

		List<OrderItemEntity> items = new ArrayList<>();
		double totalAmount = 0.0;

		for (int i=0; i < orderDTO.getItems().size(); i++){
			OrderItemDTO orderItemDTO = orderDTO.getItems().get(i);

			Integer availableStock = pr.getQuantityProductByProductIdAndPsId(
					orderItemDTO.getProduct_id(),
					orderItemDTO.getProduct_size_id()
			);
			if (availableStock < orderItemDTO.getQuantity()){
				throw new CustomException(425, "Stock not enough for product ID " + orderItemDTO.getProduct_id() + " with size " + orderItemDTO.getProduct_size_id());
			}

			OrderItemEntity orderItemEntity = new OrderItemEntity();
			orderItemEntity.setOrder_id(orderEntity.getId());
			orderItemEntity.setProduct_id(orderItemDTO.getProduct_id());
			orderItemEntity.setProduct_size_id(orderItemDTO.getProduct_size_id());
			orderItemEntity.setQuantity(orderItemDTO.getQuantity());
			orderItemEntity.setTotal_amount(orderItemDTO.getPrice() * orderItemDTO.getQuantity());
			orderItemEntity.setActive(true);
			orderItemEntity.setCreated_by(1L);
			orderItemEntity.setCreated_on(new Date());
			totalAmount += orderItemEntity.getTotal_amount();
			items.add(orderItemEntity);

//			pr.decreaseStock(orderItemDTO.getProduct_id(), orderItemDTO.getProduct_size_id(), orderItemDTO.getQuantity());
		}

		// Calculate the shipping cost using RajaOngkir
		CostRequestDTO costRequestDTO = new CostRequestDTO();
		costRequestDTO.setOrigin("501");
		costRequestDTO.setDestination(orderDTO.getShipping_address());
		costRequestDTO.setWeight(orderDTO.getWeight());
		costRequestDTO.setCourier(orderDTO.getCourier());

		CostResponseDTO costResponseDTO = ros.getCost(costRequestDTO);
		double shippingCost = (double) costResponseDTO.getRajaongkir().getResults().get(0).getCosts().get(0).getCost().get(0).getValue();

		orderEntity.setShipping_cost(shippingCost);
		orderEntity.setPayment_method("");
		orderEntity.setTotal_amount(totalAmount + shippingCost);

		or.save(orderEntity);
		oir.saveAll(items);
	}
}
