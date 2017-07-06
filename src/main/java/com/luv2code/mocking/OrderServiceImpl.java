package com.luv2code.mocking;

import java.util.List;

/**
 * Created by buckl on 25/06/2017.
 */
public class OrderServiceImpl implements OrderService{

    OrderSource orderSource;

    public String showOrders(long lId) {

//        return "basic Order Service would normally do something ...";
        long lCnt=0;
        String theShow="Order details : ";
        List<Order> theOrders= this.orderSource.getOrders(lId);

        for(Order theOrder:theOrders){
            if (lCnt++>0) {theShow = theShow + ", ";}
            theShow=theShow + theOrder.getOrderQuantity() +" of " + theOrder.getOrderCategory() + "";
        }

//        return this.orderSource.getOrders(1).toString();
        return theShow;

    }

    public long orderCount(long lId){
        long theCount=0;
        List<Order> theOrders= this.orderSource.getOrders(lId);

        for(Order theOrder:theOrders){
            theCount+= theOrder.getOrderQuantity();
        }

        return theCount;

    }


    public void setOrderSource(OrderSource theOrderSource) {
        this.orderSource=theOrderSource;
    }

    // Want to test the capture thing
    public void createOrder(long theQuantity, String theCategory) {

        Order order=new Order();
        order.setOrderQuantity(theQuantity);
        order.setOrderCategory(theCategory);

        orderSource.addOrder(order);
    }

    // Just call the staticMethod
    public void setStatic() {
        Order staticOrder=new Order();
        staticOrder.setOrderQuantity(1);
        staticOrder.setOrderCategory("whiteNoise");
        System.out.println(StaticClass.staticMethod(staticOrder));
    }
}
