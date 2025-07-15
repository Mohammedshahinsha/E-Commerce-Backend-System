package com.project321.service;

import java.util.List;

import com.project321.controller.ProductNotFound;
import com.project321.exception.CartItemNotFound;
import com.project321.models.Cart;
import com.project321.models.CartDTO;
import com.project321.models.CartItem;




public interface CartService {
	
	public Cart addProductToCart(CartDTO cart, String token) throws CartItemNotFound;
	public Cart getCartProduct(String token);
	public Cart removeProductFromCart(CartDTO cartDto,String token) throws ProductNotFound;

	
	public Cart clearCart(String token);
	
}
