package com.company.entities;

import com.company.enums.Ingredients;
import com.company.enums.Pizzas;

import java.io.Serializable;
import java.util.List;

public class Pizza implements Serializable {
    private List<Ingredients> ingredients;
    private final Pizzas name;

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public Pizzas getName() {
        return name;
    }

    public Pizza(Pizzas orderedPizza, List<Ingredients> ingr) {
        this.name = orderedPizza;
        this.ingredients = ingr;
    }

    public Pizza(String orderedPizza, List<Ingredients> ingr) {
        this.name = Pizzas.valueOf(orderedPizza);
        this.ingredients = ingr;
    }
}
