package com.ecommerce.shoppingportal.service;

import com.ecommerce.shoppingportal.domain.Order;
import com.ecommerce.shoppingportal.kafka.ShoppingProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Random;

@Service
public class ShoppingService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ShoppingProducer producer;

    /**
     * call ProductService rest API and get recommended results
     * @param cat
     * @return
     */

    @HystrixCommand(fallbackMethod = "recommendProduct_fallback")
    public String recommendProduct(String cat){
        //RestTemplate
        //localhost:8082/product/recommend/category
        String url = "http://product-service/product/recommend/" + cat;
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    public String recommendProduct_fallback(String cat) { //variable要和上面一致
        return "there is an error recommending product for category:" + cat;
    }

    //Ribbon
    public String getAccount() {
        String url = "http://account-service/accountInfo";
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * step 1: check if product is available for the given quantity
     * step 2: call account service to reduce the account balance
     * step 3: call product service to reduce the inventory
     * step 4: add product to the account service order history
     * @param name
     * @param amount
     */
    public void orderProduct(String name, int amount) {
        double unitPrice = 0;
        try {
            //step 1
            String url = "http://product-service/price/" + name + "/" + amount;
            unitPrice = restTemplate.getForObject(url, Double.class);
            if (unitPrice == -1.0) {
                return; //not available
            }

            //step 2
            double totalCost = unitPrice * amount;
            String accountUrl = "http://account-service/payment/" + totalCost;
            //ResponseEntity<String> responseEntity = restTemplate.exchange(accountUrl, HttpMethod.PUT, null,String.class);
            restTemplate.put(accountUrl, null);
        }catch (Exception e) {
            System.out.println("error with payment");
            return;
        }

        //call product service to reduce the inventory
        Order order = new Order();
        order.setOrderId(Integer.toString(new Random().nextInt(50)));
        order.setProduceName(name);
        order.setProductAmount(amount);
        order.setProductUnitPrice(unitPrice);
        order.setTotalPrice(unitPrice * amount);
        order.setTaxRate(0.0625);
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));

        //step 3 producer send order to product service and account service
        producer.updateProductInventory(this.convertJsonToString(order));

        //step 4
        producer.updateAccountOrderHistory(this.convertJsonToString(order));
    }

    public String convertJsonToString(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            String result = mapper.writeValueAsString(o);
            return result;
        }catch (Exception e) {}
        return "";
    }
}
