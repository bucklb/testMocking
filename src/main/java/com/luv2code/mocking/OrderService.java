package com.luv2code.mocking;

/**
 * Created by buckl on 25/06/2017.
 *
 *  Exists to allow mocking of orderSource and orderSummary
 *
 */
public interface OrderService {

    public String showOrders(long lId);
    public long orderCount(long lId);

    public void setOrderSource(OrderSource  theOrderSource);

    // allow an order to be created
    public void createOrder(long theQuantity, String theCategory );





}
