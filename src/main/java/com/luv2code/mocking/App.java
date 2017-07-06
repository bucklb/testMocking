package com.luv2code.mocking;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        BasicCalculator basicCalculator = new BasicCalculator();
        double a=1.01, b=2.02;

        System.out.println(a + " * " + b + " = " + basicCalculator.product(a,b));

        // In reality I guess we'd need to provide the OrderService with a Source
        OrderService theOrderService = new OrderServiceImpl();

        // Will need something to take/return the orders.  Inject it
        OrderSource theOrderSource = new OrderSourceImpl();
        theOrderService.setOrderSource(theOrderSource);

        // Might as well give it a modicum of data
        theOrderService.createOrder(69,"Condom");

        // Trigger the staticMethd we want to powerMock
        theOrderService.setStatic();


        String theOutcome =theOrderService.showOrders(1);
        System.out.println(theOutcome);

        System.out.println("total item = "+theOrderService.orderCount(0));

    }
}
