package ua.kiev.repairagency.repository.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<E, Long> {
    Optional<E> findById(Long id);

    List<E> findAll();

    void deleteById(Long id);
}
