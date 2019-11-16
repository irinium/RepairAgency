package ua.kiev.repairagency.service.exception;

public class DataBaseConnectionException extends RuntimeException {
    public DataBaseConnectionException() {
    }

    public DataBaseConnectionException(String s) {
        super(s);
    }
}
