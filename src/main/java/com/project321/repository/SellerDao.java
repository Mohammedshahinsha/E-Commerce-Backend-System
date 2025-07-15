package com.project321.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project321.models.Seller;

public interface SellerDao extends JpaRepository<Seller, Integer> {
	
	Optional<Seller> findByMobile(String mobile);
	
}
