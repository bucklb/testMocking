package com.luv2code.mocking;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by buckl on 25/06/2017.
 */

public class OrderSourceTest {


    //
    //
    @Ignore
    @Test
    public void testProduct()
    {
        double a=-1.010101010,b=-2.0202020, p;
        BasicCalculator testCalculator=new BasicCalculator();
        p=testCalculator.product(a,b);

        Assert.assertEquals(p,a*b,0.0);
//        assertTrue( (p==a*b) );
    }


}
