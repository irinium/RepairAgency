package ua.kiev.repairagency.validator;

import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.service.exception.InvalidEmailOrPasswordException;

import java.util.Optional;
import java.util.regex.Pattern;

public class Validator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9.-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}");

    public void validate(UserEntity entity) {
        Optional.ofNullable(entity)
                .map(UserEntity::getEmail)
                .filter(x -> EMAIL_PATTERN.matcher(x).matches())
                .filter(x -> PASSWORD_PATTERN.matcher(x).matches())
                .orElseThrow(InvalidEmailOrPasswordException::new);
    }
}
