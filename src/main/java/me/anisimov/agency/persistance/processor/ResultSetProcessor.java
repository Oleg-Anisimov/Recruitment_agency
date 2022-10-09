package me.anisimov.agency.persistance.processor;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetProcessor<T>{
    T process(ResultSet resultSet) throws SQLException;
}
