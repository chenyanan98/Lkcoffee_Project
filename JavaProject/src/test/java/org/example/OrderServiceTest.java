package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void getOrderByRider() {
        OrderService service = new OrderService();
        order order1 = new order();
        order order2 = new order();
        order order3 = new order();
        order order4 = new order();
        order order5 = new order();
        order order6 = new order();
        order order7 = new order();
        order order8 = new order();

        order1.setRider(Rider.builder().rider_id("001").build());
        order2.setRider(Rider.builder().rider_id("001").build());
        order3.setRider(Rider.builder().rider_id("001").build());
        order4.setRider(Rider.builder().rider_id("002").build());
        order5.setRider(Rider.builder().rider_id("002").build());
        order6.setRider(Rider.builder().rider_id("003").build());
        order7.setRider(Rider.builder().rider_id("003").build());
        order8.setRider(Rider.builder().rider_id("004").build());

        order[] array = {order1, order2, order3, order4, order5, order6, order7,order8};
        List<order> all = new ArrayList<>(Arrays.asList(array));

        //测试用例1,一切正常，用例通过。
        List<order> real1 = service.getOrderByRider(all,"004");
        List<order> test1 = new ArrayList<>(Arrays.asList(order8));
        //assertEquals(real1, test1);

        //测试用例2，实际值比测试值多，用例不通过
        List<order> real2 = service.getOrderByRider(all,"001");
        List<order> test2 = new ArrayList<>(Arrays.asList(order1, order2));
        //assertEquals(real2, test2);

        //测试用例3，测试值比实际多，用例不通过
        List<order> real3 = service.getOrderByRider(all,"002");
        List<order> test3 = new ArrayList<>(Arrays.asList(order4, order5, order6));
        //assertEquals(real3, test3);

        //测试用例4，测试值与实际值完全无交集
        List<order> real4 = service.getOrderByRider(all,"003");
        List<order> test4 = new ArrayList<>(Arrays.asList(order1, order1, order1));
        assertEquals(real4, test4);
    }

    @Test
    void getOrderStatus() {
        OrderService service = new OrderService();
        //WAITING_DELIVERY, DELIVERING, DELIVERED_IN_TIME,OVERDUE,DELIVERED_OVER_TIME
        order order1 = order.builder().status(OrderStatus.WAITING_DELIVERY).build();
        order order2 = order.builder().status(OrderStatus.WAITING_DELIVERY).build();
        order order3 = order.builder().status(OrderStatus.WAITING_DELIVERY).build();
        order order4 = order.builder().status(OrderStatus.DELIVERING).build();
        order order5 = order.builder().status(OrderStatus.DELIVERING).build();
        order order6 = order.builder().status(OrderStatus.DELIVERED_IN_TIME).build();
        order order7 = order.builder().status(OrderStatus.OVERDUE).build();
        order order8 = order.builder().status(OrderStatus.DELIVERED_OVER_TIME).build();

        order[] array = {order1, order2, order3, order4, order5, order6, order7,order8};
        List<order> all = new ArrayList<>(Arrays.asList(array));

        //测试用例1,一切正常，用例通过。
        List<order> real1 = service.getOrderStatus(all,OrderStatus.OVERDUE);
        List<order> test1 = new ArrayList<>(Arrays.asList(order7));
        //assertEquals(real1, test1);

//      //测试用例2，实际值比测试值多，用例不通过
        List<order> real2 = service.getOrderStatus(all,OrderStatus.WAITING_DELIVERY);
        List<order> test2 = new ArrayList<>(Arrays.asList(order1));
        //assertEquals(real2, test2);
//
        //测试用例3，测试值比实际多，用例不通过
        List<order> real3 = service.getOrderStatus(all,OrderStatus.DELIVERING);
        List<order> test3 = new ArrayList<>(Arrays.asList(order4, order5, order6));
        //assertEquals(real3, test3);

        //测试用例4，测试值与实际值完全无交集
        List<order> real4 = service.getOrderStatus(all,OrderStatus.DELIVERED_IN_TIME);
        List<order> test4 = new ArrayList<>(Arrays.asList(order4));
        assertEquals(real4, test4);
    }
}