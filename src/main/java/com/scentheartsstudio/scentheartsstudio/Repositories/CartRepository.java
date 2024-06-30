package com.scentheartsstudio.scentheartsstudio.Repositories;

import com.scentheartsstudio.scentheartsstudio.DTO.InterCartDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
	@Query(nativeQuery = true,
	value = "select c.id, c.product_id, p.name as product_name, p.image_path as product_image,\n"
			+ "\tp.discount_price, c.total_price, c.quantity, c.user_id \n"
			+ "\tfrom t_cart c\n"
			+ "\tinner join t_product p\n"
			+ "\ton p.id = c.product_id\n"
			+ "\twhere c.user_id = :userId and c.is_delete = false")
	public List<InterCartDTO> getAllCartByUserId(@Param("userId") Long user_id);

		@Query(nativeQuery = true,
		value = "SELECT * FROM t_cart WHERE user_id = :userId AND product_id = :productId AND is_delete = false")
		Optional<CartEntity> getCartByUserIdAndProductId(@Param("userId") Long user_id,@Param("productId") Long product_id);

//	Optional<CartEntity> getCartByUserIdAndProductId(Long userId, Long productId);




}
