package com.scentheartsstudio.scentheartsstudio.Repositories;

import com.scentheartsstudio.scentheartsstudio.DTO.InterProductSizeDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.ProductSizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
// import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, Long> {
	@Query(nativeQuery = true,
		value = "select id, name, active from t_product_size where is_delete = false")
	public List<InterProductSizeDTO> getAllSizes();

	@Query(nativeQuery = true,
		value = "select exists (select * from t_product_size where name ilike :name and is_delete =false)")
	public Boolean isNameExist(@Param("name") String name);

	@Query(nativeQuery = true,
			value = "select exists(select * from t_product_size where name ilike :name and name not ilike :oldName and is_delete = false)")
	public Boolean isNameExistUpdate(@Param("name") String name, @Param("oldName") String oldName);

	@Query(nativeQuery = true,
			value = "select exists (select * from t_product_size where id = :id and is_delete = false)")
	public Boolean isProductSizeIdExists(@Param("id") Long id);
}
