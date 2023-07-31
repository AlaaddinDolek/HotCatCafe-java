package com.example.HotCatCafe.persistence.VMs;

import java.util.List;

public class OrderProductVM {
    
    private Long productId;
    private List<Long> extraList;
    private Long orderId;

    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
 
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public List<Long> getExtraList() {
        return extraList;
    }
    public void setExtraList(List<Long> extraList) {
        this.extraList = extraList;
    }

}
