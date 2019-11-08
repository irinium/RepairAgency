package ua.kiev.repairagency.service.exception;

public class AlreadyRegisteredException extends RuntimeException {

    public AlreadyRegisteredException() {
    }

    public AlreadyRegisteredException(String s) {
        super("User with this login is already registered");
    }
}
