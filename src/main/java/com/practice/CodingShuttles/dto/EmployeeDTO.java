package com.practice.CodingShuttles.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.practice.CodingShuttles.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name of the employee can not be blank.")
    @Size(min = 3, max = 10, message = "Number of character in name should be in the range: [3, 10]")
    private String name;

    @NotBlank(message = "Email of the employee can not be blank.")
    @Email(message = "Email should be in a valid format.")
    private String email;

    @NotNull(message = "Age of the employee can not be blank.")
    @Max(value = 18, message = "Age of Employee cannot be less than 18.")
    @Max(value = 80, message = "Age of Employee cannot be greater than 80.")
    private Integer age;

    @@NotBlank(message = "Role of the employee cannot be blank")
//    @Pattern(regexp = "^(USER|ADMIN)$", message = "Role of employee can either be USER or ADMIN.")
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "Salary of employee should be not null.")
    @Positive(message = "Salary of the Employee should be positive.")
    @Digits(integer = 6, fraction = 2, message = "The salary can be in the form XXXXXX.YY")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

    @PastOrPresent(message = "DateOfJoining field in Employee cannot be in the future.")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be active.")
    @JsonProperty("isActive")
    private Boolean isActive;

}
