package com.luv2code.mocking;

// For future reference.  Finally got the powermockito imported by
// Right click on pom file, and in context menu there's maven.  Open it and select Reimport

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


/**
 * Created by buckl on 06/07/2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(StaticClass.class)
public class OrderServicePowerMockTest {

    // Argument capture
    // Make sure the service creates (and passes on) an object that matches what we asked for
    // Just stops
    @Test
    public void testOrderService_staticMethod(){

        OrderServiceImpl target=new OrderServiceImpl();
        OrderServiceImpl spyOrderService=Mockito.spy(target);

        String mockedString="powerMocked message";

        // It's not much of a static method, but still.  Specify that we want to mock the static
        PowerMockito.mockStatic(StaticClass.class);

        // At this point we should specify what form the mocking would take
        PowerMockito.when(StaticClass.staticMethod(Matchers.any(Order.class))).thenReturn(mockedString);

        // Make the things happen
        // Trigger the staticMethod call.  Should sout the mocked message
        target.setStatic();

        // Put it in to verify mode
        PowerMockito.verifyStatic();

        // We're not wanting to test the mocked method, other than how our "target" interacts with it
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);

        // Specify that we want to know what it got passed and then get it
        StaticClass.staticMethod(orderCaptor.capture());
        Order staticOrder = orderCaptor.getValue();

        // We know what should have been passed in ...
        Assert.assertEquals(staticOrder.getOrderQuantity(),1);
        Assert.assertEquals(staticOrder.getOrderCategory(),"whiteNoise");
//        Mockito.verify(spyOrderService,atLeast(1)).set(666);



        System.out.println(staticOrder.getOrderCategory());

    }





}
