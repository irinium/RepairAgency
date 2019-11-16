package ua.kiev.repairagency.service.validator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.exception.InvalidEmailOrPasswordException;

public class ValidatorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private final Validator userValidator = new Validator();

    @Test
    public void validateShouldNotThrowExceptionForValidUserEmail() {
        final User user = initUser("alex52@gmail.com","11111111");
        userValidator.validate(user);
    }

    @Test
    public void validateShouldThrowExceptionForNullUserEmail() {
        expectedException.expect(NullPointerException.class);
        final User user = initUser(null,null);
        userValidator.validate(user);
    }

    @Test
    public void validateShouldThrowExceptionForInvalidUserEmail() {
        expectedException.expect(InvalidEmailOrPasswordException.class);
        final User user = initUser("email","password");

        userValidator.validate(user);
    }

    private static User initUser(String email, String password) {
        return User.builder()
                .withEmail(email)
                .withPassword(password)
                .build();
    }
}