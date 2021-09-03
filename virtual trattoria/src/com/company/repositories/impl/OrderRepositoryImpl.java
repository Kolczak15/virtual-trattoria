package com.company.repositories.impl;

import com.company.entities.Order;
import com.company.repositories.OrderRepository;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderRepositoryImpl implements OrderRepository {
    private final Logger logger = Logger.getLogger(getClass().getName());

    public boolean saveOrder(Order order) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("orders.bin"))) {
            outputStream.writeObject(order);
            return true;
        } catch (IOException e) {
            logger.log(Level.INFO, "{} when saving order.", e.getMessage());
            return false;
        }
    }

    public Order readOrder() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("orders.bin"))) {
            return (Order) inputStream.readObject();
        } catch (Exception e) {
            logger.log(Level.INFO, "{} when reading order.", e.getMessage());
            return null;
        }
    }
}
