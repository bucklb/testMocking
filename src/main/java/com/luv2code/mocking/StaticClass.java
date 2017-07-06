package com.luv2code.mocking;

// Jusrt want something to use with PowerMokito

/**
 * Created by buckl on 06/07/2017.
 */
public class StaticClass {

    public static String staticMethod(Order order){
        if (order == null) {
            return "staticMethod just got NULL order"   ;
        } else {
            return "staticMethod just got order : " + order.getOrderQuantity() + " of " + order.getOrderCategory();
        }
    }

}
