package org.example;

import java.util.ArrayList;
import java.util.List;

public class RiderService {
    List<order> AllWaitingDeliveryOrders = new ArrayList<>();

    //查看是否有可以接单的订单
    public List<order> getWaitingDeliveryOrder(List<order> orders) {
        for (order order : orders) {
            if (order.getStatus() == OrderStatus.WAITING_DELIVERY) {
                AllWaitingDeliveryOrders.add(order);
            }
        }
        return AllWaitingDeliveryOrders;
    }

    //骑手开始接单
    public void takeOrder(Rider rider,order order) {
        //判断此外卖员能否接单，订单是否为等待配送的状态
        if (rider.canAcceptOrders() && order.getStatus() == OrderStatus.WAITING_DELIVERY) {
            order.setRider(rider);
            rider.getWaitingDeliveryOrders().add(order);// 外卖员的待配送列表中新增一条记录
            this.AllWaitingDeliveryOrders.remove(order); //系统待配送列表少一条记录
        }

    }

    //骑手离店，订单转为配送中状态
    public void deliveringOrder(Rider rider,order order) {
        order.setToDelivering(); //订单状态转为配送中
        rider.getDeliveringOrders().add(order); //外卖员的配送中列表新增一条记录
        rider.getWaitingDeliveryOrders().remove(order); //外卖员的待配送列表删除一条记录

    }

    //骑手完成配送，订单转为准时送达状态或者超时送达状态
    public void finishingOrder(Rider rider,order order) {
        //通过判断当前系统时间和预期送达时间，来判断订单是否超时
        if (System.currentTimeMillis() <= order.getExpectedDeliveryTime()) {
            order.setToDelivered_in_time(); //订单转为按时送达状态
        } else {
            order.setToDelivered_over_time(); //订单转为超时送达状态
        }
        rider.getDeliveredOrders().add(order); //外卖员已送达列表中新增一条记录
        rider.getDeliveringOrders().remove(order); //外卖员配送中列表中删除一条记录
    }
}
