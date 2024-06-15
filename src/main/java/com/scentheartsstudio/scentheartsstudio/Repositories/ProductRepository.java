package com.scentheartsstudio.scentheartsstudio.Repositories;

import com.scentheartsstudio.scentheartsstudio.DTO.InterProductDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	@Query(nativeQuery = true,
	value = "select * from t_product where is_delete = false")
	public List<InterProductDTO> getAllProducts();


}
