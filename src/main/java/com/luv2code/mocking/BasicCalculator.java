package com.luv2code.mocking;

/**
 * Created by buckl on 25/06/2017.
 *
 *  Want something to play with ...
 *
 */
public class BasicCalculator {

    public double product(double a, double b){
        return a*b;
    }

//    @Test
    public double fraction(double a, double b){

        if (b==0.0){
            throw new ArithmeticException("Divide by zero");
        }
        return a/b;
    }

}
