package com.example.comparustestwork.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@ConfigurationProperties(prefix = "app")
public record DataSourceProperties(
        @Valid @NotEmpty List<DataSourceConfig> datasources
) {}