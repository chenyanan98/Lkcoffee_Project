package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RiderServiceTest {

    @Test
    void getWaitingDeliveryOrder() {
        RiderService service = new RiderService();


        order order1 = order.builder().status(OrderStatus.WAITING_DELIVERY).build();
        order order2 = order.builder().status(OrderStatus.WAITING_DELIVERY).build();
        order order3 = order.builder().status(OrderStatus.WAITING_DELIVERY).build();
        order order4 = order.builder().status(OrderStatus.WAITING_DELIVERY).build();
        order order5 = order.builder().status(OrderStatus.DELIVERED_IN_TIME).build();
        order order6 = order.builder().status(OrderStatus.DELIVERED_IN_TIME).build();
        order order7 = order.builder().status(OrderStatus.DELIVERED_OVER_TIME).build();
        order order8 = order.builder().status(OrderStatus.DELIVERED_OVER_TIME).build();

        order[] array = {order1, order2, order3, order4, order5, order6, order7, order8};
        List<order> all = new ArrayList<>(Arrays.asList(array));

        List<order> test1 = new ArrayList<>(Arrays.asList(order1, order2, order3, order4));
        List<order> real1 = service.getWaitingDeliveryOrder(all);
        //assertEquals(real1, test1);

        //测试takeOrder方法，模拟外卖员开始接单
        Rider rider = Rider.builder().rider_id("001")
                .name("zhangsan").healthCertificateExpirationDate(LocalDate.parse("2025-01-01"))
                .lastCheckedInDate(LocalDate.parse("2024-06-25")).WaitingDeliveryOrders(new ArrayList<>())
                .DeliveringOrders(new ArrayList<>()).DeliveredOrders(new ArrayList<>()).build();
        service.takeOrder(rider, order1);
        List<order> test2 = new ArrayList<>(Arrays.asList(order1));
        List<order> real2 = rider.getWaitingDeliveryOrders();  //获取外卖员的待配送订单
        //assertEquals(real2, test2); //测试外卖员待配送列表是否新增一条记录

        List<order> test3 = new ArrayList<>(Arrays.asList(order2, order3, order4));
        //assertEquals(real1, test3);// 测试系统待配送列表中是否少一条记录

        //继续让外卖员接单，假设接了三单。
        service.takeOrder(rider, order2);
        service.takeOrder(rider, order3);

        //模拟外卖员离店，测试deliveringOrder方法
        service.deliveringOrder(rider,order1);
        service.deliveringOrder(rider,order2);
        List<order> test4 = new ArrayList<>(Arrays.asList(order1, order2));
        List<order> real4 = rider.getDeliveringOrders();
        //assertEquals(real4, test4); //测试外卖员的配送中列表是否新增记录

        test4 = new ArrayList<>(Arrays.asList(order3));
        real4 = rider.getWaitingDeliveryOrders();
        //assertEquals(real4, test4);  //测试外卖员的待配送列表是否删除记录

        //模拟订单已经送达，测试finishingOrder方法
        service.finishingOrder(rider,order1);
        List<order> test5 = new ArrayList<>(Arrays.asList(order1));
        List<order> real5 = rider.getDeliveredOrders();
        //assertEquals(real5, test5); //测试外卖员的配送完成列表是否新增记录

        test5 = new ArrayList<>(Arrays.asList(order2));
        real5 = rider.getDeliveringOrders();
        assertEquals(real5, test5); //测试外卖员的配送中列表是否删除记录

    }

}