package com.scentheartsstudio.scentheartsstudio.Services;

import com.scentheartsstudio.scentheartsstudio.DTO.InterCartDTO;
import com.scentheartsstudio.scentheartsstudio.DTO.PostCartDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.CartEntity;
import com.scentheartsstudio.scentheartsstudio.Entities.ProductEntity;
import com.scentheartsstudio.scentheartsstudio.Entities.UserEntity;
import com.scentheartsstudio.scentheartsstudio.Repositories.CartRepository;
import com.scentheartsstudio.scentheartsstudio.Repositories.ProductRepository;
import com.scentheartsstudio.scentheartsstudio.Repositories.UserRepository;
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


	public List<InterCartDTO> getAllCartByUserId(Long user_id) {
		return cr.getAllCartByUserId(user_id);
	}

	@Transactional
	public void addProductToCart(PostCartDTO postCartDTO) throws CustomException {
		// Cek Product ada atau tidak di db
		Optional<ProductEntity> optProduct = pr.findById(postCartDTO.getProduct_id());
		if (optProduct.isEmpty()) {
			throw new CustomException( 420, "Product with ID " + postCartDTO.getProduct_id() + " Not Found");
		}
		ProductEntity productEntity = optProduct.get();

		Double price = productEntity.getDiscount_price();
		Integer newQuantity = postCartDTO.getQuantity();
		Integer quantity = productEntity.getQuantity();
		Double totalPrice = price * newQuantity;
		// check quantity
		if (newQuantity <= 0){
			throw new CustomException(423, "Quantity must be greater than 0");
		}
		if (newQuantity > quantity){
			throw new CustomException(424, "Quantity not enough!!, Quantity only has " + quantity);
		}

		// is product in cart
		Optional<CartEntity> optCart = cr.getCartByUserIdAndProductId(postCartDTO.getProduct_id(), postCartDTO.getUser_id());
		CartEntity cartEntity;
		if (optCart.isPresent()) {
			cartEntity = optCart.get();
			cartEntity.setQuantity(cartEntity.getQuantity() + newQuantity);
			cartEntity.setTotal_price(cartEntity.getTotal_price() + totalPrice);
		} else {
			cartEntity = new CartEntity();
			cartEntity.setUser_id(postCartDTO.getUser_id());
			cartEntity.setProduct_id(postCartDTO.getProduct_id());
			cartEntity.setProduct_size_id(postCartDTO.getProduct_size_id());
			cartEntity.setQuantity(newQuantity);
			cartEntity.setTotal_price(totalPrice);
			cartEntity.setCreated_by(1L);
			cartEntity.setCreated_on(new Date());
		}
		cr.save(cartEntity);
	}


	@Transactional
	public void updateProductQuantity(PostCartDTO postCartDTO) throws CustomException {
		// Cek Product ada atau tidak di db
		Optional<ProductEntity> optProduct = pr.findById(postCartDTO.getProduct_id());
		if (optProduct.isEmpty()) {
			throw new CustomException( 420, "Product with ID " + postCartDTO.getProduct_id() + " Not Found");
		}
		ProductEntity productEntity = optProduct.get();

		//cek user exist
		Optional<UserEntity> optUser = ur.findById(postCartDTO.getUser_id());
		if (optUser.isEmpty()) {
			throw new CustomException( 420, "User with ID " + postCartDTO.getUser_id() + " Not Found");
		}

		Double price = productEntity.getDiscount_price();
		Integer newQuantity = postCartDTO.getQuantity();
		Integer quantity = productEntity.getQuantity();
		Double totalPrice = price * newQuantity;
		// check quantity
		if (newQuantity <= 0){
			throw new CustomException(423, "Quantity must be greater than 0");
		}
		if (newQuantity > quantity){
			throw new CustomException(424, "Quantity not enough!!, Quantity only has " + quantity);
		}

		// is product in cart
		Optional<CartEntity> optCart = cr.getCartByUserIdAndProductId(postCartDTO.getProduct_id(), postCartDTO.getUser_id());
		CartEntity cartEntity;
		if (optCart.isPresent()){
			cartEntity = optCart.get();
			cartEntity.setQuantity(newQuantity);
			// there is bug i think
			cartEntity.setProduct_size_id(postCartDTO.getProduct_size_id());
			cartEntity.setTotal_price(totalPrice);
			cr.save(cartEntity);
		}
	}

	@Transactional
	public void deleteProductInCart(PostCartDTO postCartDTO) throws CustomException {
		Optional<CartEntity> optCart = cr.getCartByUserIdAndProductId(postCartDTO.getProduct_id(), postCartDTO.getUser_id());
		if (optCart.isEmpty()){
			throw new CustomException(435, "Product with ID " + postCartDTO.getProduct_id() + " Not Found");
		}

		CartEntity cartEntity;
		cartEntity = optCart.get();
		cartEntity.setIs_delete(true);
		cartEntity.setDeleted_by(1L);
		cartEntity.setDeleted_on(new Date());
		cr.delete(cartEntity);
	}
}
