package com.scentheartsstudio.scentheartsstudio.Repositories;

import com.scentheartsstudio.scentheartsstudio.DTO.InterProductDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	@Query(nativeQuery = true,
	value = "select p.id, p.name as product_name,p.category_id as category_id, c.name as category_name, \n"
			+ "\tp.description, p.image_path, p.real_price, \n"
			+ "\tp.discount_price, p.stock\n"
			+ "\tfrom t_product p\n"
			+ "\tinner join t_category c\n"
			+ "\ton p.category_id = c.id\n"
			+ "\twhere p.is_delete = false")
	public List<InterProductDTO> getAllProducts();

	@Query(nativeQuery = true,
	value = "select exists(select * from t_product where name ilike :name and is_delete = false)")
	public Boolean isNameExist(@Param("name") String name);

	@Query(nativeQuery = true,
			value = "select exists(select * from t_product where name ilike :name and name not ilike :oldName and is_delete = false)")
	public Boolean isNameExistUpdate(@Param("name") String name, @Param("oldName") String oldName);
}
