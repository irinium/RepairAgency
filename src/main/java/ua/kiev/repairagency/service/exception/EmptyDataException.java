package ua.kiev.repairagency.service.exception;

public class EmptyDataException extends RuntimeException {

    public EmptyDataException() {
    }

    public EmptyDataException(String message) {
        super(message);
    }
}
