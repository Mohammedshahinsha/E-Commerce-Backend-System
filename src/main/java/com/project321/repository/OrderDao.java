package com.project321.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project321.exception.CustomerNotFoundException;
import com.project321.exception.OrderException;
import com.project321.models.CartItem;
import com.project321.models.Customer;
import com.project321.models.Order;
import com.project321.models.Product;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {
	public List<Order> findByDate(LocalDate date);
	


	
	@Query("select c from Customer c where c.customerId = customerId")
	public Customer getCustomerByOrderid(@Param("customerId") Integer customerId);
	

	





	
}
