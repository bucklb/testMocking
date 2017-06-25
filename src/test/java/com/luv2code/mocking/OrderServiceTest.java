package com.luv2code.mocking;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.times;

/**
 * Created by buckl on 25/06/2017.
 */

public class OrderServiceTest {

    // clean up the code by extracting this bit of setup
    private List<Order> getTestOrders(){

        // Create a bit of data
        Order newOrder;
        List<Order> theList=new ArrayList<Order>();

        newOrder=new Order();
        newOrder.setOrderQuantity(666);
        newOrder.setOrderCategory("Devilment");
        theList.add(newOrder);

        newOrder=new Order();
        newOrder.setOrderQuantity(668);
        newOrder.setOrderCategory("Neighbour of the beast");
        theList.add(newOrder);

        return theList;
    }



    // Want to inject our choice of orderSource rather than rely on anything given
    @Test
    public void testOrderService()
    {
        // Want to test the class/methods
        OrderService target=new OrderServiceImpl();

        // Create a tame orderSource (NOTE - via interface, not our implementation) and inject in to the class we want to test
        OrderSource mockOrderSource= org.mockito.Mockito.mock(OrderSource.class);
        target.setOrderSource(mockOrderSource);

        // Set up the mock.  Want the orders returned to be our list
        Mockito.when(mockOrderSource.getOrders(666)).thenReturn(getTestOrders());

        // At this point we can check that calling things doesn't result in NULLs or other wierdnesses.

        // Piss Poor kinf of a test, but so it goes
        Assert.assertEquals(target.showOrders(666),"Order details : 666 of Devilment, 668 of Neighbour of the beast");

        // Check that the numbers added up correctly
        Assert.assertEquals(target.orderCount(666),666+668);

        // Check that we called the order source the correct number of times (Holy Hand Grenade of Antioch stylee)
        Mockito.verify(mockOrderSource,times(2)).getOrders(666);
        Mockito.verify(mockOrderSource,atLeast(1)).getOrders(666);
        Mockito.verify(mockOrderSource,atMost(3)).getOrders(666);

    }

    // Just playing with forcing an exception when we want one
    @Test(expected =   ArithmeticException.class)
    public void testOrderServiceWithNegativeId(){

        // Want to test the class/methods
        OrderService target=new OrderServiceImpl();

        // Create a pet orderSource (that will retrun our choice of object list)
        // and inject in to the class we want to test. Don't need it to have data
        OrderSource mockOrderSource= org.mockito.Mockito.mock(OrderSource.class);
        target.setOrderSource(mockOrderSource);

        Mockito.when(mockOrderSource.getOrders(-1)).thenThrow(new ArithmeticException("Arse Monkey"));
        System.out.println(target.orderCount(-1));

    }



}
