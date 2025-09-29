package com.order_service.service;

import com.order_service.feign.PaymentFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final PaymentFeignClient paymentFeignClient;

    public OrderService(PaymentFeignClient paymentFeignClient) {
        this.paymentFeignClient = paymentFeignClient;
    }

    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    public String placeOrder(String orderId) {
        // Agar Payment service down → FeignException throw hoga → fallback call
        return paymentFeignClient.processPayment(orderId);
    }

    // Fallback method must match original parameters + Throwable
    public String paymentFallback(String orderId, Throwable t) {
        System.out.println("Fallback called due to: " + t.getMessage());
        return "Payment Service is down, please try again later";
    }
}
