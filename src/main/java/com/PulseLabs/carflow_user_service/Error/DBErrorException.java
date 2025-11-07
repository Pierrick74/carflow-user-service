package com.PulseLabs.carflow_user_service.Error;

public class DBErrorException extends RuntimeException {
    public DBErrorException(String message) {
        super(message);
    }
}
