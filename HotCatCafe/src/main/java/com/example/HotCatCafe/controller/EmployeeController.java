package com.example.HotCatCafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotCatCafe.exceptions.EmployeeNotFoundException;
import com.example.HotCatCafe.persistence.DTOs.EmployeeDTO;
import com.example.HotCatCafe.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

   

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/id/{id}")
    public EmployeeDTO findEmployeeById(@PathVariable Long id) throws EmployeeNotFoundException {
        return employeeService.findEmployeeById(id);
    }

    @PostMapping
    public Long addEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.addEmployee(employeeDTO);
    }

     @PutMapping("/{id}")
    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long id)
            throws EmployeeNotFoundException {
        return employeeService.updateEmployee(employeeDTO, id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) throws EmployeeNotFoundException {
        return employeeService.deleteById(id);
    }

}
