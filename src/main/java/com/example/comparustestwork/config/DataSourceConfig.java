package com.example.comparustestwork.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataSourceConfig(
        @NotBlank String name,
        @NotBlank String url,
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String table,
        @Valid @NotNull FieldMapping mapping,
        @Valid @NotNull HikariConfig config
) {}