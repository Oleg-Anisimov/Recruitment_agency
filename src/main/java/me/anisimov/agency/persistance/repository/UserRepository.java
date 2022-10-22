package me.anisimov.agency.persistance.repository;

import me.anisimov.agency.domain.User;
import me.anisimov.agency.persistance.DAO;
import me.anisimov.agency.persistance.processor.UserResultProcessor;
import me.anisimov.agency.util.PersistenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository implements CRUDRepository<User> {
    String prefix = "";
    @Autowired
    DAO dao;
    @Autowired
    PersistenceUtil persistenceUtil;

    @Autowired
    UserResultProcessor userResultProcessor;

    @Override
    public void create(User data) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        String sql = persistenceUtil.buildSqlInsert(data);
        dao.execute(sql, (rs) -> {
            return null;
        });
    }

    @Override
    public void update(User data) throws SQLException {
        String sql = "Update users set email=?,phone=?,password=?,activated=?";
        PreparedStatement preparedStatement = dao.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, data.getEmail());
        preparedStatement.setString(2, data.getPhone());
        preparedStatement.setString(3, data.getPassword());
        preparedStatement.setBoolean(4, data.getActivated());
    }

    @Override
    public void delete(long id) {
        String sql = "Delete from users where id=" + id;
        dao.execute(sql, (rs) -> {
            return null;
        });
    }

    @Override
    public void delete(User data) {
        String sql = "Delete from users where id=" + data.getId();
        dao.execute(sql, (rs) -> {
            return null;
        });
    }

    @Override
    public User getById(long id) {
        String sqlId = "Select from users where id=" + id;
        User user = (User) dao.execute(sqlId, userResultProcessor);
        return user;
    }

    @Override
    public List<User> getById(long... id) {
        String prefix = "";
        String sql = "Select * from users where id in (";
        StringBuilder stringBuilder = new StringBuilder(sql);
        for (long userId : id) {
            stringBuilder.append(prefix);
            prefix = ",";
            stringBuilder.append(userId);
        }
        stringBuilder.append(")");
        List<User> users = dao.execute(stringBuilder.toString(), userResultProcessor);
        return users;
    }

    @Override
    public List<User> getAll() {
        String sql = "Select * from users";
        List<User> users = dao.execute(sql, userResultProcessor);
        return users;
    }

    public User findByPhoneOrEmail(String phoneOrEmail) {
        String sql ="Select * from users where email="+phoneOrEmail+" or phone="+phoneOrEmail;
        User user = (User) dao.execute(sql,userResultProcessor).get(0);
        return null;
    }

}
