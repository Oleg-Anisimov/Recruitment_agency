package me.anisimov.agency.persistance.processor;

import me.anisimov.agency.domain.User;
import me.anisimov.agency.domain.Vacancy;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserResultProcessor implements ResultSetProcessor<User> {
    @Override
    public User process(ResultSet resultSet) throws SQLException {
        User user =new User();
        user.setId(resultSet.getLong("id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setActivated(resultSet.getBoolean("activated"));
        return user;
    }
}
