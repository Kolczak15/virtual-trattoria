package com.company.services.impl;

import com.company.entities.Pizza;
import com.company.enums.Ingredients;
import com.company.enums.Pizzas;
import com.company.services.PizzaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class PizzaServiceImplTest {
    private PizzaService pizzaService;

    @BeforeEach
    public void initTest() {
        pizzaService = new PizzaServiceImpl();
    }

    @Test
    public void should_createPizza_return_new_pizza() {
        //given
        String orderedPizza = "Margherita";
        List<Ingredients> ingredients = Pizzas.valueOf(orderedPizza).getIngredients();
        //when
        Pizza newPizza = pizzaService.createPizza(orderedPizza, ingredients);
        //then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(newPizza),
                () -> Assertions.assertEquals(Pizzas.valueOf(orderedPizza), newPizza.getName()),
                () -> Assertions.assertArrayEquals(ingredients.toArray(), newPizza.getIngredients().toArray())
        );
    }

    @Test
    public void should_createPizza_throw_exception_when_orderedPizza_is_null() {
        //given
        List<Ingredients> ingredients = Pizzas.valueOf("Margherita").getIngredients();
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> pizzaService
                .createPizza(null, ingredients));
    }

    @Test
    public void should_createPizza_throw_exception_when_ingredients_are_null() {
        //given
        String orderedPizza = "Margherita";
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> pizzaService
                .createPizza(orderedPizza, null));
    }
}