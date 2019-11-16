package ua.kiev.repairagency.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordEncoderTest {
    PasswordEncoder passwordEncoder = new PasswordEncoder();

    @Test
    public void encodedPasswordNotEqualsPassword() {
        String actual = "UserPassword";
        String expected = passwordEncoder.encode(actual);
        assertNotEquals(expected, actual);
    }

    @Test
    public void dencodedPasswordNotEqualsPassword() {
        String actual = "UserPassword";
        String expected = passwordEncoder.decode(actual);
        assertNotEquals(expected, actual);
    }
}
