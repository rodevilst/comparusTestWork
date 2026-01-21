package com.example.comparustestwork.api.controller;

import com.example.comparustestwork.api.dto.SortParams;
import com.example.comparustestwork.api.dto.UserFilter;
import com.example.comparustestwork.domain.model.User;
import com.example.comparustestwork.exp.ErrorResponse;
import com.example.comparustestwork.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    @Operation(
            summary = "Get all users",
            description = "Retrieves users from all configured databases with optional filtering and sorting"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved users",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "503",
                    description = "Database unavailable",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<?> getUsers(@ParameterObject @ModelAttribute UserFilter filter,
                                      @ParameterObject @ModelAttribute SortParams sortParams) {
        List<User> all = userService.findAll(filter,sortParams);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
