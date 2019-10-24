package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.entity.appliance.ElectricApplianceEntity;
import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.entity.user.ManagerEntity;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.repository.dao.ApplianceDao;
import ua.kiev.repairagency.repository.dao.UserDao;
import ua.kiev.repairagency.repository.dao.impl.OrderDaoImpl;
import ua.kiev.repairagency.service.ManagerService;
import ua.kiev.repairagency.service.PasswordEncoderImpl;
import ua.kiev.repairagency.service.exception.EntityNotFoundException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Properties;

import static java.util.stream.Collectors.toList;

public class ManagerServiceImpl implements ManagerService {
    private final UserDao userDao;
    private final ApplianceDao applianceDao;
    private final OrderDaoImpl orderDao = new OrderDaoImpl();//////////////////////////
    private final PasswordEncoderImpl passwordEncoder;

    public ManagerServiceImpl(UserDao userDao, ApplianceDao applianceDao,
                              PasswordEncoderImpl passwordEncoder) {
        this.userDao = userDao;
        this.applianceDao = applianceDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(ManagerEntity managerEntity) {
        userDao.save(managerEntity);
    }

    @Override
    public UserEntity login(String login, String password) {
        String encoder = passwordEncoder.encode(password);
        ManagerEntity manager = (ManagerEntity) userDao.findByEmail(login)
                .orElseThrow(() -> new EntityNotFoundException("EntityNotFound"));
        String adminPassword = manager.getPassword();
        if (passwordEncoder.matches(adminPassword, encoder)) {
            return manager;
        }
        throw new EntityNotFoundException("");
    }


    @Override
    public void update(UserEntity userEntity, String password) {
        userDao.update(userEntity, password);
    }

    @Override
    public Optional findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public UserEntity deleteById(Long id) {
        return userDao.deleteById(id);
    }

    @Override
    public Optional findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public Optional<ElectricApplianceEntity> findApplianceById(Long id) {
        return applianceDao.findById(id);
    }

    public void setRepairPrice(OrderEntity order, Long pice){
        orderDao.update(order,pice);
    }

    @Override
    public void sendAdvertisements(ManagerEntity managerEntity, String advertisement) {
        final String EMAIL_FROM = "Electric-appliance-center@gmail.com";
        final String EMAIL_TO = userDao.findAll().stream()
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

