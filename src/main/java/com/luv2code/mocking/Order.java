package com.luv2code.mocking;

/**
 * Created by buckl on 25/06/2017.
 *  something to do the testing with
 */
public class Order {
    private long orderQuantity;
    private String orderCategory;

    public String getOrderCategory() {
        return orderCategory;
    }

    public void setOrderCategory(String orderCategory) {
        this.orderCategory = orderCategory;
    }

    public long getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(long orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
}
