package com.example.comparustestwork.api.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class SortParams {
    @Parameter(description = "Field to sort by", example = "ID")
    private SortField sortBy = SortField.ID;

    @Parameter(description = "Sort order", example = "ASC")
    private SortOrder sortOrder = SortOrder.ASC;
}