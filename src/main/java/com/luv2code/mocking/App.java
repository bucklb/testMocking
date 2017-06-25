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

        // In reality I guess we'd need to provide the OrderService with a SOurce
        OrderSource theOrderSource = new OrderSourceImpl();
        OrderService theOrderService = new OrderServiceImpl();
        theOrderService.setOrderSource(theOrderSource);

        String theOutcome =theOrderService.showOrders(1);
        System.out.println(theOutcome);

    }
}
