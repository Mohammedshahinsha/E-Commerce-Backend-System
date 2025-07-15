package com.project321.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project321.models.CartItem;

public interface CartItemDao extends JpaRepository<CartItem, Integer>{

}
