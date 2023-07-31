package com.example.HotCatCafe.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HotCatCafe.exceptions.ExtraNotFoundException;
import com.example.HotCatCafe.exceptions.OrderNotFoundException;
import com.example.HotCatCafe.exceptions.ProductNotFoundException;
import com.example.HotCatCafe.persistence.DTOs.OrderDTO;
import com.example.HotCatCafe.persistence.DTOs.OrderProductDTO;
import com.example.HotCatCafe.persistence.VMs.OrderProductVM;
import com.example.HotCatCafe.persistence.entity.Order;
import com.example.HotCatCafe.persistence.entity.OrderProduct;
import com.example.HotCatCafe.persistence.entity.Product;
import com.example.HotCatCafe.persistence.repository.ExtraRepository;
import com.example.HotCatCafe.persistence.repository.OrderProductRepository;
import com.example.HotCatCafe.persistence.repository.OrderRepository;
import com.example.HotCatCafe.persistence.repository.ProductRepository;

@Service
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ExtraRepository extraRepository;

    @Autowired
    public OrderProductService(OrderProductRepository orderProductRepository, ProductRepository productRepository,
            OrderRepository orderRepository,ExtraRepository extraRepository) {
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.extraRepository = extraRepository;
    }

    public List<OrderProductDTO> getAllOrderProducts() {
        return StreamSupport.stream(orderProductRepository.findAll().spliterator(), false).map(OrderProduct::toDTO)
                .toList();
    }

    public List<OrderProductDTO> getOrderProductsByProductId(Long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return StreamSupport.stream(orderProductRepository.findByProduct(product).spliterator(), false)
                .map(OrderProduct::toDTO).toList();

    }

    public List<OrderProductDTO> getOrderProductsByOrderId(Long orderId) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        return StreamSupport.stream(orderProductRepository.findByOrder(order).spliterator(), false)
                .map(OrderProduct::toDTO).toList();
    }

    public void deleteById(Long id) {
        orderProductRepository.deleteById(id);
    }

    public OrderDTO insertOrderProduct(OrderProductVM opVM) throws OrderNotFoundException, ExtraNotFoundException, ProductNotFoundException {
        Order order = orderRepository.findById(opVM.getOrderId()).orElseThrow(()-> new OrderNotFoundException("Order not found"));
        if(order.getSale()==null) {
        OrderProduct op = new OrderProduct();
        for (Long extraId : opVM.getExtraList()) {
            op.getExtraList().add(extraRepository.findById(extraId).orElseThrow(() -> new ExtraNotFoundException("Extra not found")));
        }
        op.setOrder(order);
        Product product = productRepository.findById(opVM.getProductId()).orElseThrow(()-> new ProductNotFoundException("Product not found"));
        op.setProduct(product);
        product.setUnitInStock(product.getUnitInStock()-1);
        //TODO: stock minstock' a ulaştığında bilgi ver
        orderProductRepository.save(op);
        return order.toDTO();
    } else {
         throw new OrderNotFoundException("Take a new order");
    }
    }
}
