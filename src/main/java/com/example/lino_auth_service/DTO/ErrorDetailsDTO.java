package com.example.lino_auth_service.DTO;

public class ErrorDetailsDTO {
    private String message;

    public ErrorDetailsDTO(String message) {
    }

    public void ErrorDetails(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
