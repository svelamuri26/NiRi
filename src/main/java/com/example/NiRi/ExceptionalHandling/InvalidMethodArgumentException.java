package com.example.NiRi.ExceptionalHandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvalidMethodArgumentException extends RuntimeException {
    private String errorKey;
    public InvalidMethodArgumentException(String errorKey, String message) {
        super(message);
        this.errorKey = errorKey;
    }

    public String getErrorKey() {
        return null;
    }
}







