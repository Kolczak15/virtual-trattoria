package com.company.services.impl;

import com.company.entities.Order;
import com.company.entities.Pizza;
import com.company.enums.Ingredients;
import com.company.enums.Pizzas;
import com.company.repositories.OrderRepository;
import com.company.services.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;

class OrderServiceImplTest {
    private OrderService orderService;

    @BeforeEach
    public void initTest() {
        orderService = new OrderServiceImpl();
    }

    @Test
    public void should_isPizzaInMenu_return_true_when_pizza_is_in_menu() {
        //given
        String orderedPizza = "Margherita";
        //when
        boolean result = orderService.isPizzaInMenu(orderedPizza);
        //then
        Assertions.assertTrue(result);
    }

    @Test
    public void should_isPizzaInMenu_return_false_when_pizza_is_not_in_menu() {
        //given
        String orderedPizza = "test";
        //when
        boolean result = orderService.isPizzaInMenu(orderedPizza);
        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void should_isPizzaInMenu_throw_exception_when_pizza_is_null() {
        //given
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> orderService.isPizzaInMenu(null));
    }

    @Test
    public void should_removeIngredient_return_false_when_there_is_not_ingredient_on_pizza() {
        //given
        Pizza pizza = new PizzaBuilder()
                .withCapriciosa()
                .build();
        List<Ingredients> ingredients = pizza.getIngredients();
        //when
        boolean result = orderService.removeIngredient(ingredients, "2");
        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void should_removeIngredient_return_true_when_there_is_ingredient_on_pizza() {
        //given
        Pizza pizza = new PizzaBuilder()
                .withCapriciosa()
                .build();
        List<Ingredients> ingredients = pizza.getIngredients();
        //when
        boolean result = orderService.removeIngredient(ingredients, "ham");
        //then
        Assertions.assertTrue(result);
    }

    @Test
    public void should_removeIngredient_return_false_when_ingredient_list_is_empty() {
        //given
        List<Ingredients> ingredients = new ArrayList<>();
        //when
        boolean result = orderService.removeIngredient(ingredients, "ham");
        //then
        Assertions.assertFalse(result);
    }


    @Test
    public void should_removeIngredient_throw_exception_when_ingredients_are_null() {
        //given
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> orderService
                .removeIngredient(null, "ham"));
    }

    @Test
    public void should_removeIngredient_throw_exception_when_answer_is_null() {
        //given
        Pizza pizza = new PizzaBuilder()
                .withMargherita()
                .build();
        List<Ingredients> ingredients = pizza.getIngredients();
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> orderService
                .removeIngredient(ingredients, null));
    }

    @Test
    public void should_addIngredient_return_false_when_there_is_no_such_ingredient() {
        //given
        Pizza pizza = new PizzaBuilder()
                .withCapriciosa()
                .build();
        List<Ingredients> ingredients = pizza.getIngredients();
        //when
        boolean result = orderService.addIngredient(ingredients, "2");
        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void should_addIngredient_return_false_when_there_already_is_such_ingredient_on_pizza() {
        //given
        Pizza pizza = new PizzaBuilder()
                .withCapriciosa()
                .build();
        List<Ingredients> ingredients = pizza.getIngredients();
        //when
        boolean result = orderService.addIngredient(ingredients, "olives");
        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void should_addIngredient_return_true_when_ingredient_can_be_added() {
        //given
        Pizza pizza = new PizzaBuilder()
                .withMargherita()
                .build();
        List<Ingredients> ingredients = pizza.getIngredients();
        //when
        boolean result = orderService.addIngredient(ingredients, "olives");
        //then
        Assertions.assertTrue(result);
    }

    @Test
    public void should_addIngredient_throw_exception_when_ingredients_are_null() {
        //given
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> orderService
                .addIngredient(null, "olives"));
    }

    @Test
    public void should_addIngredient_throw_exception_when_answer_is_null() {
        //given
        Pizza pizza = new PizzaBuilder()
                .withMargherita()
                .build();
        List<Ingredients> ingredients = pizza.getIngredients();
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> orderService
                .addIngredient(ingredients, null));
    }

    @Test
    public void should_addPizza_add_new_pizza_to_pizzaList() {
        //given
        Order newOrder = new Order();
        List<Pizza> pizzaList = newOrder.getPizzaList();
        Pizza pizza = new PizzaBuilder()
                .withMargherita()
                .build();
        //when
        orderService.addPizza(newOrder, pizza);
        //then
        Assertions.assertEquals(1, pizzaList.size());
    }

    @Test
    public void should_addPizza_throw_exception_when_pizza_is_null() {
        //given
        Order newOrder = new Order();
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> orderService.addPizza(newOrder, null));
    }

    @Test
    public void should_addPizza_throw_exception_when_order_is_null() {
        //given
        Pizza pizza = new PizzaBuilder()
                .withCalzone()
                .build();
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> orderService.addPizza(null, pizza));
    }

    @Test
    public void should_createOrder_return_new_order() {
        //given
        String name = "name";
        //when
        Order expected = orderService.createOrder(name);
        //then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(expected),
                () -> Assertions.assertEquals(name, expected.getUserName())
        );
    }

    @Test
    public void should_createOrder_throw_exception_when_userName_is_null() {
        //given
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> orderService.createOrder(null));
    }

    @Test
    public void should_saveOrder_throw_exception_when_order_is_null() {
        //given
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> orderService.saveOrder(null));
    }

    @Test
    public void should_saveOrder_save_given_order() throws IOException {
        //given
        Order order = new Order();
        OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
        Mockito.when(orderRepository.saveOrder(any())).thenReturn(true);
        //when
        boolean result = orderService.saveOrder(order);
        //then
        Assertions.assertTrue(result);
    }

    @Test
    public void should_readSavedOrder_return_order() throws IOException, ClassNotFoundException {
        //given
        OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
        Mockito.when(orderRepository.readOrder()).thenReturn(new Order());
        //when
        Order result = orderService.readSavedOrder();
        //then
        Assertions.assertNotNull(result);
    }

    public static class PizzaBuilder {
        private List<Ingredients> ingredients;
        private Pizzas name;

        public PizzaBuilder() {
        }

        public PizzaBuilder withMargherita() {
            this.name = Pizzas.Margherita;
            this.ingredients = name.getIngredients();
            return this;
        }

        public PizzaBuilder withCapriciosa() {
            this.name = Pizzas.Capriciosa;
            this.ingredients = name.getIngredients();
            return this;
        }

        public PizzaBuilder withCalzone() {
            this.name = Pizzas.Calzone;
            this.ingredients = name.getIngredients();
            return this;
        }

        public Pizza build() {
            return new Pizza(name, ingredients);
        }
    }
}