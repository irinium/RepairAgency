package ua.kiev.repairagency.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericDao<E, ID> extends Serializable {
    void save(E entity);

    Optional<E> findById(ID id);

    void update(E entity, String parameter);

    List<E> findAll(int currentPage, int recordsPerPage);
}
