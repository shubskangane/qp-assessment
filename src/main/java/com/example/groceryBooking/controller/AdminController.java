package com.example.groceryBooking.controller;

import com.example.groceryBooking.model.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.groceryBooking.service.GroceryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    GroceryService groceryService;

//   - Add new grocery items to the system
    @PostMapping("/addGrocery")
    public ResponseEntity<GroceryItem> addGrocery(@RequestBody GroceryItem item){
        return ResponseEntity.ok(groceryService.addNewGrocery(item));
    }

//   - View existing grocery items
    @GetMapping("/getAllGroceries")
    public List<GroceryItem> getAllGroceries(){
        return groceryService.getAllGrocery();
    }

    //   - Remove grocery items from the system
    @DeleteMapping("/deleteGrocery/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable int id) {
        groceryService.deleteGroceryItem(id);
        return ResponseEntity.noContent().build();
    }

//    - Update details (e.g., name, price) of existing grocery items
//   - Manage inventory levels of grocery items
    @PutMapping("/updateGrocery/{id}")
    public GroceryItem updateItem(@PathVariable int id, @RequestBody GroceryItem item) {
        Optional<GroceryItem> updatedItem = groceryService.updateGroceryItem(id, item);
        return updatedItem.get();

    }

}
