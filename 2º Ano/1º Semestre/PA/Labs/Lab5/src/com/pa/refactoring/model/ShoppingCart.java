package com.pa.refactoring.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author patricia.macedo
 */
public class ShoppingCart {
    private ShoppingCartDate sCartDate;
    // Temporary fields
    //private double total;
    // Data Clumps - "this group of variables should be combined into an object" - Extract Class
    //private int day, year,month,hour, minute;

    private boolean terminated;

    // Refused Bequest -> Replace Inheritance with Delegation (ArrayList<Product>)
    private ArrayList<Product> productArrayList;

    public ShoppingCart() {

//        LocalDateTime d= LocalDateTime.now();
//        day=d.getDayOfMonth();
//        month=d.getMonthValue();
//        year=d.getYear();
//        hour=d.getHour();
//        minute =d.getMinute();
        sCartDate = new ShoppingCartDate();
        productArrayList = new ArrayList<>();
        terminated = false;
    }

    public double getTotal() {
        double total = 0;
        for (Product p : productArrayList) {
            total += p.getCost();
        }
        return total;
    }

    public ArrayList<Product> getProductArrayList() {
        return this.productArrayList;
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void terminate() {
        terminated = true;
        this.sCartDate = new ShoppingCartDate();
//        LocalDateTime d= LocalDateTime.now();
//        day=d.getDayOfMonth();
//        month=d.getMonthValue();
//        year=d.getYear();
//        hour=d.getHour();
//        minute =d.getMinute();
    }

    public ShoppingCartDate getCartDate() {
        return this.sCartDate;
    }

//    public String getDateStr() {
//      String  dateStr= String.format("%02d/%02d/%4d %02d:%02d", day,month,year,hour, minute);
//        return dateStr;
//    }

}