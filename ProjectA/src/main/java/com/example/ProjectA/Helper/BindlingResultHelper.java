package com.example.ProjectA.Helper;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class BindlingResultHelper {

    public static Map<String, String> BindlingResultHelper (BindingResult bindingResult) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return errors;
    }
}
