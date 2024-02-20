package com.example.NiRi.modules;

public class CartItemUpdateResponse {
    private boolean success;
    private String message;

    public CartItemUpdateResponse() {
    }

    public CartItemUpdateResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
