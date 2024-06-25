package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void checkCurStatus() {
        order order = new order();
        order.init();//初始化

        //测试init方法，用例1，正常情况，用例通过
        OrderStatus test1 = OrderStatus.WAITING_DELIVERY;
        OrderStatus real1 = order.checkCurStatus();
        //assertEquals(test1, real1);

        //测试init方法，用例2，异常情况，用例不通过
        OrderStatus test2 = OrderStatus.OVERDUE;
        OrderStatus real2 = order.checkCurStatus();
        //assertEquals(test2, real2);

        //测试setToDelivering方法，用例3，正常情况，用例通过
        order.setToDelivering();
        OrderStatus test3 = OrderStatus.DELIVERING;
        OrderStatus real3 = order.checkCurStatus();
        //assertEquals(test3, real3);
//
//        //测试setToDelivering方法，用例4，异常情况，用例不通过
//        OrderStatus test4 = OrderStatus.DELIVERED_IN_TIME;
//        OrderStatus real4 = order.checkCurStatus();
//        //assertEquals(test4, real4);
//
//        //测试setToDelivered_in_time方法，用例5，正常情况，用例通过
//        order.setToDelivered_in_time();
//        OrderStatus test5 = OrderStatus.DELIVERED_IN_TIME;
//        OrderStatus real5 = order.checkCurStatus();
//        //assertEquals(test5, real5);
//
//        //测试setToDelivered_in_time方法，用例6，异常情况，用例不通过
//        OrderStatus test6 = OrderStatus.DELIVERING;
//        OrderStatus real6 = order.checkCurStatus();
//        //assertEquals(test6, real6);

        //测试setToOverdue方法，用例7，正常情况，用例通过
//        order.setExpectedDeliveryTime(System.currentTimeMillis() - 30 * 61 * 1000);
//        order.setToOverdue();
//        OrderStatus test7 = OrderStatus.OVERDUE;
//        OrderStatus real7 = order.checkCurStatus();
//        assertEquals(test7, real7);

        //测试setToOverdue方法，用例8，异常情况，用例不通过
//        order.setToOverdue();
//        order.setExpectedDeliveryTime(System.currentTimeMillis() - 30 * 61 * 1000);
//        OrderStatus test8 = OrderStatus.DELIVERED_IN_TIME;
//        OrderStatus real8 = order.checkCurStatus();
//        assertEquals(test8, real8);

        //测试setToDelivered_over_time方法，用例9，正常情况，用例通过
        order.setExpectedDeliveryTime(System.currentTimeMillis() - 30 * 61 * 1000);
        order.setToDelivered_over_time();
        OrderStatus test9 = OrderStatus.DELIVERED_OVER_TIME;
        OrderStatus real9 = order.checkCurStatus();
        //assertEquals(test9, real9);

        //测试setToDelivered_over_time方法，用例10，异常情况，用例不通过
        order.setExpectedDeliveryTime(System.currentTimeMillis() - 30 * 61 * 1000);
        order.setToDelivered_over_time();
        OrderStatus test10 = OrderStatus.OVERDUE;
        OrderStatus real10 = order.checkCurStatus();
        assertEquals(test10, real10);
    }
}