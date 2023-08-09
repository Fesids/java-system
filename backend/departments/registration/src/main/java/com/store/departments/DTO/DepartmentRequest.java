package com.store.departments.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DepartmentRequest(

        @NotNull @NotEmpty String department_name
) {
}
