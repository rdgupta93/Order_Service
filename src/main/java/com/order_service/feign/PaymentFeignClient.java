package com.order_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "payment-service",
        url = "http://localhost:8081",
        fallback = PaymentFallback.class
)
public interface PaymentFeignClient {
    @GetMapping("/pay/{orderId}")
    String processPayment(@PathVariable("orderId") String orderId);
}
