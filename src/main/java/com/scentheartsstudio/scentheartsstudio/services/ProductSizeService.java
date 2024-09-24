package com.scentheartsstudio.scentheartsstudio.services;

import com.scentheartsstudio.scentheartsstudio.dto.InterProductSizeDTO;
import com.scentheartsstudio.scentheartsstudio.dto.PostProductSizeDTO;
import com.scentheartsstudio.scentheartsstudio.entities.ProductSizeEntity;
import com.scentheartsstudio.scentheartsstudio.repositories.ProductSizeRepository;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductSizeService {
	@Autowired
	private ProductSizeRepository psr;

	public List<InterProductSizeDTO> getAllSizes(){
		return psr.getAllSizes();
	}

	public void insertProductSize(PostProductSizeDTO postProductSizeDTO) throws CustomException {
		String productName = psr.getProductNameBySizeNameAndProductId(postProductSizeDTO.getSize_name(), postProductSizeDTO.getProduct_id());
		Boolean isNameExistByProductId = psr.isNameExistByProductId(postProductSizeDTO.getSize_name(), postProductSizeDTO.getProduct_id());
		if (isNameExistByProductId){
			throw new CustomException(452, "Size " + postProductSizeDTO.getSize_name() + " with Product " + productName + " already exists");
		}
		ProductSizeEntity productSizeEntity = new ProductSizeEntity();
		productSizeEntity.setProduct_id(postProductSizeDTO.getProduct_id());
		productSizeEntity.setSize_name(postProductSizeDTO.getSize_name());
		productSizeEntity.setStock(postProductSizeDTO.getStock());
		productSizeEntity.setActive(postProductSizeDTO.getActive());
		productSizeEntity.setCreated_by(postProductSizeDTO.getUser_id());
		productSizeEntity.setCreated_on(new Date());
		psr.save(productSizeEntity);
	}

	public void updateProductSize(PostProductSizeDTO postProductSizeDTO) throws CustomException {
		ProductSizeEntity productSizeEntity = psr.getReferenceById(postProductSizeDTO.getId());

		String productName = psr.getProductNameBySizeNameAndProductId(postProductSizeDTO.getSize_name(), postProductSizeDTO.getProduct_id());
		Boolean isNameProductSizeExistsUpdate = psr.isNameProductSizeExistsUpdate(postProductSizeDTO.getProduct_id(), postProductSizeDTO.getSize_name(), productSizeEntity.getSize_name());
		if (isNameProductSizeExistsUpdate){
			throw new CustomException(452, "Size " + postProductSizeDTO.getSize_name() + " with Product " + productName + " already exists");
		}
		productSizeEntity.setId(postProductSizeDTO.getId());
		productSizeEntity.setProduct_id(postProductSizeDTO.getProduct_id());
		productSizeEntity.setSize_name(postProductSizeDTO.getSize_name());
		productSizeEntity.setStock(postProductSizeDTO.getStock());
		productSizeEntity.setActive(postProductSizeDTO.getActive());
		productSizeEntity.setModified_by(postProductSizeDTO.getUser_id());
		productSizeEntity.setModified_on(new Date());
		psr.save(productSizeEntity);
	}

	public void deleteProductSize(PostProductSizeDTO postProductSizeDTO){
		ProductSizeEntity productSizeEntity = psr.getReferenceById(postProductSizeDTO.getId());
		productSizeEntity.setIs_delete(true);
		productSizeEntity.setDeleted_by(postProductSizeDTO.getUser_id());
		productSizeEntity.setDeleted_on(new Date());
		psr.save(productSizeEntity);
	}


}
