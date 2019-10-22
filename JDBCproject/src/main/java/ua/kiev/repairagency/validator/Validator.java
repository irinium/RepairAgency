package ua.kiev.appliances.validator;

import org.springframework.stereotype.Component;
import ua.kiev.appliances.domain.user.CustomerEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class Validator {
    private static final String LOGIN_REGEX = "^[A-Z0-9.-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";
    private static final String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}";
    private static final Pattern PATTERN_LOG = Pattern.compile(LOGIN_REGEX, Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_PAS = Pattern.compile(PASSWORD_REGEX, Pattern.CASE_INSENSITIVE);


    public void validate(CustomerEntity customerEntity) {
        Matcher matcherLog = PATTERN_LOG.matcher(customerEntity.getEmail());
        Matcher matcherPas = PATTERN_PAS.matcher(customerEntity.getPassword());
        if (!(matcherLog.matches() && matcherPas.matches())) {
            throw new IllegalArgumentException();
        }
    }
}
