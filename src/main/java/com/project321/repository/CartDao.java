package com.project321.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project321.models.Cart;
import com.project321.models.Product;

@Repository
public interface CartDao extends JpaRepository<Cart,Integer> {



}
