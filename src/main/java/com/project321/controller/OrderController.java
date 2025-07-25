package com.project321.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project321.models.Customer;
import com.project321.models.Order;
import com.project321.models.OrderDTO;
import com.project321.repository.OrderDao;
import com.project321.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	private OrderDao oDao;
	
	@Autowired
	private OrderService oService;
	
	@PostMapping("/order/place")
	public ResponseEntity<Order> addTheNewOrder(@Valid @RequestBody OrderDTO odto,@RequestHeader("token") String token){
		
		Order savedorder = oService.saveOrder(odto,token);
		return new ResponseEntity<Order>(savedorder,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/orders")
	public List<Order> getAllOrders(){
		
		
		List<Order> listOfAllOrders = oService.getAllOrders();
		return listOfAllOrders;
		
	}
	
	@GetMapping("/orders/{orderId}")
	public Order getOrdersByOrderId(@PathVariable("orderId") Integer orderId) {
		
		return oService.getOrderByOrderId(orderId);
		
	}
	
	@DeleteMapping("/orders/{orderId}")
	public Order cancelTheOrderByOrderId(@PathVariable("orderId") Integer orderId,@RequestHeader("token") String token){
		
		return oService.cancelOrderByOrderId(orderId,token);
	}
	
	@PutMapping("/orders/{orderId}")
	public ResponseEntity<Order> updateOrderByOrder(@Valid @RequestBody OrderDTO orderdto, @PathVariable("orderId") Integer orderId,@RequestHeader("token") String token){
		
		Order updatedOrder= oService.updateOrderByOrder(orderdto,orderId,token);
		
		return new ResponseEntity<Order>(updatedOrder,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/orders/by/date")
	public List<Order> getOrdersByDate(@RequestParam("date") String date){
		
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate ld=LocalDate.parse(date,dtf);
		return oService.getAllOrdersByDate(ld);
	}
	
	@GetMapping("/customer/{orderId}")
	public Customer getCustomerDetailsByOrderId(@PathVariable("orderId") Integer orderId) {
		return oService.getCustomerByOrderid(orderId);
	}

}
