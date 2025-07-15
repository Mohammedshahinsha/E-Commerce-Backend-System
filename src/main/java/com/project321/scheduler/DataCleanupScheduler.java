package com.project321.scheduler;

import com.project321.repository.CartRepository;
import com.project321.repository.OrderRepository;
import com.project321.repository.ProductRepository;
import com.project321.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataCleanupScheduler {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public DataCleanupScheduler(
        UserRepository userRepository,
        CartRepository cartRepository,
        OrderRepository orderRepository,
        ProductRepository productRepository
    ) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    @Scheduled(fixedRate = 3600000) // runs every 1 hour
    

        cartRepository.deleteAll();
        orderRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();  

    }
}