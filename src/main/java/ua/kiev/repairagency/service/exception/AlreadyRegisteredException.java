package ua.kiev.repairagency.service.exception;

public class AlreadyRegisteredException extends RuntimeException {
    private static final String MESSAGE = "User with this login is already registered";

    public AlreadyRegisteredException() {
        super(MESSAGE);
    }
}
