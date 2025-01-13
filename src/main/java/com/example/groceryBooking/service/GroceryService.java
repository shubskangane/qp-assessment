package com.example.groceryBooking.service;

import com.example.groceryBooking.model.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.groceryBooking.repository.GroceryRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GroceryService {

    @Autowired
    GroceryRepository groceryRepository;
    public GroceryItem addNewGrocery(GroceryItem item) {
        return groceryRepository.save(item);
    }

    public List<GroceryItem> getAllGrocery() {
        return groceryRepository.findAll();
    }

    public void deleteGroceryItem(Integer id) {
        groceryRepository.deleteById(id);
    }

    public Optional<GroceryItem> updateGroceryItem(int id, GroceryItem item) {
        Optional<GroceryItem> dbItem = groceryRepository.findById(id);
        dbItem.get().setName(item.getName());
        dbItem.get().setPrice(item.getPrice());
        dbItem.get().setStockQuantity(item.getStockQuantity());

        return dbItem;
    }

    public List<GroceryItem> getAllAvailableGroceryItems() {
        return groceryRepository.findByStockQuantityGreaterThan(0);
    }

    public void bookMultipleGroceries(Map<Integer, Integer> mapOfItemIDvsStockQuantity) {
        mapOfItemIDvsStockQuantity.forEach((itemId, stockQuantity) -> {
            Optional<GroceryItem> optionalItem = groceryRepository.findById(itemId);
            optionalItem.ifPresent(item -> {
                item.setStockQuantity(item.getStockQuantity() - stockQuantity);
                groceryRepository.save(item);
            });
        });
    }
}
