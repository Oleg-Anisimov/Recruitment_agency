package me.anisimov.agency.persistance;

import lombok.extern.slf4j.Slf4j;
import me.anisimov.agency.persistance.processor.ResultSetProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DAO {
    private final String url = "jdbc:postgresql://localhost:5432/recruitmentAgency";
    private final String user = "Lemain";
    private final String password = "66321";
    private Connection connection;

    public Connection getConnection() {
        return this.connection;
    }

    @PostConstruct
    public void init() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        log.info("Connected to the PostgreSQL server successfully");
    }

    public List execute(String sql, ResultSetProcessor processor) {
        try (Statement statement = connection.createStatement()) {
            boolean execute = statement.execute(sql);
            if (execute) {
                ResultSet resultSet = statement.getResultSet();
                if (resultSet != null) {
                    List<Object> objects = new ArrayList<>();
                    while (resultSet.next()) {
                        Object process = processor.process(resultSet);
                        objects.add(process);
                    }
                    return objects;
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
