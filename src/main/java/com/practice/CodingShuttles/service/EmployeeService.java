package com.practice.CodingShuttles.service;

import com.practice.CodingShuttles.dto.EmployeeDTO;
import com.practice.CodingShuttles.entity.EmployeeEntity;
import com.practice.CodingShuttles.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public Optional<EmployeeDTO> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .toList();
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity toSave = modelMapper.map(employeeDTO, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSave);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean isExistingEmployeeId(Long employeeId) {
        return employeeRepository.existsById(employeeId);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean exists = isExistingEmployeeId(employeeId);
        if (!exists) return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        boolean exists = isExistingEmployeeId(employeeId);
        if (!exists) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((filed, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, filed);

            if (fieldToBeUpdated != null) {
                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
            }
        });

        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}
