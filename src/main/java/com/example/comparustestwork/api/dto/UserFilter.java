package com.example.comparustestwork.api.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class UserFilter {
    @Parameter(description = "Filter by username (partial match)")
    private String username;

    @Parameter(description = "Filter by name (partial match)")
    private String name;

    @Parameter(description = "Filter by surname (partial match)")
    private String surname;
}