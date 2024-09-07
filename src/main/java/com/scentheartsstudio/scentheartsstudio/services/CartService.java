package com.scentheartsstudio.scentheartsstudio.services;

import com.scentheartsstudio.scentheartsstudio.dto.InterCartDTO;
import com.scentheartsstudio.scentheartsstudio.dto.PostCartDTO;
import com.scentheartsstudio.scentheartsstudio.entities.CartEntity;
import com.scentheartsstudio.scentheartsstudio.entities.ProductEntity;
import com.scentheartsstudio.scentheartsstudio.entities.ProductSizeEntity;
import com.scentheartsstudio.scentheartsstudio.repositories.CartRepository;
import com.scentheartsstudio.scentheartsstudio.repositories.ProductRepository;
import com.scentheartsstudio.scentheartsstudio.repositories.ProductSizeRepository;
import com.scentheartsstudio.scentheartsstudio.repositories.UserRepository;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

	@Autowired
	private CartRepository cr;

	@Autowired
	private ProductRepository pr;

	@Autowired
	private UserRepository ur;

	@Autowired
	private ProductSizeRepository psr;

	public List<InterCartDTO> getAllCartByUserId(Long user_id) {
		return cr.getAllCartByUserId(user_id);
	}

	@Transactional
	public void addProductToCart(PostCartDTO postCartDTO) throws CustomException {
		// Cek Product ada atau tidak di db
		ProductEntity productEntity = pr.findById(postCartDTO.getProduct_id())
				.orElseThrow(() -> new CustomException(420, "Product with ID " + postCartDTO.getProduct_id() + " Not Found"));

		ProductSizeEntity productSizeEntity = psr.findById(postCartDTO.getProduct_size_id())
				.orElseThrow(() -> new CustomException(421, "Product Size with ID " + postCartDTO.getProduct_size_id() + " Not Found"));

//		available quantity
		Integer quantityProductInStock = pr.getQuantityProductByProductIdAndPsId(
				postCartDTO.getProduct_id(),
				postCartDTO.getProduct_size_id()
		);
//		System.out.println("Quantity product remaining = " + quantityProductInStock);

		// quantity product in cart user
		Integer quantityInCart = cr.getTotalQuantityInCartByUserIdAndProductIdAndProductSizeId(
				postCartDTO.getProduct_id(),
				postCartDTO.getUser_id(),
				postCartDTO.getProduct_size_id()
		);

		if (quantityInCart == null) {
			quantityInCart = 0;
		}
//		System.out.println("Quantity in cart for this product and size = " + quantityInCart);

		if (postCartDTO.getQuantity() <= 0){
			throw new CustomException(423, "Quantity must be greater than 0");
		}

		if (postCartDTO.getQuantity() + quantityInCart > quantityProductInStock){
			throw new CustomException(424, "Quantity not enough!!, Product Id " + postCartDTO.getProduct_id() + " with Size " +
					postCartDTO.getProduct_size_id() + " only has " + (quantityProductInStock - quantityInCart) + " remaining");
		}

		// is product in cart
		Optional<CartEntity> optCart = cr.getCartByUserIdAndProductIdAndPsId(postCartDTO.getProduct_id(), postCartDTO.getProduct_size_id(), postCartDTO.getUser_id());
		CartEntity cartEntity;
		if (optCart.isPresent()) {
			cartEntity = optCart.get();
			cartEntity.setQuantity(cartEntity.getQuantity() + postCartDTO.getQuantity());
			cartEntity.setProduct_price(productEntity.getPrice());
			cartEntity.setTotal_price(cartEntity.getProduct_price() * cartEntity.getQuantity());
			cartEntity.setModified_by(postCartDTO.getUser_id());
			cartEntity.setModified_on(new Date());
		} else {
			cartEntity = new CartEntity();
			cartEntity.setUser_id(postCartDTO.getUser_id());
			cartEntity.setProduct_id(postCartDTO.getProduct_id());
			cartEntity.setProduct_size_id(productSizeEntity.getId());
			cartEntity.setQuantity(postCartDTO.getQuantity());
			cartEntity.setProduct_price(productEntity.getPrice());
			cartEntity.setTotal_price(productEntity.getPrice() * postCartDTO.getQuantity());
			cartEntity.setCreated_by(postCartDTO.getUser_id());
			cartEntity.setCreated_on(new Date());
		}
		cr.save(cartEntity);
	}


	@Transactional
	public void updateProductQuantity(PostCartDTO postCartDTO) throws CustomException {
		// Cek Product ada atau tidak di db
		Optional<CartEntity> optCart = cr.getCartByUserIdAndProductIdAndPsId(postCartDTO.getProduct_id(), postCartDTO.getProduct_size_id(), postCartDTO.getUser_id());
		if (optCart.isEmpty()){
			throw new CustomException(435, "Product with ID " + postCartDTO.getProduct_id() + " and size " + postCartDTO.getProduct_size_id() + " Not Found in cart");
		}
		CartEntity cartEntity;
		cartEntity = optCart.get();

		// available quantity
		Integer quantityProductInStock = pr.getQuantityProductByProductIdAndPsId(
				postCartDTO.getProduct_id(),
				postCartDTO.getProduct_size_id()
		);
		//		System.out.println("Quantity product remaining = " + quantityProductInStock);

		// quantity product in cart user
		Integer quantityInCart = cartEntity.getQuantity();

		if (quantityInCart == null) {
			quantityInCart = 0;
		}
		//		System.out.println("Quantity in cart for this product and size = " + quantityInCart);

		if (postCartDTO.getQuantity() <= 0){
			throw new CustomException(423, "Quantity must be greater than 0");
		}

		if (postCartDTO.getQuantity() + quantityInCart > quantityProductInStock){
			throw new CustomException(424, "Quantity not enough!!, Product Id " + postCartDTO.getProduct_id() + " with Size " +
					postCartDTO.getProduct_size_id() + " only has " + (quantityProductInStock - quantityInCart) + " remaining");
		}
		cartEntity.setQuantity(postCartDTO.getQuantity());
		cartEntity.setTotal_price(cartEntity.getProduct_price() * postCartDTO.getQuantity());
		cartEntity.setModified_by(postCartDTO.getUser_id());
		cartEntity.setModified_on(new Date());
		cr.save(cartEntity);
	}

	@Transactional
	public void deleteProductInCart(PostCartDTO postCartDTO) throws CustomException {
		Optional<CartEntity> optCart = cr.getCartByUserIdAndProductIdAndPsId(postCartDTO.getProduct_id(), postCartDTO.getProduct_size_id(), postCartDTO.getUser_id());
		if (optCart.isEmpty()){
			throw new CustomException(435, "Product with ID " + postCartDTO.getProduct_id() + " and size " + postCartDTO.getProduct_size_id() + " Not Found in cart");
		}
		CartEntity cartEntity;
		cartEntity = optCart.get();
		cartEntity.setIs_delete(true);
		cartEntity.setDeleted_by(postCartDTO.getUser_id());
		cartEntity.setDeleted_on(new Date());
		cr.delete(cartEntity);
	}
}
