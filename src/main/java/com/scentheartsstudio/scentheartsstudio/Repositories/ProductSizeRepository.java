package com.scentheartsstudio.scentheartsstudio.Repositories;

import com.scentheartsstudio.scentheartsstudio.DTO.InterProductSizeDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.ProductSizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, Long> {

	@Modifying
	@Transactional
	@Query(nativeQuery = true,
	value = "DELETE FROM t_product_size WHERE product_id = :productId and is_deleted = false")
	public void deleteByProductId(@Param("productId") Long product_id);

	@Query(nativeQuery = true,
			value = "select ps.id, p.id as product_id, ps.size, ps.stock from t_product_size ps\n"
					+ "\tinner join t_product p\n"
					+ "\ton p.id = ps.product_id\n"
					+ "\twhere ps.product_id = :productId and ps.is_delete = false")
	public List<InterProductSizeDTO> getProductSizeByProductId(@Param("productId") Long product_id);

}
