package com.company.services;

import com.company.entities.Order;
import com.company.entities.Pizza;
import com.company.enums.Ingredients;

import java.io.IOException;
import java.util.List;

public interface OrderService {
    boolean isPizzaInMenu(String pizza);

    boolean removeIngredient(List<Ingredients> ingredientsOnPizza, String answer);

    boolean addIngredient(List<Ingredients> ingredientsOnPizza, String answer);

    Order createOrder(String userName);

    void addPizza(Order order, Pizza pizza);

    boolean saveOrder(Order order) throws IOException;

    Order readSavedOrder() throws IOException, ClassNotFoundException;
}
