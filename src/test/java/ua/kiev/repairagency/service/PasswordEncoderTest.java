package ua.kiev.repairagency.service;

import org.junit.Test;
import ua.kiev.repairagency.service.encoder.PasswordEncoder;

import static org.junit.Assert.*;

public class PasswordEncoderTest {
    private PasswordEncoder passwordEncoder = new PasswordEncoder();

    @Test
    public void encodedPasswordNotEqualsPassword() {
        String actual = "UserPassword";
        String expected = passwordEncoder.encode(actual);
        assertNotEquals(expected, actual);
    }
}
