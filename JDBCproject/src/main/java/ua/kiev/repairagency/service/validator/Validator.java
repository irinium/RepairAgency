package ua.kiev.repairagency.service.validator;

import ua.kiev.repairagency.domain.user.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String LOGIN_REGEX = "^[a-zA-Z0-9_.]+@[a-zA-Z.]+?\\.[a-zA-Z]{2,3}$";
    private static final String PASSWORD_REGEX = "^\\S{8,}$";
    private static final Pattern PATTERN_LOG = Pattern.compile(LOGIN_REGEX, Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_PAS = Pattern.compile(PASSWORD_REGEX, Pattern.CASE_INSENSITIVE);


    public void validate(User user) {
        Matcher matcherLog = PATTERN_LOG.matcher(user.getEmail());
        Matcher matcherPas = PATTERN_PAS.matcher(user.getPassword());
        if (!(matcherLog.matches() && matcherPas.matches())) {
            throw new IllegalArgumentException();
        }
    }
}