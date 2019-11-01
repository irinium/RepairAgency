package ua.kiev.repairagency.service;

import org.apache.log4j.Logger;

import java.util.Base64;


public class PasswordEncoder {
    private final static Logger LOGGER = Logger.getLogger(PasswordEncoder.class);

    public String encode(String password) {
        try {
            return Base64.getEncoder().encodeToString(password.getBytes());
        } catch (Exception ex) {
            LOGGER.debug("Exception of password encoding", ex);
            throw new RuntimeException("SMT go wrong during password encoding", ex);
        }
    }

    public boolean matches(String encodedPassword, String password) {
        return encodedPassword.matches(password);
    }
}
