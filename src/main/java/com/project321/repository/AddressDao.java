package com.project321.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project321.models.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer>{

}
