package com.company.views;

import com.company.entities.Order;
import com.company.entities.Pizza;
import com.company.enums.Ingredients;
import com.company.enums.Pizzas;

import java.util.List;

public class ConsoleView {

    public void showQuestionName() {
        System.out.println("Welcome to virtual trattoria! What is your name?");
    }

    public void showQuestionPizza() {
        System.out.println("Which pizza out of the following would you like to order?");
        showPizzasInMenu();
    }

    public void showMessagePizzaDoesNotExist(String orderedPizza) {
        System.out.println("There is no '" + orderedPizza + "' on the menu. Please choose another one out of the following:");
        showPizzasInMenu();
    }

    public void showQuestionRemovingIngredient(String orderedPizza) {
        System.out.println("Would you like to remove any ingredient? (pick one or type 'no')");
        List<Ingredients> ingredients = Pizzas.valueOf(orderedPizza).getIngredients();
        showIngredients(ingredients);
    }

    public void showMessageNoSuchIngredientToRemove(List<Ingredients> ingredients, String answer) {
        System.out.println("There is no '" + answer + "' in your pizza. Please pick another one or type " +
                "'no' if you want to withdraw from the removal of ingredient.");
        showIngredients(ingredients);
    }

    public void showMessageIngredientRemoved(List<Ingredients> ingredientsOnPizza, String answer) {
        System.out.println("You successfully removed '" + answer + "'.");
        System.out.print("Your pizza contains: ");
        showIngredients(ingredientsOnPizza);
        System.out.println("Would you like to remove " +
                "another ingredient? Type 'no' if you don't.");
    }

    public void showMessageIngredientAdded(List<Ingredients> ingredientsOnPizza, String answer) {
        System.out.println("You successfully added '" + answer + "'. Would you like to add " +
                "another ingredient? Type 'no' if you don't.");
        System.out.print("Your pizza contains: ");
        showIngredients(ingredientsOnPizza);
        System.out.print("Possible ingredients to add: ");
        showPossibleIngredientsToAdd(ingredientsOnPizza);
    }

    public void showMessageNoSuchIngredientToAdd(List<Ingredients> ingredientsOnPizza, String answer) {
        System.out.println("You can't add '" + answer + "'. Would you like to add " +
                "another ingredient? Type 'no' if you don't.");
        showPossibleIngredientsToAdd(ingredientsOnPizza);
    }

    public void showQuestionAddingIngredient(List<Ingredients> ingredientsOnPizza) {
        System.out.print("Your pizza contains: ");
        showIngredients(ingredientsOnPizza);
        System.out.println("Would you like to add any ingredient? (pick one or type 'no')");
        showPossibleIngredientsToAdd(ingredientsOnPizza);
    }

    public void showQuestionAnotherPizza() {
        System.out.println("Would you like to order another pizza? (type 'yes' or any other string to finish your " +
                "order)");
    }

    public void showFinalizationMessage() {
        System.out.println("Thank you for your order.");
    }

    public void showOrderDetails(Order order) {
        if (order == null) {
            System.out.println("Order is null");
        } else {
            System.out.println("User name: " + order.getUserName());
            System.out.println("Order details:");
            for (Pizza pizza : order.getPizzaList()) {
                System.out.print(pizza.getName() + ": ");
                for (Ingredients ingredient : pizza.getIngredients()) {
                    System.out.print(ingredient + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public void showErrorMessage() {
        System.out.println("Something went wrong. Please try again later.");
    }

    private void showPizzasInMenu() {
        for (Pizzas pizza : Pizzas.values()) {
            System.out.print(pizza + " ");
        }
        System.out.println();
    }

    private void showIngredients(List<Ingredients> ingredients) {
        for (Ingredients ingredient : ingredients) {
            System.out.print(ingredient + " ");
        }
        System.out.println();
    }

    private void showPossibleIngredientsToAdd(List<Ingredients> ingredientsOnPizza) {
        for (Ingredients ingr : Ingredients.values()) {
            if (!ingredientsOnPizza.contains(ingr)) {
                System.out.print(ingr + " ");
            }
        }
        System.out.println();
    }
}
