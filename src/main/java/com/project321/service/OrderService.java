package com.project321.service;

import java.time.LocalDate;
import java.util.List;

import com.project321.exception.CustomerNotFoundException;
import com.project321.exception.LoginException;
import com.project321.exception.OrderException;
import com.project321.models.Customer;
import com.project321.models.Order;
import com.project321.models.OrderDTO;

public interface OrderService {
	public Order saveOrder(OrderDTO odto,String token) throws LoginException, OrderException;
	
	public Order getOrderByOrderId(Integer OrderId) throws OrderException;
	
	public List<Order> getAllOrders() throws OrderException;
	
	public Order cancelOrderByOrderId(Integer OrderId,String token) throws OrderException;
	
	public Order updateOrderByOrder(OrderDTO order,Integer OrderId,String token) throws OrderException,LoginException;
	
	public List<Order> getAllOrdersByDate(LocalDate date) throws OrderException;

	public Customer getCustomerByOrderid(Integer orderId) throws OrderException;
	

	

	
}
