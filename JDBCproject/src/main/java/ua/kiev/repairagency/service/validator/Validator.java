package ua.kiev.repairagency.service.validator;

import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.exception.InvalidEmailOrPasswordException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_.]+@[a-zA-Z]+?\\.[a-zA-Z]{2,3}$";
    private static final String PASSWORD_REGEX = "^\\S{8,}$";
    private static final Pattern PATTERN_EMAIL_COMPILE = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_PASSWORD_COMPILE = Pattern.compile(PASSWORD_REGEX, Pattern.CASE_INSENSITIVE);


    public void validate(User user) {
        Matcher emailMatcher = PATTERN_EMAIL_COMPILE.matcher(user.getEmail());
        Matcher passwordMatcher = PATTERN_PASSWORD_COMPILE.matcher(user.getPassword());
        if (!(emailMatcher.matches() && passwordMatcher.matches())) {
            throw new InvalidEmailOrPasswordException("Email must content letters, @, ., 2 or 3 characters in the end");
        }
    }
}