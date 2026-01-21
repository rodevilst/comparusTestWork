package com.example.comparustestwork.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User from aggregated databases")
public class User {

    @Schema(description = "User ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private String id;

    @Schema(description = "Username", example = "john_doe")
    private String username;

    @Schema(description = "First name", example = "John")
    private String name;

    @Schema(description = "Last name", example = "Doe")
    private String surname;
}