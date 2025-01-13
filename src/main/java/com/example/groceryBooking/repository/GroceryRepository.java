package com.example.groceryBooking.repository;

import com.example.groceryBooking.model.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryRepository extends JpaRepository<GroceryItem, Integer> {
    List<GroceryItem> findByStockQuantityGreaterThan(int quantity);
}
