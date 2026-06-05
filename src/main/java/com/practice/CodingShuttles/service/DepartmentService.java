package com.practice.CodingShuttles.service;

import com.practice.CodingShuttles.dto.DepartmentDTO;
import com.practice.CodingShuttles.entity.DepartmentEntity;
import com.practice.CodingShuttles.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map((entity) -> modelMapper.map(entity, DepartmentDTO.class))
                .toList();
    }

    public Optional<DepartmentDTO> getDepartmentById(Long departmentId) {
        return  departmentRepository.findById(departmentId)
                    .map((entity) -> modelMapper.map(entity, DepartmentDTO.class));
    }
}
