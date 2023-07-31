package com.example.HotCatCafe.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HotCatCafe.exceptions.EmployeeNotFoundException;
import com.example.HotCatCafe.persistence.DTOs.EmployeeDTO;
import com.example.HotCatCafe.persistence.entity.Employee;
import com.example.HotCatCafe.persistence.enums.JobDefinition;
import com.example.HotCatCafe.persistence.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> getAllEmployees() {
        return StreamSupport.stream(employeeRepository.findAll().spliterator(), false).map(Employee::toDTO).toList();
    }

    public EmployeeDTO findEmployeeById(Long id) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return employee.toDTO();
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long id)
            throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        String[] nameParts = employeeDTO.getEmployeeFullName().split("\\s+");
        employee.setFirstName(nameParts[0]);
        employee.setLastName(nameParts[1]);

        try {
            JobDefinition job = JobDefinition.valueOf(employeeDTO.getJobDefinition());
            employee.setJobDefinition(job);
        } catch (Exception e) {
            e.getMessage().toString();
        }
        employeeRepository.save(employee);
        return employee.toDTO();
    }

    public Long addEmployee(EmployeeDTO employeeDTO) {
        Employee emp = new Employee();
        try {
            JobDefinition job = JobDefinition.valueOf(employeeDTO.getJobDefinition());
            emp.setJobDefinition(job);
        } catch (Exception e) {
            e.getMessage().toString();
        }
        
        String[] nameParts = employeeDTO.getEmployeeFullName().split("\\s+");
        emp.setFirstName(nameParts[0]);
        emp.setLastName(nameParts[1]);
        employeeRepository.save(emp);
        return emp.getId();
    }

    public String deleteById(Long id) throws EmployeeNotFoundException {
        employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        employeeRepository.deleteById(id);
        return "Successfully Deleted";
    }

}
