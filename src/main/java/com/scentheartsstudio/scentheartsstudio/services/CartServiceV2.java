//package com.scentheartsstudio.scentheartsstudio.services;
//
//import com.scentheartsstudio.scentheartsstudio.dto.InterCartDTO;
//import com.scentheartsstudio.scentheartsstudio.dto.PostCartDTO;
//import com.scentheartsstudio.scentheartsstudio.entities.CartEntity;
//import com.scentheartsstudio.scentheartsstudio.entities.ProductEntity;
//import com.scentheartsstudio.scentheartsstudio.entities.UserEntity;
//import com.scentheartsstudio.scentheartsstudio.repositories.CartRepository;
//import com.scentheartsstudio.scentheartsstudio.repositories.ProductRepository;
//import com.scentheartsstudio.scentheartsstudio.repositories.ProductSizeRepository;
//import com.scentheartsstudio.scentheartsstudio.repositories.UserRepository;
//import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CartServiceV2 {
//
//	@Autowired
//	private CartRepository cr;
//
//	@Autowired
//	private ProductRepository pr;
//
//	@Autowired
//	private UserRepository ur;
//
//	@Autowired
//	private ProductSizeRepository psr;
//
//	public List<InterCartDTO> getAllCartByUserId(Long user_id) {
//		return cr.getAllCartByUserId(user_id);
//	}
//
//	@Transactional
//	public void addProductToCart(PostCartDTO postCartDTO) throws CustomException {
//		// Cek Product ada atau tidak di db
//		Optional<ProductEntity> optProduct = pr.findById(postCartDTO.getProduct_id());
//		if (optProduct.isEmpty()) {
//			throw new CustomException( 420, "Product with ID " + postCartDTO.getProduct_id() + " Not Found");
//		}
//		ProductEntity productEntity = optProduct.get();
//
//		Boolean isProductSizeIdExists = psr.isProductSizeIdExists(postCartDTO.getProduct_size_id());
//		if (!isProductSizeIdExists){
//			throw new CustomException(421, "Product Size Id " + postCartDTO.getProduct_size_id() + " not found!!");
//		}
//
//		Integer quantityProductInStock = pr.getQuantityProductByProductIdAndPsId(
//				postCartDTO.getProduct_id(),
//				postCartDTO.getProduct_size_id()
//		);
////		System.out.println("Quantity product remaining = " + quantityProductInStock);
//
//		// Ambil kuantitas produk yang sudah ada di keranjang pengguna
//		Integer quantityInCart = cr.getTotalQuantityInCartByUserIdAndProductIdAndProductSizeId(
//				postCartDTO.getProduct_id(),
//				postCartDTO.getUser_id(),
//				postCartDTO.getProduct_size_id()
//		);
//
//		if (quantityInCart == null) {
//			quantityInCart = 0;
//		}
////		System.out.println("Quantity in cart for this product and size = " + quantityInCart);
//
//		Integer newQuantity = postCartDTO.getQuantity();
////		System.out.println("Quantity user request " + newQuantity);
//		Double price = productEntity.getPrice();
//		Double totalPrice = price * newQuantity;
//
//		if (newQuantity <= 0){
//			throw new CustomException(423, "Quantity must be greater than 0");
//		}
//
//		if (newQuantity + quantityInCart > quantityProductInStock){
//			throw new CustomException(424, "Quantity not enough!!, Product Id " + postCartDTO.getProduct_id() + " with Size " +
//					postCartDTO.getProduct_size_id() + " only has " + (quantityProductInStock - quantityInCart) + " remaining");
//		}
//
//		// is product in cart
//		Optional<CartEntity> optCart = cr.getCartByUserIdAndProductId(postCartDTO.getProduct_id(), postCartDTO.getUser_id());
//		CartEntity cartEntity;
//		if (optCart.isPresent()) {
//			cartEntity = optCart.get();
//			cartEntity.setQuantity(cartEntity.getQuantity() + newQuantity);
//			cartEntity.setTotal_price(cartEntity.getTotal_price() + totalPrice);
//		} else {
//			cartEntity = new CartEntity();
//			cartEntity.setUser_id(postCartDTO.getUser_id());
//			cartEntity.setProduct_id(postCartDTO.getProduct_id());
//			cartEntity.setProduct_size_id(postCartDTO.getProduct_size_id());
//			cartEntity.setQuantity(newQuantity);
//			cartEntity.setTotal_price(totalPrice);
//			cartEntity.setCreated_by(1L);
//			cartEntity.setCreated_on(new Date());
//		}
//		cr.save(cartEntity);
//	}
//
//
//	@Transactional
//	public void updateProductQuantity(PostCartDTO postCartDTO) throws CustomException {
//		// Cek Product ada atau tidak di db
//		Optional<ProductEntity> optProduct = pr.findById(postCartDTO.getProduct_id());
//		if (optProduct.isEmpty()) {
//			throw new CustomException( 420, "Product with ID " + postCartDTO.getProduct_id() + " Not Found");
//		}
//		ProductEntity productEntity = optProduct.get();
//
//		Boolean isProductSizeIdExists = psr.isProductSizeIdExists(postCartDTO.getProduct_size_id());
//		if (!isProductSizeIdExists){
//			throw new CustomException(421, "Product Size Id " + postCartDTO.getProduct_size_id() + " not found!!");
//		}
//
//		//cek user exist
//		Optional<UserEntity> optUser = ur.findById(postCartDTO.getUser_id());
//		if (optUser.isEmpty()) {
//			throw new CustomException( 422, "User with ID " + postCartDTO.getUser_id() + " Not Found");
//		}
//		Integer quantityProductInStock = pr.getQuantityProductByProductIdAndPsId(
//				postCartDTO.getProduct_id(),
//				postCartDTO.getProduct_size_id()
//		);
//		//		System.out.println("Quantity product remaining = " + quantityProductInStock);
//
//		// Ambil kuantitas produk yang sudah ada di keranjang pengguna
//		Integer quantityInCart = cr.getTotalQuantityInCartByUserIdAndProductIdAndProductSizeId(
//				postCartDTO.getProduct_id(),
//				postCartDTO.getUser_id(),
//				postCartDTO.getProduct_size_id()
//		);
//
//		if (quantityInCart == null) {
//			quantityInCart = 0;
//		}
//		//		System.out.println("Quantity in cart for this product and size = " + quantityInCart);
//
//		Integer newQuantity = postCartDTO.getQuantity();
//		//		System.out.println("Quantity user request " + newQuantity);
//		Double price = productEntity.getPrice();
//		Double totalPrice = price * newQuantity;
//
//		if (newQuantity <= 0){
//			throw new CustomException(423, "Quantity must be greater than 0");
//		}
//
//		if (newQuantity + quantityInCart > quantityProductInStock){
//			throw new CustomException(424, "Quantity not enough!!, Product Id " + postCartDTO.getProduct_id() + " with Size " +
//					postCartDTO.getProduct_size_id() + " only has " + (quantityProductInStock - quantityInCart) + " remaining");
//		}
//
//		// is product in cart
//		Optional<CartEntity> optCart = cr.getCartByUserIdAndProductId(postCartDTO.getProduct_id(), postCartDTO.getUser_id());
//		if (optCart.isPresent()) {
//			CartEntity cartEntity = optCart.get();
//			cartEntity.setQuantity(newQuantity);
//			cartEntity.setProduct_size_id(postCartDTO.getProduct_size_id());
//			cartEntity.setTotal_price(productEntity.getPrice() * newQuantity);
//			cr.save(cartEntity);
//		} else {
//			throw new CustomException(425, "Product not found in cart");
//		}
//	}
//
//	@Transactional
//	public void deleteProductInCart(PostCartDTO postCartDTO) throws CustomException {
//		Optional<CartEntity> optCart = cr.getCartByUserIdAndProductId(postCartDTO.getProduct_id(), postCartDTO.getUser_id());
//		if (optCart.isEmpty()){
//			throw new CustomException(435, "Product with ID " + postCartDTO.getProduct_id() + " Not Found");
//		}
//
//		CartEntity cartEntity;
//		cartEntity = optCart.get();
//		cartEntity.setIs_delete(true);
//		cartEntity.setDeleted_by(1L);
//		cartEntity.setDeleted_on(new Date());
//		cr.delete(cartEntity);
//	}
//}
