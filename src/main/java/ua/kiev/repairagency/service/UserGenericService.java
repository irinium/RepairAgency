package ua.kiev.repairagency.service;

import ua.kiev.repairagency.domain.user.User;

import java.util.List;

public interface UserGenericService {
    User register(User entity);

    User login(String email, String password);

    void updatePassword(User entity, String password);

    List<User> findAll(int currentPage, int recordsPerPage);

    int getNumberOfRows();
}
