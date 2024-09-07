package com.scentheartsstudio.scentheartsstudio.repositories;

import com.scentheartsstudio.scentheartsstudio.dto.InterProductSizeDTO;
import com.scentheartsstudio.scentheartsstudio.entities.ProductSizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, Long> {
	@Query(nativeQuery = true,
			value = "select * from t_product_size where product_id = :productId and is_delete = false")
	public List<ProductSizeEntity> getAllSizesByProductId(@Param("productId") Long productId);

	@Query(nativeQuery = true,
			value = "select exists(select * from t_product_size where product_id = :productId and name ilike :name and name not ilike :oldName and is_delete = false)")
	public Boolean isNameProductSizeExistsUpdate(@Param("productId") Long productId,
	                                             @Param("name") String name,
	                                             @Param("oldName") String oldName);

	@Query(nativeQuery = true,
			value = "select exists (select * from t_product_size where id = :id and is_delete = false)")
	public Boolean isProductSizeIdExists(@Param("id") Long id);

//	@Query(nativeQuery = true,
//			value = "select id, product_id, name, quantity, active from t_product_size where is_delete = false")
//	public List<InterProductSizeDTO> getAllSizes();
//
//	@Query(nativeQuery = true,
//			value = "select exists (select * from t_product_size where name ilike :name and is_delete =false)")
//	public Boolean isNameExist(@Param("name") String name);
}
