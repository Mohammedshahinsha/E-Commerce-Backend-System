package com.project321.service;

import com.project321.models.CartDTO;
import com.project321.models.CartItem;

public interface CartItemService {
	
	public CartItem createItemforCart(CartDTO cartdto);
	
}
