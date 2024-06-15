package com.scentheartsstudio.scentheartsstudio.Services;

import com.scentheartsstudio.scentheartsstudio.DTO.InterProductDTO;
import com.scentheartsstudio.scentheartsstudio.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

	@Autowired
	private ProductRepository pr;

	public List<InterProductDTO> getAllProduct(){
		return pr.getAllProducts();
	}
}
