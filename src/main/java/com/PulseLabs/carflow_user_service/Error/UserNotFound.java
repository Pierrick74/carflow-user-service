package com.PulseLabs.carflow_user_service.Error;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String message) {
        super(message);
    }
}
