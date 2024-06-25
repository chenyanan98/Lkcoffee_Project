package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RiderTest {

    @Test
    void canAcceptOrders() {
        //测试用例1 一切都正常的情况
        Rider rider1 = Rider.builder().rider_id("001")
                        .name("zhangsan").healthCertificateExpirationDate(LocalDate.parse("2025-01-01"))
                .lastCheckedInDate(LocalDate.parse("2024-06-24")).WaitingDeliveryOrders(new ArrayList<>())
                .DeliveringOrders(new ArrayList<>()).DeliveredOrders(new ArrayList<>()).build();
        boolean test1 = rider1.canAcceptOrders();
        //assertTrue(test1);

        //测试用例2，健康证过期，用例不通过
        Rider rider2 = Rider.builder().rider_id("002")
                .name("zhangsan").healthCertificateExpirationDate(LocalDate.parse("2024-06-01"))
                .lastCheckedInDate(LocalDate.parse("2024-06-24")).WaitingDeliveryOrders(new ArrayList<>())
                .DeliveringOrders(new ArrayList<>()).DeliveredOrders(new ArrayList<>()).build();
        boolean test2 = rider2.canAcceptOrders();
        //assertTrue(test2);

        //测试用例3，打卡时间不是今天，用例不通过
        Rider rider3 = Rider.builder().rider_id("002")
                .name("zhangsan").healthCertificateExpirationDate(LocalDate.parse("2025-01-01"))
                .lastCheckedInDate(LocalDate.parse("2024-06-23")).WaitingDeliveryOrders(new ArrayList<>())
                .DeliveringOrders(new ArrayList<>()).DeliveredOrders(new ArrayList<>()).build();
        boolean test3 = rider3.canAcceptOrders();
        //assertTrue(test3);

        //测试用例4，接单数达到上限，用例不通过
        order order1 = new order();
        order order2 = new order();
        order order3 = new order();
        List<order> DeliveredOrders = new ArrayList<>();
        DeliveredOrders.add(order1);
        DeliveredOrders.add(order2);
        DeliveredOrders.add(order3);

        Rider rider4 = Rider.builder().rider_id("002")
                .name("zhangsan").healthCertificateExpirationDate(LocalDate.parse("2025-01-01"))
                .lastCheckedInDate(LocalDate.parse("2024-06-24")).WaitingDeliveryOrders(new ArrayList<>())
                .DeliveringOrders(new ArrayList<>()).DeliveredOrders(DeliveredOrders).build();
        boolean test4 = rider4.canAcceptOrders();
        assertTrue(test4);
    }
}