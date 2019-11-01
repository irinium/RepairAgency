package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.dao.impl.UserDaoImpl;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.service.PasswordEncoder;
import ua.kiev.repairagency.service.exception.EntityNotFoundException;
import ua.kiev.repairagency.validator.Validator;

public abstract class GenericService <T extends UserEntity>{
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private final Validator validator;

    public GenericService() {
        this.passwordEncoder = new PasswordEncoder();
        this.userDao = new UserDaoImpl();
        this.validator = new Validator();
    }

    public <T extends UserEntity> void register(T entity) {
        try {
            validator.validate(entity);
            if (!userDao.findByEmail(entity.getEmail()).isPresent()) {
                String password = entity.getPassword();
                String encode = passwordEncoder.encode(password);
                if (passwordEncoder.matches(password, encode)) {
                    userDao.save(entity);
                }
            } else {
                System.out.println("User with this login is already registered");
            }
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
    }

    @SuppressWarnings("unchecked")
    <T extends UserEntity> T login(String email, String password){
        String encoder = passwordEncoder.encode(password);
        T entity = (T) userDao.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("EntityNotFound"));
        String entityPassword = entity.getPassword();
        if (passwordEncoder.matches(entityPassword, encoder)) {
            return entity;
        }
        throw new EntityNotFoundException("Entity not found. Please, check your email or password and try again");
    }

    void update(UserEntity entity, String password){
        userDao.update(entity, password);
    }
}
