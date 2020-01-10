package com.ecommerce.product.kafka;

import com.ecommerce.product.domain.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {

    private static final String PRODUCT_TOPIC = "cwymk76o-inventory";

    @KafkaListener(topics = PRODUCT_TOPIC)
    public void receive(String payload) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(payload);
            Product product = mapper.readValue(payload, Product.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
