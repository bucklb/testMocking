package com.luv2code.mocking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by buckl on 25/06/2017.
 */
public class OrderSourceImpl implements OrderSource {

    public List<Order> getOrders(long customerId){
        Order order = new Order();
        order.setOrderQuantity(69);
        order.setOrderCategory("Sixty Nine");
        List<Order> theList=new ArrayList<Order>();
        theList.add(order);
        return theList;
    }
}
