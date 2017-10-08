package com.example.pradumn.sssdashboard_2.Model;

/**
 * Created by Pradumn on 25-Sep-17.
 */

public class Order {
    private long orderId;
    private int count;

    //constructors
    public Order() {

    }
    public Order(long orderId, int count) {
        this.orderId = orderId;
        this.count = count;
    }

    //getters and setters

    public long getOrderId() {
        return orderId;
    }
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
