package org.example;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter@Setter
@Builder
public class Rider {
    private String rider_id; //外卖员的ID
    private String name; //外卖员姓名
    private LocalDate healthCertificateExpirationDate; // 健康证到期日期
    private LocalDate lastCheckedInDate; // 最后打卡日期
    private List<order> WaitingDeliveryOrders = new ArrayList<>();//待配送列表
    private List<order> DeliveringOrders = new ArrayList<>();//配送中列表
    private List<order> DeliveredOrders = new ArrayList<>();//配送完成列表

    public Rider() {

    }

    public Rider(String rider_id, String name, LocalDate healthCertificateExpirationDate, LocalDate lastCheckedInDate, List<order> waitingDeliveryOrders, List<order> deliveringOrders, List<order> deliveredOrders) {
        this.rider_id = rider_id;
        this.name = name;
        this.healthCertificateExpirationDate = healthCertificateExpirationDate;
        this.lastCheckedInDate = lastCheckedInDate;
        WaitingDeliveryOrders = waitingDeliveryOrders;
        DeliveringOrders = deliveringOrders;
        DeliveredOrders = deliveredOrders;
    }

    // 检查骑手是否可以接单
    public boolean canAcceptOrders() {
        // 检查今天是否已打卡
        return LocalDate.now().isEqual(this.lastCheckedInDate)
                // 并且健康证在有效期内
                && LocalDate.now().isBefore(this.healthCertificateExpirationDate)
                // 并且当前已接单数未达到上限
                && this.DeliveredOrders.size() < 3;
    }

    // 骑手打卡
    public void checkIn() {
        this.lastCheckedInDate = LocalDate.now();
    }


}
