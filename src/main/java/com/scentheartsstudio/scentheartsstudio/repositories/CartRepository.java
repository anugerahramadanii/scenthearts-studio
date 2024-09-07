package com.scentheartsstudio.scentheartsstudio.repositories;

import com.scentheartsstudio.scentheartsstudio.dto.InterCartDTO;
import com.scentheartsstudio.scentheartsstudio.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
	@Query(nativeQuery = true,
	value = "select c.id, c.product_id as product_id, p.name as product_name,\n"
			+ "\tc.product_size_id as product_size_id, p.image_path, c.product_price, c.total_price, ps.name as size_name,\n"
			+ "\tc.quantity, c.user_id\n"
			+ "\tfrom t_cart c\n"
			+ "\tinner join t_product p\n"
			+ "\ton p.id = c.product_id\n"
			+ "\tinner join m_user u\n"
			+ "\ton u.id = c.user_id \n"
			+ "\tinner join t_product_size ps\n"
			+ "\ton ps.id = c.product_size_id\n"
			+ "where c.user_id = :userId and c.is_delete = false")
	public List<InterCartDTO> getAllCartByUserId(@Param("userId") Long user_id);

//	@Query(nativeQuery = true,
//		value = "SELECT * FROM t_cart WHERE user_id = :userId AND product_id = :productId AND is_delete = false")
	//	Optional<CartEntity> getCartByUserIdAndProductId(@Param("userId") Long user_id, @Param("productId") Long product_id);

	@Query(nativeQuery = true,
			value = "select * from t_cart where product_id = :productId and product_size_id = :productSizeId and user_id = :userId and is_delete = false")
	Optional<CartEntity> getCartByUserIdAndProductIdAndPsId(@Param("productId") Long product_id,
													 @Param("productSizeId") Long product_size_id,
	                                                 @Param("userId") Long user_id);

	@Query(nativeQuery = true,
	value = "select sum(c.quantity) from t_cart c " +
			"where user_id = :userId and c.product_id = :productId and c.product_size_id = :productSizeId and c.is_delete = false")
	Integer getTotalQuantityInCartByUserIdAndProductIdAndProductSizeId(
			@Param("userId") Long userId,
			@Param("productId") Long productId,
			@Param("productSizeId") Long productSizeId);
}
