package com.scentheartsstudio.scentheartsstudio.repositories;

import com.scentheartsstudio.scentheartsstudio.dto.InterProductDTO;
import com.scentheartsstudio.scentheartsstudio.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	@Query(nativeQuery = true,
			value = "select p.id, ps.id as product_size_id, p.name as product_name, ps.size_name,\n"
					+ "p.category_id as category_id, c.name as category_name, p.description, p.image_path, \n"
					+ "ps.stock, p.price\n" + "from t_product p\n"
					+ "left join t_product_size ps\n" + "on p.id = ps.product_id\n"
					+ "inner join t_category c\n" + "on p.category_id = c.id\n"
					+ "where p.is_delete = false ORDER BY p.id asc")
	public List<InterProductDTO> getAllProducts();


	@Query(nativeQuery = true,
	value = "select p.id, ps.id as product_size_id, p.name as product_name, ps.size_name,\n"
			+ "p.category_id as category_id, c.name as category_name, p.description, p.image_path, \n"
			+ "ps.stock, p.price\n" + "from t_product p\n"
			+ "left join t_product_size ps\n" + "on p.id = ps.product_id\n"
			+ "inner join t_category c\n" + "on p.category_id = c.id\n"
			+ "where p.category_id = :categoryId and p.is_delete = false ORDER BY p.id asc")
	public List<InterProductDTO> getAllProductsByCategoryId(@Param("categoryId")Long category_id);

	@Query(nativeQuery = true,
			value = "select exists(select * from t_product where name ilike :name and is_delete = false)")
	public Boolean isNameExists(@Param("name") String name);

	@Query(nativeQuery = true,
			value = "select exists(select * from t_product where name ilike :name and name not ilike :oldName and is_delete = false)")
	public Boolean isNameExistsUpdate(@Param("name") String name,
	                                        @Param("oldName") String oldName);

	@Query(nativeQuery = true,
		value = "select ps.stock from t_product p\n"
				+ "\tinner join t_product_size ps\n"
				+ "\ton p.id = ps.product_id\n"
				+ "\twhere p.id = :productId and ps.id = :productSizeId and p.is_delete = false and ps.is_delete = false ")
	public Integer getQuantityProductByProductIdAndPsId(@Param("productId") Long productId, @Param("productSizeId") Long productSizeId);
}
