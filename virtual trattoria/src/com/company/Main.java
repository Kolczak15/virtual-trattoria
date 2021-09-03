package com.company;

import com.company.controllers.OrderController;

public class Main {

    public static void main(String[] args) {
        OrderController orderController = new OrderController();
        orderController.order();
    }
}
