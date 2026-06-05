package com.practice.CodingShuttles.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Title of the department can not be blank")
    @Size(min = 5, max = 20, message = "Number of characters in department title should be in the range: [5, 20]")
    private String title;

    @AssertTrue(message = "Department should be active")
    private boolean active;

    @PastOrPresent(message = "Date of creation of the department can not be in future")
    private LocalDate createdAt;
}
