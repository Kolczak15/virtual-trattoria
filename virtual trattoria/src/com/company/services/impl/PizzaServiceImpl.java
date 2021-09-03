package com.company.services.impl;

import com.company.entities.Pizza;
import com.company.enums.Ingredients;
import com.company.services.PizzaService;

import java.util.List;

public class PizzaServiceImpl implements PizzaService {

    @Override
    public Pizza createPizza(String orderedPizza, List<Ingredients> ingredientsOnPizza) {
        if (orderedPizza == null) {
            throw new IllegalArgumentException("orderedPizza is null");
        }
        if (ingredientsOnPizza == null) {
            throw new IllegalArgumentException("ingredientsOnPizza is null");
        }
        return new Pizza(orderedPizza, ingredientsOnPizza);
    }
}
