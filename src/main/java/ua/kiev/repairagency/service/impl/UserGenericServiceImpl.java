package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.encoder.PasswordEncoder;
import ua.kiev.repairagency.service.UserGenericService;
import ua.kiev.repairagency.service.exception.AlreadyRegisteredException;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.UserValidator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserGenericServiceImpl implements UserGenericService {
    private final PasswordEncoder passwordEncoder;
    protected final UserDao userDao;
    private final UserValidator userValidator;
    protected final UserMapper userMapper;

    public UserGenericServiceImpl(PasswordEncoder passwordEncoder, UserDao userDao, UserValidator userValidator, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.userValidator = userValidator;
        this.userMapper = userMapper;
    }

    @Override
    public User register(User entity) {
        userValidator.validate(entity);

        if (userDao.findByEmail(entity.getEmail()).isPresent()) {
            throw new AlreadyRegisteredException();
        }
        String password = entity.getPassword();
        String encode = passwordEncoder.encode(password);
        entity.setPassword(encode);
        userDao.save(userMapper.mapUserToUserEntity(entity));

        return entity;
    }

    @Override
    public User login(String email, String password) {
        return userDao.findByEmail(email)
                .map(userMapper::mapUserEntityToUser)
                .filter(x -> Objects.equals(x.getPassword(), passwordEncoder.encode(password)))
                .orElse(null);
    }

    @Override
    public void updatePassword(User entity, String password) {
        userDao.update(userMapper.mapUserToUserEntity(entity), password);
    }

    @Override
    public List<User> findAll(int currentPage, int recordsPerPage) {
        return userDao.findAll(currentPage, recordsPerPage).stream()
                .map(userMapper::mapUserEntityToUser)
                .collect(Collectors.toList());
    }

    @Override
    public int getNumberOfRows() {
       return userDao.getNumberOfRows();
    }
}
