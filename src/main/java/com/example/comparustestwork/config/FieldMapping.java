package com.example.comparustestwork.config;

import jakarta.validation.constraints.NotBlank;

public record FieldMapping(
        @NotBlank String id,
        @NotBlank String username,
        @NotBlank String name,
        @NotBlank String surname
) {}