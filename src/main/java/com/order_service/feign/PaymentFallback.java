package com.order_service.feign;

public class PaymentFallback implements PaymentFeignClient {
    @Override
    public String processPayment(String orderId) {
        return "Payment Service is down, please try again later";
    }
}
