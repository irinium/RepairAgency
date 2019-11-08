package ua.kiev.repairagency.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<E, Long> {
    Optional<E> findById(Long id);

    List<E> findAll(int currentPage, int recordsPerPage);
}
