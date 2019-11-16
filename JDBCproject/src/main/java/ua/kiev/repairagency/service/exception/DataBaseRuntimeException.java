package ua.kiev.repairagency.service.exception;

public class DataBaseRuntimeException extends RuntimeException {

    public DataBaseRuntimeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DataBaseRuntimeException(Throwable throwable) {
        super(throwable);
    }
}
