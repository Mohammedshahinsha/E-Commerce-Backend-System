package com.project321.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project321.exception.ProductNotFoundException;
import com.project321.models.CartDTO;
import com.project321.models.CartItem;
import com.project321.models.Product;
import com.project321.models.ProductStatus;
import com.project321.repository.ProductDao;

@Service
public class CartItemServiceImpl implements CartItemService{

	@Autowired
	ProductDao productDao;

	@Override
	public CartItem createItemforCart(CartDTO cartdto) {
		
		Product existingProduct = productDao.findById(cartdto.getProductId()).orElseThrow( () -> new ProductNotFoundException("Product Not found"));
		
		if(existingProduct.getStatus().equals(ProductStatus.OUTOFSTOCK) || existingProduct.getQuantity() == 0) {
			throw new ProductNotFoundException("Product OUT OF STOCK");
		}
		
		CartItem newItem = new CartItem();
		
		newItem.setCartItemQuantity(1);
		
		newItem.setCartProduct(existingProduct);
		
		return newItem;
	}
	























}
