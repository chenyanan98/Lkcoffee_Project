package org.example;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class order {
    private String order_id; //订单ID
    private Rider rider ; // 关联骑手
    private OrderStatus status; // 枚举类型，包含：待接单、待配送、配送中、配送完成
    private long createTime; // 订单创建时间
    private long expectedDeliveryTime; // 预计送达时间（时效30分钟后）
    private long actualDeliveryTime; // 实际送达时间
    private boolean isAccepted; //订单
    private boolean isDelivered; //订单是否送达

    public order() {

    }

    public order(String order_id, Rider rider, OrderStatus status, long createTime, long expectedDeliveryTime, long actualDeliveryTime, boolean isAccepted, boolean isDelivered) {
        this.order_id = order_id;
        this.rider = rider;
        this.status = status;
        this.createTime = createTime;
        this.expectedDeliveryTime = expectedDeliveryTime;
        this.actualDeliveryTime = actualDeliveryTime;
        this.isAccepted = isAccepted;
        this.isDelivered = isDelivered;
    }

    public OrderStatus checkCurStatus(){
        if(System.currentTimeMillis() <= this.expectedDeliveryTime && !isDelivered && !isAccepted){
            return OrderStatus.WAITING_DELIVERY;
        }
        else if (System.currentTimeMillis() <=  this.expectedDeliveryTime && !isDelivered && isAccepted){
            return OrderStatus.DELIVERING;
        }
        else if (System.currentTimeMillis() <= this.expectedDeliveryTime && isDelivered && isAccepted){
            return OrderStatus.DELIVERED_IN_TIME;
        }
        else if (System.currentTimeMillis() > this.expectedDeliveryTime && !isDelivered && isAccepted){
            return OrderStatus.OVERDUE;
        }
        else {
            return OrderStatus.DELIVERED_OVER_TIME;
        }
    }
    // 订单状态变更的方法
    //订单生成，状态转为等待配送，同时开始计时，设置预计送达时间为30分钟以后
    public void init() {
        this.status = OrderStatus.WAITING_DELIVERY;
        this.isAccepted = false;
        this.isDelivered = false;
        this.createTime = System.currentTimeMillis();
        // 计算预计送达时间
        this.expectedDeliveryTime = System.currentTimeMillis() + 30 * 60 * 1000; // 假设现在加30分钟
    }

    //外卖员接单，订单状态转为配送中
    public void setToDelivering() {
        this.status = OrderStatus.DELIVERING;
        this.isAccepted = true;
        this.isDelivered = false;
    }

    //在预计送达时间之内送达，订单状态转为准时送达
    public void setToDelivered_in_time() {
        if(System.currentTimeMillis() <= this.expectedDeliveryTime) {
            this.status = OrderStatus.DELIVERED_IN_TIME;
            this.isAccepted = true;
            this.isDelivered = true;
            this.actualDeliveryTime = System.currentTimeMillis();
        }
    }

    //订单超时且没有送达
    public void setToOverdue() {
        if(System.currentTimeMillis() > this.expectedDeliveryTime) {
            this.status = OrderStatus.OVERDUE;
            this.isAccepted = true;
            this.isDelivered = false;
        }
    }

    // 订单超时送达
    public void setToDelivered_over_time() {
        if(System.currentTimeMillis() > this.expectedDeliveryTime) {
            this.status = OrderStatus.DELIVERED_OVER_TIME;
            this.isAccepted = true;
            this.isDelivered = true;
            this.actualDeliveryTime = System.currentTimeMillis();
        }
    }
}


