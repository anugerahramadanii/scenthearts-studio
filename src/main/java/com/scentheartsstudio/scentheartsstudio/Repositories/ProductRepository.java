package com.scentheartsstudio.scentheartsstudio.Repositories;

import com.scentheartsstudio.scentheartsstudio.DTO.InterProductDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
// import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

//	@Query(nativeQuery = true,
//	value = "select p.id, p.name as product_name,p.category_id as category_id, c.name as category_name, \n"
//			+ "\tp.description, p.image_path, p.real_price, p.discount_rate, \n"
//			+ "\tp.discount_price, p.quantity\n"
//			+ "\tfrom t_product p\n"
//			+ "\tinner join t_category c\n"
//			+ "\ton p.category_id = c.id\n"
//			+ "\twhere p.is_delete = false order by p.id asc")
//	public List<InterProductDTO> getAllProducts();


	@Query(nativeQuery = true,
			value = "select p.id, p.product_size_id as product_size_id, ps.name as product_size_name, p.name as product_name, \n"
					+ "\tp.category_id as category_id, c.name as category_name, p.description, p.image_path, \n"
					+ "\tp.quantity, p.discount_rate, p.real_price, p.discount_price\n"
					+ "\tfrom t_product p\n"
					+ "\tinner join t_product_size ps\n"
					+ "\ton ps.id = p.product_size_id\n"
					+ "\tinner join t_category c\n"
					+ "\ton p.category_id = c.id\n"
					+ "\twhere p.is_delete = false ORDER BY p.id asc")
	public List<InterProductDTO> getAllProducts();

	@Query(nativeQuery = true,
	value = "select p.id, p.product_size_id as product_size_id, ps.name as product_size_name, p.name as product_name, \n"
			+ "\tp.category_id as category_id, c.name as category_name, p.description, p.image_path, \n"
			+ "\tp.quantity, p.discount_rate, p.real_price, p.discount_price\n" + "\tfrom t_product p\n"
			+ "\tinner join t_product_size ps\n"
			+ "\ton ps.id = p.product_size_id\n"
			+ "\tinner join t_category c\n"
			+ "\ton p.category_id = c.id\n"
			+ "\twhere p.category_id = :categoryId and p.is_delete = false ORDER BY p.id asc")
	public List<InterProductDTO> getAllProductsByCategoryId(@Param("categoryId")Long category_id);

	@Query(nativeQuery = true,
	value = "select exists(select * from t_product p\n"
			+ "\tinner join t_product_size ps\n"
			+ "\ton ps.id = p.product_size_id\n"
			+ "\twhere p.name ilike :productName and ps.name ilike :productSizeName and p.is_delete = false)")
	public Boolean isNameExists(@Param("productName") String productName, @Param("productSizeName") String productSizeName);

	@Query(nativeQuery = true,
			value = "select exists(select * from t_product p\n" +
					"\tinner join t_product_size ps\n" +
					"\ton ps.id = p.product_size_id\n" +
					"\twhere p.name ilike :productName\n" +
					"\tand p.name not ilike :oldProductName\n" +
					"\tand ps.name ilike :productSizeName\n" +
					"\tand ps.name not ilike :oldProductSizeName\n" +
					"\tand p.is_delete = false)")
	public Boolean isNameExistsUpdate(@Param("productName") String productName,
									  @Param("oldProductName") String oldProductName,
	                                  @Param("productSizeName") String productSizeName,
	                                  @Param("oldProductSizeName") String oldProductSizeName);

//	@Query(nativeQuery = true,
//			value = "select exists(select * from t_product where name ilike :name and name not ilike :oldName and is_delete = false)")
//	public Boolean isNameExistsUpdate(@Param("name") String name, @Param("oldName") String oldName);

	@Query(nativeQuery = true,
		value = "select quantity from t_product p\n"
				+ "\tinner join t_product_size ps\n"
				+ "\ton p.product_size_id = ps.id\n"
				+ "\twhere p.id = :productId and ps.id = :productSizeId and p.is_delete = false")
	public Integer getQuantityProductByProductIdAndPsId(@Param("productId") Long productId, @Param("productSizeId") Long productSizeId);
}
