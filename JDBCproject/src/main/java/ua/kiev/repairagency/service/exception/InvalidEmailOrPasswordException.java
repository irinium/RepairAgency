package ua.kiev.repairagency.service.exception;

public class InvalidEmailOrPasswordException extends RuntimeException{
    public InvalidEmailOrPasswordException() {
    }

    public InvalidEmailOrPasswordException(String s) {
        super(s);
    }
}
