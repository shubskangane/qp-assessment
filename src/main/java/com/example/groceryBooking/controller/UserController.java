package com.example.groceryBooking.controller;

import com.example.groceryBooking.model.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.groceryBooking.repository.GroceryRepository;
import com.example.groceryBooking.service.GroceryService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private GroceryService groceryService;

    @Autowired
    private GroceryRepository groceryRepository;

//       - View the list of available grocery items i.e. quantity is greater than 0;
    @GetMapping("/availableItems")
    public List<GroceryItem> viewItems() {
        return groceryService.getAllAvailableGroceryItems();
    }

    //   - Ability to book multiple grocery items in a single order
    @PostMapping("/order")
    public String placeOrder(@RequestBody Map<Integer,Integer> mapOfItemIDvsStockQuantity ) {
        groceryService.bookMultipleGroceries(mapOfItemIDvsStockQuantity);
        List<String> itemNames = groceryRepository.findAllById(mapOfItemIDvsStockQuantity.keySet()).stream().map(GroceryItem::getName).toList();
        return "Order placed for items: " + itemNames;
    }
}
