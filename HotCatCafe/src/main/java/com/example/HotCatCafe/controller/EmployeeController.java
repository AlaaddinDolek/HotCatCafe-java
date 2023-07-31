package com.example.HotCatCafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotCatCafe.exceptions.EmployeeNotFoundException;
import com.example.HotCatCafe.persistence.DTOs.EmployeeDTO;
import com.example.HotCatCafe.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

   

    @GetMapping("/getAll")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/byId")
    public EmployeeDTO findEmployeeById(@RequestParam Long id) throws EmployeeNotFoundException {
        return employeeService.findEmployeeById(id);
    }

    @PostMapping("/add")
    public Long addEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.addEmployee(employeeDTO);
    }

     @PutMapping("/update")
    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @RequestParam Long id)
            throws EmployeeNotFoundException {
        return employeeService.updateEmployee(employeeDTO, id);
    }

    @DeleteMapping("/deleteById")
    public String deleteById(@RequestParam Long id) throws EmployeeNotFoundException {
        return employeeService.deleteById(id);
    }

}
