package com.paperized.productstore.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
public class ApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private List<String> errors;

    public static ApiError fromErrors(List<String> errors) {
        return new ApiError(LocalDateTime.now(), errors);
    }

    public static ApiError fromErrors(String... errors) {
        return new ApiError(LocalDateTime.now(), List.of(errors));
    }
}
