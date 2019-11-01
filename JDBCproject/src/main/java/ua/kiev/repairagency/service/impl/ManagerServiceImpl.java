package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.entity.user.CustomerEntity;
import ua.kiev.repairagency.entity.user.ManagerEntity;
import ua.kiev.repairagency.entity.user.MasterEntity;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.dao.ApplianceDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.service.ManagerService;
import ua.kiev.repairagency.service.PasswordEncoder;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class ManagerServiceImpl extends GenericService<ManagerEntity> implements ManagerService {
    private final UserDao userDao;
    private final ApplianceDao applianceDao;
    private final OrderDao orderDao;


    public ManagerServiceImpl(UserDao userDao, ApplianceDao applianceDao, OrderDao orderDao) {
        super();
        this.userDao = userDao;
        this.applianceDao = applianceDao;
        this.orderDao = orderDao;
    }

    @Override
    public List<UserEntity> findAll() {
        return userDao.findAll();
    }

    public void register(MasterEntity masterEntity) {
        super.register(masterEntity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ManagerEntity login(String login, String password) {
        return super.login(login, password);
    }

    @Override
    public void update(UserEntity userEntity, String password) {
        update(userEntity,password);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userDao.findById(id);
    }

    public Optional<OrderEntity> findOrderById(Long id) {
        return orderDao.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    public void deleteOrderById(Long id) {
        orderDao.deleteById(id);
    }

    @Override
    public Optional findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void setPrice(OrderEntity order, Long price) {
        orderDao.update(order, price);
    }

    @Override
    public void acceptOrder(OrderEntity orderEntity, ManagerEntity managerEntity, CustomerEntity customerEntity) {
        orderDao.save(orderEntity);
        sendEmailAboutOrderStatus(managerEntity, acceptMessage(customerEntity, managerEntity, orderEntity), customerEntity);
    }

    @Override
    public void rejectOrder(OrderEntity orderEntity, ManagerEntity managerEntity, String reason, CustomerEntity customerEntity) {
        sendEmailAboutOrderStatus(managerEntity, rejectMessage(customerEntity, managerEntity, reason), customerEntity);
        orderDao.deleteById(orderEntity.getId());
    }

    private void sendEmailAboutOrderStatus(ManagerEntity managerEntity, String text, CustomerEntity customerEntity) {
        final String EMAIL_FROM = managerEntity.getEmail();
        final String EMAIL_TO = customerEntity.getEmail();

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
            message.setSubject("Reject request to Repair Agency");
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String acceptMessage(CustomerEntity customerEntity, ManagerEntity managerEntity, OrderEntity orderEntity) {
        return "Dear, " + customerEntity.getName() + ",\n" +
                "I'm a manager of Repair Agency. Thank you for your interesting in our company. " +
                "Your order is " + orderEntity + "\n" +
                "We appreciate your contacting us. Please write to us anytime if you need help.\n" +
                "Sincerely, Repair Agency\n" + managerEntity.getEmail() + " " + managerEntity.getPhoneNumber();
    }

    private String rejectMessage(CustomerEntity customerEntity, ManagerEntity managerEntity, String reason) {
        return "Dear, " + customerEntity.getName() + ",\n" +
                "I'm a manager of Repair Agency. Thank you for your interesting in our company. " +
                "Unfortunately, we won't be able to accommodate your request for repair yor appliance, because " + reason +
                "\n" +
                "We appreciate your contacting us. We value your business and look forward to serving you." +
                " Please write to us anytime if you need help with another request in the future.\n" +
                "Sincerely, Repair Agency\n" + managerEntity.getEmail() + " " + managerEntity.getPhoneNumber();
    }

    public void answerToResponse(String answer){

    }
}

