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
			value = "select * from t_product_size where is_delete = false")
	public List<InterProductSizeDTO> getAllSizes();

	@Query(nativeQuery = true,
			value = "select exists (select * from t_product_size where size_name ilike :sizeName and product_id = :productId and is_delete = false)")
	public Boolean isNameExistByProductId(@Param("sizeName") String size_name,
	                           @Param("productId") Long product_id);

//	Get Product Name
	@Query(nativeQuery = true,
			value = "select p.name as product_name from t_product_size ps inner join t_product p on p.id = ps.product_id\n"
					+ "where ps.size_name ilike :sizeName and ps.product_id = :productId and ps.is_delete = false")
	public String getProductNameBySizeNameAndProductId(@Param("sizeName") String size_name,
	                                                   @Param("productId") Long product_id);


	@Query(nativeQuery = true,
			value = "select exists(select * from t_product_size where product_id = :productId and size_name ilike :size_name and size_name not ilike :oldSizeName and is_delete = false)")
	public Boolean isNameProductSizeExistsUpdate(@Param("productId") Long productId,
	                                             @Param("size_name") String size_name,
	                                             @Param("oldSizeName") String old_size_name);
}
