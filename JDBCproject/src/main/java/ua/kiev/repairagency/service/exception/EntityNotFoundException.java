package ua.kiev.repairagency.service.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String s) {
        System.out.println(s);
    }
}
