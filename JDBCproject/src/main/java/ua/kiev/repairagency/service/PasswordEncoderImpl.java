package ua.kiev.repairagency.service;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;


public class PasswordEncoderImpl implements PasswordEncoder {
    static Logger logger = Logger.getLogger(BCrypt.class.getName());
    static {
        new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
    }

    @Override
    public String encode(CharSequence password) {
        String loggedPassword = "";
        try {
            loggedPassword = BCrypt.hashpw(password.toString(), BCrypt.gensalt(4));
        } catch (Exception ex) {
            logger.log(Level.DEBUG, null, ex);
        }
        return loggedPassword;
    }

    @Override
    public boolean matches(CharSequence loggedPassword, String password) {
        return BCrypt.checkpw(loggedPassword.toString(), password);
    }
}
