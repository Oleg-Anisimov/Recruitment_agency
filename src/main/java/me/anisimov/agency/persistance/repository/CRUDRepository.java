package me.anisimov.agency.persistance.repository;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CRUDRepository<T> {
    void create(T data) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    void update(T data) throws SQLException;

    void delete(long id);

    void delete(T data);

    T getById(long id);

    List<T> getById(long... id);

    List<T> getAll() throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException;
}
