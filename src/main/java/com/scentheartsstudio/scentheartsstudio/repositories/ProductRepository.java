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
			value = "select p.id, ps.id as product_size_id, ps.product_id, ps.name as product_size_name, p.name as product_name,\n"
					+ "\tp.category_id as category_id, c.name as category_name, p.description, p.image_path, ps.quantity, p.price\n"
					+ "\tfrom t_product p \n"
					+ "\tleft join t_product_size ps\n"
					+ "\ton p.id = ps.product_id and ps.is_delete = false\n"
					+ "\tinner join t_category c\n" + "\ton c.id = p.category_id\n"
					+ "\twhere p.is_delete = false\n"
					+ "\tORDER BY p.id ASC")
	public List<InterProductDTO> getAllProducts();

	@Query(nativeQuery = true,
	value = "select p.id, ps.product_id, ps.name as product_size_name, p.name as product_name, \n"
			+ "p.category_id as category_id, c.name as category_name, p.description, p.image_path, \n"
			+ "ps.quantity, p.price\n" + "from t_product p\n" + "left join t_product_size ps\n" + "on p.id = ps.product_id\n"
			+ "inner join t_category c\n" + "on p.category_id = c.id\n"
			+ "where p.category_id = :categoryId and p.is_delete = false ORDER BY p.id asc")
	public List<InterProductDTO> getAllProductsByCategoryId(@Param("categoryId")Long category_id);


	@Query(nativeQuery = true,
			value = "select exists(select * from t_product p\n"
					+ "\tinner join t_product_size ps\n"
					+ "\ton p.id = ps.product_id\n"
					+ "\twhere p.name ilike :productName and ps.name ilike :productSizeName and p.is_delete = false and ps.is_delete = false)")
	public Boolean isNameProductAndSizeExists(@Param("productName") String productName,
	                            @Param("productSizeName") String productSizeName);

	@Query(nativeQuery = true,
			value = "select exists(select * from t_product where name ilike :productName " +
					"and name not ilike :oldProductName and is_delete = false)")
	public Boolean isNameProductExistsUpdate(@Param("productName") String productName,
	                                   @Param("oldProductName") String oldProductName);

	@Query(nativeQuery = true,
		value = "select ps.quantity from t_product p\n"
				+ "\tinner join t_product_size ps\n"
				+ "\ton p.id = ps.product_id\n"
				+ "\twhere p.id = :productId and ps.id = :productSizeId and p.is_delete = false and ps.is_delete = false ")
	public Integer getQuantityProductByProductIdAndPsId(@Param("productId") Long productId, @Param("productSizeId") Long productSizeId);
}
