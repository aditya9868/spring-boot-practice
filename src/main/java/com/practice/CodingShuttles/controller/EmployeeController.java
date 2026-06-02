package com.practice.CodingShuttles.controller;

import com.practice.CodingShuttles.dto.EmployeeDTO;
import com.practice.CodingShuttles.entity.EmployeeEntity;
import com.practice.CodingShuttles.repository.EmployeeRepository;
import com.practice.CodingShuttles.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                             @RequestParam(required = false) String sortBy) {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createNewEmployee(employeeDTO);
    }

    @PutMapping("/{employeeId}")
    public EmployeeDTO updateEmployeeById(@PathVariable Long employeeId,
                                          @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployeeById(employeeId, employeeDTO);
    }

    @DeleteMapping("/{employeeId}")
    public boolean deleteEmployeeById(@PathVariable Long employeeId) {
        return employeeService.deleteEmployeeById(employeeId);
    }

    @PatchMapping("/{employeeId}")
    public EmployeeDTO updatePartialEmployeeById(@PathVariable Long employeeId,
                                                 @RequestBody EmployeeDTO employeeDTO) {

    }

}
