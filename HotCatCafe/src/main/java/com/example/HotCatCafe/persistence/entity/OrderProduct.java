package com.example.HotCatCafe.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.HotCatCafe.persistence.DTOs.ExtraDTO;
import com.example.HotCatCafe.persistence.DTOs.OrderProductDTO;
import com.example.HotCatCafe.persistence.DTOs.interfaces.DTOConverter;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orderProducts")
public class OrderProduct extends BaseEntity implements DTOConverter<OrderProductDTO> {
    
    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToMany
    private List<Extra> extraList;


    @Override
    public OrderProductDTO toDTO() {
        OrderProductDTO dto = new OrderProductDTO();
        List<ExtraDTO> extraDTOList = new ArrayList<>();
        for (Extra extra : extraList) {
            extraDTOList.add(extra.toDTO());
        }
        dto.setProductName(product.getProductName());
        dto.setTableNumber(order.getTableNumber());
        dto.setUnitPrice(product.getUnitPrice());
        dto.setId(id);
        dto.setExtraDtoList(extraDTOList);
        return dto;
    }

    public OrderProduct() {
        extraList = new ArrayList<>();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Extra> getExtraList() {
        return extraList;
    }

    public void setExtraList(List<Extra> extraList) {
        this.extraList = extraList;
    }

 
}
