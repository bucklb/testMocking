package com.luv2code.mocking;

/*
*  Probably should be sing the framework, but want to get something rolling today (specifically around expected exceptions)
*  so pulling in what appear to be old fashioned calls/annotations in this case
*/
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by buckl on 25/06/2017.
 */
public class BasicCalculatorTest
//    extends TestCase                // The extend TesCase just seems to make it visible to be run as test from menu
{
    /**
     * There are a few things we ought to check with the calculator, including
     * vanilla  - multiplies two numbers
     * zero     - what happens if we use zero(es)
     * nulls    - what do we want to happen if one or more values is null
     * overflow - what happens if we multiply maximum sized value
     */
    @Test
    public void testProduct()
    {
        double a=-1.010101010,b=-2.0202020, p;
        BasicCalculator testCalculator=new BasicCalculator();
        p=testCalculator.product(a,b);

        Assert.assertEquals(p,a*b,0.0);
//        assertTrue( (p==a*b) );
    }

    // Vanilla test
    @Test
    public void testFraction(){
        double a=-1.010101010,b=-2.0202020, p;
        BasicCalculator testCalculator=new BasicCalculator();
        p=testCalculator.fraction(a,b);

        Assert.assertEquals(p,a/b,0.0);
//        assertTrue( (p==a/b) );
    }


    // Divide by zero
    @Test(expected =   ArithmeticException.class)
    public void testFractionWithZeroDivisor(){
        double a=-1.010101010,b=-0.0, p;
        BasicCalculator testCalculator=new BasicCalculator();
        p=testCalculator.fraction(a,b);

//        assertTrue( (p==a/b) );
    }


}
