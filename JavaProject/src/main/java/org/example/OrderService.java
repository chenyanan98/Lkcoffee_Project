package org.example;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    //查询某个外卖员的全部订单
    public List<order> getOrderByRider(List<order> orders,String RiderId) {
        List<order> RiderOrders = new ArrayList<>();
        for (order order : orders) {
            if (order.getRider().getRider_id().equals(RiderId)) {
                RiderOrders.add(order);
            }
        }
        return RiderOrders;
    }

    //查询某种状态下的全部订单
    public List<order> getOrderStatus(List<order> orders,OrderStatus status) {
        List<order> RiderOrders = new ArrayList<>();
        for (order order : orders) {
            if (order.getStatus() == status) {
                RiderOrders.add(order);
            }
        }
        return RiderOrders;
    }

}
