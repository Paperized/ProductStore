package com.paperized.productstore.exception;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    public EntityAlreadyExistsException() {
        super("Entity already exists!");
    }
}
