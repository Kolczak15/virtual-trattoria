package com.company.services;

import com.company.entities.Pizza;
import com.company.enums.Ingredients;

import java.util.List;

public interface PizzaService {
    Pizza createPizza(String orderedPizza, List<Ingredients> ingredientsOnPizza);
}
