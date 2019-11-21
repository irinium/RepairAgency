package ua.kiev.repairagency.dao;

import ua.kiev.repairagency.entity.order.ResponseEntity;

import java.util.List;

public interface ResponseDao {
    List<ResponseEntity> findAll(int currentPage, int recordsPerPage);

    int getNumberOfRows();
}
