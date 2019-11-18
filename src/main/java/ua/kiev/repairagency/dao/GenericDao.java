package ua.kiev.repairagency.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<E, ID> {
    int save(E entity);

    Optional<E> findById(ID id);

    void update(E entity, String password);

    List<E> findAll(int currentPage, int recordsPerPage);
}
