package me.anisimov.agency.persistance.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CRUDRepository<T>{
    void create(T data) throws SQLException;
    void update(long id,T data);
    void delete(long id);
    void delete(T data);
    T getById(long id);
    List<T> getById(long ... id);
    List<T> getAll();
}
