package com.practice.CodingShuttles.controller;

import com.practice.CodingShuttles.dto.DepartmentDTO;
import com.practice.CodingShuttles.entity.DepartmentEntity;
import com.practice.CodingShuttles.exceptions.ResourceNotFoundException;
import com.practice.CodingShuttles.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentEntity>> getAllDepartments(@RequestParam(required = false) String title,
                                                                    @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long departmentId) {
        Optional<DepartmentDTO> departmentById = departmentService.getDepartmentById(departmentId);
        return departmentId
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));
    }
}
