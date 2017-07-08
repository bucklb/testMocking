package com.luv2code.mocking;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by buckl on 25/06/2017.
 */

public class OrderServiceTest {

    // Should look at the specifics of setting up the stuff we need to mock (esp if it's common yo many tests)
    // and similarly the tearing down of stuff
    // - annotations are a definite option



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
    // Give it two orders and check that they get output as a summary format correctly
    @Test
    public void testOrderService_showOrders()
    {
        // Want to test the class/methods
        OrderService target=new OrderServiceImpl();

        // Create a tame orderSource (NOTE - via interface, not our implementation) and inject in to the class we want to test
        OrderSource mockOrderSource= org.mockito.Mockito.mock(OrderSource.class);
        target.setOrderSource(mockOrderSource);

        // Set up the mock.  Want the orders returned to be our list
        Mockito.when(mockOrderSource.getOrders(666)).thenReturn(getTestOrders());

        // Note - caling it twice to make sure that the underlying getOrders is called twice
        Assert.assertEquals(target.showOrders(666),"Order details : 666 of Devilment, 668 of Neighbour of the beast");
        Assert.assertEquals(target.showOrders(666),"Order details : 666 of Devilment, 668 of Neighbour of the beast");

        // Check that we called the order source the correct number of times (Holy Hand Grenade of Antioch stylee)
        Mockito.verify(mockOrderSource,times(2)).getOrders(666);
        Mockito.verify(mockOrderSource,atLeast(1)).getOrders(666);
        Mockito.verify(mockOrderSource,atMost(3)).getOrders(666);

    }

    // Want to inject our choice of orderSource rather than rely on anything given
    // Give it two orders and check that the totals are correct
    @Test
    public void testOrderService_orderCount()
    {
        // Want to test the class/methods
        OrderService target=new OrderServiceImpl();

        // Create a tame orderSource (NOTE - via interface, not our implementation) and inject in to the class we want to test
        OrderSource mockOrderSource= org.mockito.Mockito.mock(OrderSource.class);
        target.setOrderSource(mockOrderSource);

        // Set up the mock.  Want the orders returned to be our list
        Mockito.when(mockOrderSource.getOrders(666)).thenReturn(getTestOrders());

        // At this point we can check that calling things doesn't result in NULLs or other wierdnesses.

        // Check that the numbers added up correctly
        Assert.assertEquals(target.orderCount(666),666+668);

        // Check that we called the order source the correct number of times (Holy Hand Grenade of Antioch stylee)
        Mockito.verify(mockOrderSource,times(1)).getOrders(666);

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
        System.out.println("total " + target.orderCount(-1));

    }

    // Argument capture
    // Make sure the service creates (and passes on) an object that matches what we asked for
    @Test
    public void testOrderService_createOrder(){

        // What we will pass in to the create order method (and expect to see in the addOrder call)
        String orderCategory="ticklers";
        long orderQuantity=96;

        // Want to test the class/methods
        OrderService target=new OrderServiceImpl();

        // Create a pet orderSource (that will return our choice of object list)
        // and inject in to the class we want to test. Don't need it to have data
        OrderSource mockOrderSource= org.mockito.Mockito.mock(OrderSource.class);
        target.setOrderSource(mockOrderSource);

        // Run the method we want to test.  Seems we have to do this BEFORE saying we want to capture the argument (to addOrder)
        target.createOrder(orderQuantity,orderCategory);

        // Specify that we want to capture what was passed in to "addOrder".  Create a Captor and use it & its capture method
        ArgumentCaptor<Order> orderCaptor=ArgumentCaptor.forClass(Order.class);
        Mockito.verify(mockOrderSource).addOrder(orderCaptor.capture());

        // We should now have captured what was passed in to the desired method of the mock.
        // Get the Value (/object) that was captured and reflect it to the screen for now
        Order order=orderCaptor.getValue();
        System.out.println(order.getOrderQuantity() + " of " +  order.getOrderCategory());

        // Want to be sure what was passed in is as expected.
        Assert.assertNotNull(order);
        Assert.assertEquals(order.getOrderCategory(),orderCategory);
        Assert.assertEquals(order.getOrderQuantity(),orderQuantity);
    }

    // Get at lest a partial handle on Spy ...
    //
    @Test
    public void testOrderService_usingSpy(){

        // Want to test the class/methods
        OrderService target=new OrderServiceImpl();

        // Put the implementation within a spy (so the normal functionality is used unless overridden)
        OrderSourceImpl orderSourceImpl=new OrderSourceImpl();
        OrderSourceImpl spyOrderSource=Mockito.spy(orderSourceImpl);
        Mockito.when(spyOrderSource.getOrders(Mockito.anyLong())).thenReturn(getTestOrders());

        // Normal mocking, for comparison
        OrderSource mockOrderSource= org.mockito.Mockito.mock(OrderSource.class);
        Mockito.when(mockOrderSource.getOrders(666)).thenReturn(getTestOrders());


        // Assume it still needs to be injected
        target.setOrderSource(spyOrderSource);

        // The create order should call the REAL class method
        System.out.println("Really should see message from real createOrder method here");
        target.createOrder(1,"One");

        // The orderCount calls getOrders, which will use the mocked/spy version. Not the real one
        Assert.assertEquals(target.orderCount(666),666+668);

        // Now do some full on mocking
        target.setOrderSource(mockOrderSource);

        // The create order should call the REAL class method
        System.out.println("Really should see NO message as we're using the MOCK (not spy)");
        target.createOrder(2,"Two");

        // The orderCount calls getOrders, which will use the mocked/spy version. Not the real one
        Assert.assertEquals(target.orderCount(666),666+668);




    }

    @Test
    public void test_staticMethod(){

// Various attempts to prove to myself that mock only works with static if it's POWERmock

        // Set up the mock.  Want the orders returned to be our list
//        Mockito.when(StaticClass.staticMethod(Matchers.any(Order.class))).thenReturn("ha bloody ha");
//        Mockito.when(StaticClass.staticMethod(any(Order.class))).thenReturn("ha bloody ha");
//        Mockito.when(StaticClass.staticMethod(Mockito.anyObject())).thenReturn("ha bloody ha");



    }












}
