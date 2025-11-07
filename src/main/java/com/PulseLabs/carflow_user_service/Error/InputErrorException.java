package com.PulseLabs.carflow_user_service.Error;

public class InputErrorException extends RuntimeException {
    public InputErrorException(String message) {
        super(message);
    }
}
