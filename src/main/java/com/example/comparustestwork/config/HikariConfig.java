package com.example.comparustestwork.config;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record HikariConfig(
        @NotNull  @Positive Integer maxPoolSize,
        @NotNull  @Positive Integer minIdle,
        @NotNull  @Positive Long connectionTimeout,
        @NotNull @Positive Long idleTimeout,
        @NotNull  @Positive Long maxLifetime
) {
}
