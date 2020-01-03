package com.ecommerce.shoppingportal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingPortalController {

    @GetMapping({"/", "/greeting", "/welcome"})
    public String welcome(){
        return "Welcome to e-commerce customer shopping portal";
    }

}
