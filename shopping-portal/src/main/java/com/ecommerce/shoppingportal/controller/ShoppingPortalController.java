package com.ecommerce.shoppingportal.controller;

import com.ecommerce.shoppingportal.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShoppingPortalController {

    @Autowired
    ShoppingService service;

    @GetMapping({"/", "/greeting", "/welcome"})
    public String welcome(){
        return "Welcome to e-commerce customer shopping portal";
    }

    @RequestMapping(value = "/recommend/{category}", method = RequestMethod.GET)
    public String recommendProductByCategory(@PathVariable String category) {
        return service.recommendProduct(category);
    }

    @GetMapping("/accountInfo")
    public String getAccountInfoWithServer() {
        return service.getAccount();
    }

    @GetMapping("order/{name}/{amount}")
    public void orderProduct(@PathVariable String name,
                             @PathVariable int amount) {
        service.orderProduct(name, amount);
    }

}
