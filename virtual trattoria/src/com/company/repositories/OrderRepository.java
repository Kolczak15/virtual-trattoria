package com.company.repositories;

import com.company.entities.Order;

import java.io.IOException;

public interface OrderRepository {

    boolean saveOrder(Order order) throws IOException;

    Order readOrder() throws IOException, ClassNotFoundException;
}
