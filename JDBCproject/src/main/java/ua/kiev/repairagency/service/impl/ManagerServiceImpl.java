package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.domain.user.ManagerEntity;
import ua.kiev.repairagency.domain.user.UserEntity;
import ua.kiev.repairagency.repository.UserRepository;
import ua.kiev.repairagency.service.ManagerService;
import ua.kiev.repairagency.service.PasswordEncoderImpl;
import ua.kiev.repairagency.service.exception.EntityNotFoundException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static java.util.stream.Collectors.toList;

public class ManagerServiceImpl implements ManagerService {
    private final UserRepository userRepository;
    private final PasswordEncoderImpl passwordEncoder;


    public ManagerServiceImpl(UserRepository userRepository,
                              PasswordEncoderImpl passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(ManagerEntity managerEntity) {
        userRepository.save(managerEntity);
    }

    @Override
    public UserEntity login(String login, String password) {
        String encoder = passwordEncoder.encode(password);
        UserEntity userEntity = userRepository.findByEmail(login);
        if (userEntity == null) {
            throw new EntityNotFoundException("Entity not found!");
        } else {
            String adminPassword = userEntity.getPassword();
            if (passwordEncoder.matches(adminPassword, encoder)) {
                return userEntity;
            }
            throw new EntityNotFoundException("Entity not found!");
        }
    }

    @Override
    public void update(UserEntity userEntity, String password) {
        userRepository.update(userEntity, password);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity deleteById(Long id) {
        return userRepository.deleteById(id);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void sendAdvertisements(ManagerEntity managerEntity, String advertisement) {
        final String EMAIL_FROM = "Electric-appliance-center@gmail.com";
        final String EMAIL_TO = userRepository.getUserEntities().stream()
                .map(UserEntity::getEmail)
                .collect(toList()).toString();

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(managerEntity.getEmail(), managerEntity.getPassword());
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO));
            message.setSubject("Advertisement");
            message.setText(advertisement);
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

