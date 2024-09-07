package com.scentheartsstudio.scentheartsstudio.repositories;

import com.scentheartsstudio.scentheartsstudio.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

}
