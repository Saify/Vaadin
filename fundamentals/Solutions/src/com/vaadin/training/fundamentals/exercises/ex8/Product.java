package com.vaadin.training.fundamentals.exercises.ex8;

import java.util.Date;
import java.util.Set;

public class Product {

    private String name;
    private double price;
    private Set<String> options;
    private Date available;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<String> getOptions() {
        return options;
    }

    public void setOptions(Set<String> options) {
        this.options = options;
    }

    public Date getAvailable() {
        return available;
    }

    public void setAvailable(Date available) {
        this.available = available;
    }
}
