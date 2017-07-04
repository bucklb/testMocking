package com.luv2code.mocking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by buckl on 25/06/2017.
 */
public class OrderSourceImpl implements OrderSource {

    List<Order> orderList = new ArrayList<Order>();

    // Just return the lsit (that we could have go from anywhere
    public List<Order> getOrders(long customerId){
        return orderList;
    }

    // Allow a test of argument capture
    public long addOrder(Order theOrder) {

        // Flag that the real class got called
        System.out.println("Adding an ordet to the list !!!");

        orderList.add(theOrder);
        return orderList.size();
    }
}
