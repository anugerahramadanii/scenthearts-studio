package com.scentheartsstudio.scentheartsstudio.Repositories;

import com.scentheartsstudio.scentheartsstudio.DTO.InterProductDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

//	@Query(nativeQuery = true,
//	value = "select p.id, p.name as product_name,p.category_id as category_id, c.name as category_name, \n"
//			+ "\tp.description, p.image_path, p.real_price, p.discount_rate, \n"
//			+ "\tp.discount_price, p.stock\n"
//			+ "\tfrom t_product p\n"
//			+ "\tinner join t_category c\n"
//			+ "\ton p.category_id = c.id\n"
//			+ "\twhere p.is_delete = false order by p.id asc")
//	public List<InterProductDTO> getAllProducts();


	@Query(nativeQuery = true,
			value = "select p.id, p.name as product_name, c.id as category_id, c.name as category_name, \n"
					+ "\tp.description, p.image_path, p.real_price, p.discount_rate,\n"
					+ "\tp.discount_price, ps.stock, ps.size\n"
					+ "\tfrom t_product p\n"
					+ "\tinner join t_product_size ps\n"
					+ "\ton p.id = ps.product_id\n"
					+ "\tinner join t_category c\n"
					+ "\ton p.category_id = c.id\n"
					+ "\twhere p.is_delete = false \n"
					+ "\tGROUP BY product_name, p.id, c.id, category_name, ps.stock, ps.size\n"
					+ "\tORDER BY p.id asc")
	public List<InterProductDTO> getAllProducts();

	@Query(nativeQuery = true,
	value = "select p.id, p.name as product_name, c.id as category_id, c.name as category_name, \n"
			+ "\tp.description, p.image_path, p.real_price, p.discount_rate,\n"
			+ "\tp.discount_price, ps.stock, ps.size\n"
			+ "\tfrom t_product p\n"
			+ "\tinner join t_product_size ps\n"
			+ "\ton p.id = ps.product_id\n"
			+ "\tinner join t_category c\n"
			+ "\ton p.category_id = c.id\n"
			+ "\twhere c.id = :categoryId and p.is_delete = false \n"
			+ "\tGROUP BY p.id, c.id, ps.size, ps.stock\n"
			+ "\tORDER BY p.id asc")
	public List<InterProductDTO> getAllProductsByCategoryId(@Param("categoryId")Long category_id);

	@Query(nativeQuery = true,
	value = "select exists(select * from t_product where name ilike :name and is_delete = false)")
	public Boolean isNameExists(@Param("name") String name);

	@Query(nativeQuery = true,
			value = "select exists(select * from t_product where name ilike :name and name not ilike :oldName and is_delete = false)")
	public Boolean isNameExistsUpdate(@Param("name") String name, @Param("oldName") String oldName);

	@Query(nativeQuery = true,
	value = "select exists(select * from t_product where id = :productId and is_delete = false)")
	public Boolean isProductExists(@Param("productId") Long product_id);

}
