package com.example.comparustestwork.exp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Error response")
public class ErrorResponse {

    @Schema(description = "Error status", example = "Database error")
    private String status;

    @Schema(description = "Error message", example = "Connection refused to postgres-db")
    private String message;
}