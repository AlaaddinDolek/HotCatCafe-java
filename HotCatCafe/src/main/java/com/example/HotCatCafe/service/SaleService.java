package com.example.HotCatCafe.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HotCatCafe.exceptions.EmployeeNotFoundException;
import com.example.HotCatCafe.exceptions.OrderNotFoundException;
import com.example.HotCatCafe.exceptions.SaleNotFoundException;
import com.example.HotCatCafe.persistence.DTOs.SaleDTO;
import com.example.HotCatCafe.persistence.entity.Employee;
import com.example.HotCatCafe.persistence.entity.Order;
import com.example.HotCatCafe.persistence.entity.Sale;
import com.example.HotCatCafe.persistence.repository.EmployeeRepository;
import com.example.HotCatCafe.persistence.repository.OrderRepository;
import com.example.HotCatCafe.persistence.repository.SaleRepository;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final EmployeeRepository employeeRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, EmployeeRepository employeeRepository,
            OrderRepository orderRepository) {
        this.saleRepository = saleRepository;
        this.employeeRepository = employeeRepository;
        this.orderRepository = orderRepository;
    }

    public List<SaleDTO> getAllSales() {
        return StreamSupport.stream(saleRepository.findAll().spliterator(), false).map(Sale::toDTO).toList();
    }

    public SaleDTO findSaleById(Long id) throws SaleNotFoundException {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new SaleNotFoundException("Sale not found"));

        return sale.toDTO();
    }

    public List<SaleDTO> getSalesByEmployeeId(Long employeeId) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return StreamSupport.stream(saleRepository.findByEmployee(employee).spliterator(), false).map(Sale::toDTO)
                .toList();

    }

    public SaleDTO getSalesByOrderId(Long orderId) throws OrderNotFoundException, SaleNotFoundException {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        Sale sale = saleRepository.findByOrder(order).orElseThrow(() -> new SaleNotFoundException("Sale not found"));
        return sale.toDTO();

    }

    public SaleDTO insertSale(Long orderId, Long employeeId) throws OrderNotFoundException, EmployeeNotFoundException {
        Sale sale = new Sale();
        Order order = orderRepository.findByIdAndSaleIsNull(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
                order.setSale(sale);
        sale.setOrder(order);
        sale.setEmployee(employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found")));
        saleRepository.save(sale);
        return sale.toDTO();
    }

    public Long deleteSaleById(Long id) throws SaleNotFoundException {
        saleRepository.findById(id).orElseThrow(()-> new SaleNotFoundException("Sale not found"));
        saleRepository.deleteById(id);
        return id;
    }
}
