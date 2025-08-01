package com.project321.scheduler;

import com.project321.repository.AddressDao;
import com.project321.repository.CartDao;
import com.project321.repository.CartItemDao;
import com.project321.repository.CustomerDao;
import com.project321.repository.OrderDao;
import com.project321.repository.ProductDao;
import com.project321.repository.SellerDao;
import com.project321.repository.SessionDao;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataCleanupScheduler {

    private final AddressDao addressDao;
    private final CartDao cartDao;
    private final CartItemDao cartItemDao;
    private final CustomerDao customerDao;
    private final OrderDao orderDao;
    private final ProductDao productDao;
    private final SellerDao sellerDao;
    private final SessionDao sessionDao;

    public DataCleanupScheduler(
        AddressDao addressDao,
        CartDao cartDao,
        CartItemDao cartItemDao,
        CustomerDao customerDao,
        OrderDao orderDao,
        ProductDao productDao,
        SellerDao sellerDao,
        SessionDao sessionDao
    ) {
        this.addressDao = addressDao;
        this.cartDao = cartDao;
        this.cartItemDao = cartItemDao;
        this.customerDao = customerDao;
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.sellerDao = sellerDao;
        this.sessionDao = sessionDao;
    }

    @Transactional
    @Scheduled(fixedRate = 3600000) // every 1 hour
    public void clearTestData() {
        System.out.println("🔄 DAO Cleanup Started...");

        cartItemDao.deleteAll();
        cartDao.deleteAll();
        orderDao.deleteAll();
        addressDao.deleteAll();
        sessionDao.deleteAll();
        productDao.deleteAll();
        customerDao.deleteAll();
        sellerDao.deleteAll();

        System.out.println("✅ DAO Cleanup Completed.");
    }
}