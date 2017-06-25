package com.luv2code.mocking;

import java.util.List;

/**
 * Created by buckl on 25/06/2017.
 */
public interface OrderSource {

    // a mechanism to return orders
    public List<Order> getOrders(long customerId);

}
