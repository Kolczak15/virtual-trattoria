package com.company.controllers;

import com.company.entities.Order;
import com.company.enums.Ingredients;
import com.company.enums.Pizzas;
import com.company.services.OrderService;
import com.company.services.PizzaService;
import com.company.services.impl.OrderServiceImpl;
import com.company.services.impl.PizzaServiceImpl;
import com.company.views.ConsoleView;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderController {
    private final OrderService orderService;
    private final PizzaService pizzaService;
    private final ConsoleView consoleView;
    private final Scanner scanner;
    private final Logger logger;

    public OrderController() {
        orderService = new OrderServiceImpl();
        pizzaService = new PizzaServiceImpl();
        consoleView = new ConsoleView();
        scanner = new Scanner(System.in);
        logger = Logger.getLogger(getClass().getName());
    }

    public void order() {
        consoleView.showQuestionName();
        String userName = scanner.nextLine();
        Order newOrder = orderService.createOrder(userName);
        String orderedPizza = pickPizza();
        List<Ingredients> ingredientsOnPizza = composePizza(orderedPizza);
        orderService.addPizza(newOrder, pizzaService.createPizza(orderedPizza, ingredientsOnPizza));

        consoleView.showQuestionAnotherPizza();
        String answer = scanner.nextLine();
        while ("yes".equals(answer)) {
            orderedPizza = pickPizza();
            ingredientsOnPizza = composePizza(orderedPizza);
            orderService.addPizza(newOrder, pizzaService.createPizza(orderedPizza, ingredientsOnPizza));
            consoleView.showQuestionAnotherPizza();
            answer = scanner.nextLine();
        }
        try {
            if (orderService.saveOrder(newOrder)) {
                consoleView.showFinalizationMessage();
                consoleView.showOrderDetails(readOrder());
            } else {
                consoleView.showErrorMessage();
            }
        } catch (Exception e) {
            logger.log(Level.INFO, "{} when saving order.", e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private List<Ingredients> composePizza(String orderedPizza) {
        List<Ingredients> ingredientsOnPizza = removeIngredients(orderedPizza);
        addIngredients(ingredientsOnPizza);
        return ingredientsOnPizza;
    }

    private String pickPizza() {
        consoleView.showQuestionPizza();
        String orderedPizza = scanner.nextLine();
        while (!orderService.isPizzaInMenu(orderedPizza)) {
            consoleView.showMessagePizzaDoesNotExist(orderedPizza);
            orderedPizza = scanner.nextLine();
        }
        return orderedPizza;
    }

    private List<Ingredients> removeIngredients(String orderedPizza) {
        consoleView.showQuestionRemovingIngredient(orderedPizza);
        String answer = scanner.nextLine();
        List<Ingredients> ingredientsOnPizza = Pizzas.valueOf(orderedPizza).getIngredients();
        while (!"no".equals(answer)) {
            if (orderService.removeIngredient(ingredientsOnPizza, answer)) {
                consoleView.showMessageIngredientRemoved(ingredientsOnPizza, answer);
            } else {
                consoleView.showMessageNoSuchIngredientToRemove(ingredientsOnPizza, answer);
            }
            answer = scanner.nextLine();
        }
        return ingredientsOnPizza;
    }

    private void addIngredients(List<Ingredients> ingredientsOnPizza) {
        consoleView.showQuestionAddingIngredient(ingredientsOnPizza);
        String answer = scanner.nextLine();
        while (!"no".equals(answer)) {
            if (orderService.addIngredient(ingredientsOnPizza, answer)) {
                consoleView.showMessageIngredientAdded(ingredientsOnPizza, answer);
            } else {
                consoleView.showMessageNoSuchIngredientToAdd(ingredientsOnPizza, answer);
            }
            answer = scanner.nextLine();
        }
    }

    private Order readOrder() {
        try {
            return orderService.readSavedOrder();
        } catch (Exception e) {
            logger.log(Level.INFO, "{} when saving order.", e.getMessage());
            return null;
        }
    }
}
