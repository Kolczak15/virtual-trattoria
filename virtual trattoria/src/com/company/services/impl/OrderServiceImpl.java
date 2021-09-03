package com.company.services.impl;

import com.company.entities.Order;
import com.company.entities.Pizza;
import com.company.enums.Ingredients;
import com.company.enums.Pizzas;
import com.company.repositories.OrderRepository;
import com.company.repositories.impl.OrderRepositoryImpl;
import com.company.services.OrderService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final Logger logger;

    public OrderServiceImpl() {
        orderRepository = new OrderRepositoryImpl();
        logger = Logger.getLogger(getClass().getName());
    }

    @Override
    public boolean isPizzaInMenu(String orderedPizza) {
        if (orderedPizza == null) {
            throw new IllegalArgumentException("orderedPizza is null");
        }
        for (Pizzas pizza : Pizzas.values()) {
            if (pizza.name().equals(orderedPizza)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeIngredient(List<Ingredients> ingredientsOnPizza, String answer) {
        if (ingredientsOnPizza == null) {
            throw new IllegalArgumentException("ingredientsOnPizza is null");
        }
        if (answer == null) {
            throw new IllegalArgumentException("answer is null");
        }
        for (Ingredients ingr : ingredientsOnPizza) {
            if (ingr.name().equals(answer)) {
                return ingredientsOnPizza.remove(ingr);
            }
        }
        return false;
    }

    @Override
    public boolean addIngredient(List<Ingredients> ingredientsOnPizza, String answer) {
        if (ingredientsOnPizza == null) {
            throw new IllegalArgumentException("ingredientsOnPizza is null");
        }
        if (answer == null) {
            throw new IllegalArgumentException("answer is null");
        }
        for (Ingredients ingr : ingredientsOnPizza) {
            if (ingr.name().equals(answer)) {
                return false;
            }
        }
        for (Ingredients ingr : Ingredients.values()) {
            if (ingr.name().equals(answer)) {
                return ingredientsOnPizza.add(Ingredients.valueOf(answer));
            }
        }
        return false;
    }

    @Override
    public Order createOrder(String userName) {
        if (userName == null) {
            throw new IllegalArgumentException("userName is null");
        }
        return new Order(userName);
    }

    @Override
    public void addPizza(Order order, Pizza pizza) {
        if (order == null) {
            throw new IllegalArgumentException("order is null");
        }
        if (pizza == null) {
            throw new IllegalArgumentException("pizza is null");
        }
        order.getPizzaList().add(pizza);
    }

    @Override
    public boolean saveOrder(Order order) {
        try {
            if (order == null) {
                throw new IllegalArgumentException("order is null");
            }
            return orderRepository.saveOrder(order);
        } catch (IOException e) {
            logger.log(Level.INFO, "{} when saving order.", e.getMessage());
            return false;
        }
    }

    @Override
    public Order readSavedOrder() {
        try {
            return orderRepository.readOrder();
        } catch (Exception e) {
            logger.log(Level.INFO, "{} when reading order.", e.getMessage());
            return null;
        }
    }
}
