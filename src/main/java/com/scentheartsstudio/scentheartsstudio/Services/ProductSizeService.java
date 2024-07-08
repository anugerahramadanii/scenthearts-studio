package com.scentheartsstudio.scentheartsstudio.Services;

import com.scentheartsstudio.scentheartsstudio.DTO.InterProductSizeDTO;
import com.scentheartsstudio.scentheartsstudio.DTO.PostProductSizeDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.ProductSizeEntity;
import com.scentheartsstudio.scentheartsstudio.Repositories.ProductSizeRepository;
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
		Boolean isNameExist = psr.isNameExist(postProductSizeDTO.getName());
		if (isNameExist){
			throw new CustomException(452, "Product size name " + postProductSizeDTO.getName() + " already exists");
		}
		ProductSizeEntity productSizeEntity = new ProductSizeEntity();
		productSizeEntity.setName(postProductSizeDTO.getName());
		productSizeEntity.setActive(postProductSizeDTO.getActive());
		productSizeEntity.setCreated_by(postProductSizeDTO.getUser_id());
		productSizeEntity.setCreated_on(new Date());
		psr.save(productSizeEntity);
	}

	public void updateProductSize(PostProductSizeDTO postProductSizeDTO) throws CustomException {
		ProductSizeEntity productSizeEntity = psr.getReferenceById(postProductSizeDTO.getId());

		Boolean isNameExistUpdate = psr.isNameExistUpdate(postProductSizeDTO.getName(), productSizeEntity.getName());
		if (isNameExistUpdate){
			throw new CustomException(453, "Product size name " + postProductSizeDTO.getName() + " already exists");
		}
		productSizeEntity.setId(postProductSizeDTO.getId());
		productSizeEntity.setName(postProductSizeDTO.getName());
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
