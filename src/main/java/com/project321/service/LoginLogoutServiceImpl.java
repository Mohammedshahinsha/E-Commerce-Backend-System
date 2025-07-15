package com.project321.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project321.exception.CustomerNotFoundException;
import com.project321.exception.LoginException;
import com.project321.exception.SellerNotFoundException;
import com.project321.models.Customer;
import com.project321.models.CustomerDTO;
import com.project321.models.Seller;
import com.project321.models.SellerDTO;
import com.project321.models.SessionDTO;
import com.project321.models.UserSession;
import com.project321.repository.CustomerDao;
import com.project321.repository.SellerDao;
import com.project321.repository.SessionDao;

@Service
public class LoginLogoutServiceImpl implements LoginLogoutService{

	
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private SellerDao sellerDao;

 
	


	@Override
	public UserSession loginCustomer(CustomerDTO loginCustomer) {
		
		Optional<Customer> res = customerDao.findByMobileNo(loginCustomer.getMobileId());
		
		if(!res.isPresent())
			throw new CustomerNotFoundException("Customer record does not exist with given mobile number");
		
		Customer existingCustomer = res.get();
		
		Optional<UserSession> opt = sessionDao.findByUserId(existingCustomer.getCustomerId());
		
		if(opt.isPresent()) {
			
			UserSession user = opt.get();
			
			if(user.getSessionEndTime().isBefore(LocalDateTime.now())) {
				sessionDao.delete(user);	
			}
			else
				throw new LoginException("User already logged in");
			
		}
		
		
		if(existingCustomer.getPassword().equals(loginCustomer.getPassword())) {
		
			UserSession newSession = new UserSession();
			
			newSession.setUserId(existingCustomer.getCustomerId());
			newSession.setUserType("customer");
			newSession.setSessionStartTime(LocalDateTime.now());
			newSession.setSessionEndTime(LocalDateTime.now().plusHours(1));
			
			UUID uuid = UUID.randomUUID();
			String token = "customer_" + uuid.toString().split("-")[0];
			
			newSession.setToken(token);
			
			return sessionDao.save(newSession);
		}
		else {
			throw new LoginException("Password Incorrect. Try again.");
		}
	}

	

	
	@Override
	public SessionDTO logoutCustomer(SessionDTO sessionToken) {
		
		String token = sessionToken.getToken();
		
		checkTokenStatus(token);
		
		Optional<UserSession> opt = sessionDao.findByToken(token);
		
		if(!opt.isPresent())
			throw new LoginException("User not logged in. Invalid session token. Login Again.");
		
		UserSession session = opt.get();
		
		sessionDao.delete(session);
		
		sessionToken.setMessage("Logged out sucessfully.");
		
		return sessionToken;
	}
	
	
	

	
	
	@Override
	public void checkTokenStatus(String token) {
		
		Optional<UserSession> opt = sessionDao.findByToken(token);
		
		if(opt.isPresent()) {
			UserSession session = opt.get();
			LocalDateTime endTime = session.getSessionEndTime();
			boolean flag = false;
			if(endTime.isBefore(LocalDateTime.now())) {
				sessionDao.delete(session);
				flag = true;
			}
			
			deleteExpiredTokens();
			if(flag)
				throw new LoginException("Session expired. Login Again");
		}
		else {
			throw new LoginException("User not logged in. Invalid session token. Please login first.");
		}
		
	}

	

	
	@Override
	public UserSession loginSeller(SellerDTO seller) {
		
		Optional<Seller> res = sellerDao.findByMobile(seller.getMobile());
		
		if(!res.isPresent())
			throw new SellerNotFoundException("Seller record does not exist with given mobile number");
		
		Seller existingSeller = res.get();
		
		Optional<UserSession> opt = sessionDao.findByUserId(existingSeller.getSellerId());
		
		if(opt.isPresent()) {
			
			UserSession user = opt.get();
			
			if(user.getSessionEndTime().isBefore(LocalDateTime.now())) {
				sessionDao.delete(user);	
			}
			else
				throw new LoginException("User already logged in");
			
		}
		
		
		if(existingSeller.getPassword().equals(seller.getPassword())) {
		
			UserSession newSession = new UserSession();
			
			newSession.setUserId(existingSeller.getSellerId());
			newSession.setUserType("seller");
			newSession.setSessionStartTime(LocalDateTime.now());
			newSession.setSessionEndTime(LocalDateTime.now().plusHours(1));
			
			UUID uuid = UUID.randomUUID();
			String token = "seller_" + uuid.toString().split("-")[0];
			
			newSession.setToken(token);
			
			return sessionDao.save(newSession);
		}
		else {
			throw new LoginException("Password Incorrect. Try again.");
		}
	}

	

	
	@Override
	public SessionDTO logoutSeller(SessionDTO session) {
		
		String token = session.getToken();
		
		checkTokenStatus(token);
		
		Optional<UserSession> opt = sessionDao.findByToken(token);
		
		if(!opt.isPresent())
			throw new LoginException("User not logged in. Invalid session token. Login Again.");
		
		UserSession user = opt.get();
		
		sessionDao.delete(user);
		
		session.setMessage("Logged out sucessfully.");
		
		return session;
	}
	
	

	
	@Override
	public void deleteExpiredTokens() {
		
		System.out.println("Inside delete tokens");
		
		List<UserSession> users = sessionDao.findAll();
		
		System.out.println(users);
		
		if(users.size() > 0) {
			for(UserSession user:users) {
				System.out.println(user.getUserId());
				LocalDateTime endTime = user.getSessionEndTime();
				if(endTime.isBefore(LocalDateTime.now())) {
					System.out.println(user.getUserId());
					sessionDao.delete(user);
				}
			}
		}
	}
	
}
