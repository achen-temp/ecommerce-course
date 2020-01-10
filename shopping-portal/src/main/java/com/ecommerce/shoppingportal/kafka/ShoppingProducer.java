package com.ecommerce.shoppingportal.kafka;

import com.ecommerce.shoppingportal.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShoppingProducer {

    private final KafkaTemplate kafkaTemplate;

    private static final String PRODUCT_TOPIC = "cwymk76o-inventory";
    private static final String ACCOUNT_TOPIC = "cwymk76o-inventory";

    @Autowired
    public ShoppingProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void updateProductInventory(String order) {
        kafkaTemplate.send(PRODUCT_TOPIC, order);
        System.out.println("order sent to product service");
    }

    public void updateAccountOrderHistory(String order) {
        kafkaTemplate.send(ACCOUNT_TOPIC, order);
        System.out.println("order sent to account service");
    }

//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<>()
//    }
}
