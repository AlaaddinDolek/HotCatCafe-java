package com.example.HotCatCafe.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HotCatCafe.exceptions.EmployeeNotFoundException;
import com.example.HotCatCafe.exceptions.OrderNotFoundException;
import com.example.HotCatCafe.exceptions.TableIsNotAvailableException;
import com.example.HotCatCafe.persistence.DTOs.OrderDTO;
import com.example.HotCatCafe.persistence.entity.Employee;
import com.example.HotCatCafe.persistence.entity.Order;
import com.example.HotCatCafe.persistence.repository.EmployeeRepository;
import com.example.HotCatCafe.persistence.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, EmployeeRepository employeeRepository) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<OrderDTO> getAllOrders() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false).map(Order::toDTO).toList();
    }

    public OrderDTO findOrderById(Long orderId) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        return order.toDTO();
    }

    public List<OrderDTO> findByEmployeeId(Long employeeId) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return StreamSupport.stream(orderRepository.findByEmployee(employee).spliterator(), false).map(Order::toDTO)
                .toList();
    }

    public List<OrderDTO> findByTableNumber(String tableNumber) {
        return StreamSupport.stream(orderRepository.findByTableNumber(tableNumber).spliterator(), false)
                .map(Order::toDTO).toList();
    }

    public List<OrderDTO> findTotalPriceGreaterThan(BigDecimal price) {
        return StreamSupport.stream(orderRepository.findByTotalPriceGreaterThan(price).spliterator(), false)
                .map(Order::toDTO).toList();
    }

    public List<OrderDTO> findTotalPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return StreamSupport.stream(orderRepository
                .findByTotalPriceGreaterThanEqualAndTotalPriceLessThanEqual(minPrice, maxPrice).spliterator(), false)
                .map(Order::toDTO).toList();
    }

    public List<OrderDTO> findTotalPriceLessThan(BigDecimal price) {
        return StreamSupport.stream(orderRepository.findByTotalPriceLessThan(price).spliterator(), false)
                .map(Order::toDTO).toList();
    }

    public Long insertOrder(String tableNumber, Long employeeId) throws TableIsNotAvailableException {
        Optional<Order> opOrder = orderRepository.findByTableNumberAndSaleIsNull(tableNumber);
        if(opOrder.isPresent()) throw new TableIsNotAvailableException("Table is not available");
        Order order = new Order();
        order.setTableNumber(tableNumber);
        order.setEmployee(employeeRepository.findById(employeeId).get());
        orderRepository.save(order);
        return order.getId();
    }

    public Long deleteById(Long orderId) throws OrderNotFoundException{
        orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order not found"));
        orderRepository.deleteById(orderId);
        return orderId;
    }
}