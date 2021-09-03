package com.company.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private final List<Pizza> pizzaList;
    private final String userName;

    public Order() {
        this.pizzaList = new ArrayList<>();
        this.userName = "";
    }

    public Order(String userName){
        this.userName = userName;
        this.pizzaList = new ArrayList<>();
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public String getUserName() {
        return userName;
    }
}
