package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.PasswordEncoder;
import ua.kiev.repairagency.service.exception.AlreadyRegisteredException;
import ua.kiev.repairagency.service.exception.EntityNotFoundException;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.Validator;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserGenericService<T extends User> {
    private final PasswordEncoder passwordEncoder;
    protected final UserDao userDao;
    private final Validator validator;
    protected final UserMapper userMapper;

    public UserGenericService(PasswordEncoder passwordEncoder, UserDao userDao, Validator validator, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.validator = validator;
        this.userMapper = userMapper;
    }

    public <T extends User> T register(T entity) {
        try {
            validator.validate(entity);
            if (!userDao.findByEmail(entity.getEmail()).isPresent()) {
                String password = entity.getPassword();
                String encode = passwordEncoder.encode(password);
                entity.setPassword(encode);
                userDao.save(userMapper.mapUserToUserEntity(entity));
            } else {
                throw new AlreadyRegisteredException();
            }
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        return entity;
    }

    @SuppressWarnings("unchecked")
    public <T extends User> T login(String email, String password) {
        return (T) userDao.findByEmail(email)
                .map(userMapper::mapUserEntityToUser)
                .filter(x -> {
                    String decode = x.getPassword();
                    if (!x.getPassword().equals(password)) {
                       decode = passwordEncoder.decode(x.getPassword());
                    }
                    return Objects.equals(decode,password);
                })
                .orElseThrow(() -> new EntityNotFoundException("Entity not found. Please, check your email or password and try again"));
    }

    public void update(User entity, String password) {
        userDao.update(userMapper.mapUserToUserEntity(entity), password);
    }

    public List<User> findAll(int currentPage, int recordsPerPage) {
        return userDao.findAll(currentPage, recordsPerPage).stream()
                .map(userMapper::mapUserEntityToUser)
                .collect(Collectors.toList());
    }

    public int getNumberOfRows() {
        int numberOfRows = 0;
        try {
            numberOfRows = userDao.getNumberOfRows();
        } catch (SQLException e) {
            e.getMessage();
        }
        return numberOfRows;
    }
}
