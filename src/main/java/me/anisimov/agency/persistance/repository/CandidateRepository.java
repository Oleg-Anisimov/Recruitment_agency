package me.anisimov.agency.persistance.repository;

import me.anisimov.agency.domain.Candidate;
import me.anisimov.agency.persistance.DAO;
import me.anisimov.agency.persistance.processor.CandidateResultSetProcessor;
import me.anisimov.agency.util.PersistenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CandidateRepository implements CRUDRepository<Candidate> {
    @Autowired
    private DAO dao;

    @Autowired
    private CandidateResultSetProcessor candidateResultSetProcessor;
    @Autowired
    PersistenceUtil persistenceUtil;

    @Override
    public void create(Candidate data) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        String sql = persistenceUtil.buildSqlInsert(data);
        dao.execute(sql, (rs) -> {
            return null;
        });
    }

    @Override
    public void update(Candidate data) throws SQLException {
        String sql = "Update candidate set name=?,surname=?,middlename=?,citizen_ship=?,required_experience=?,work_places=?,key_skills=?,desired_salary=? where id=" + data.getId();
        PreparedStatement preparedStatement = dao.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, data.getName());
        preparedStatement.setString(2, data.getSurname());
        preparedStatement.setString(3, data.getMiddleName());
        preparedStatement.setString(4, data.getCitizenShip());
        preparedStatement.setByte(5, data.getRequiredExperience());
        preparedStatement.setString(6, String.valueOf(data.getWorkPlaces()));
        preparedStatement.setArray(7, (Array) data.getKeySkills());
        preparedStatement.setInt(8, data.getDesiredSalary());
        preparedStatement.setString(9, data.getDesiredCareer());
        dao.execute(preparedStatement.toString(), (rs) -> {
            return null;
        });
    }

    @Override
    public void delete(long id) {
        String sql = "Select from candidate where id=" + id;
        dao.execute(sql, (rs) -> {
            return null;
        });
    }

    @Override
    public void delete(Candidate data) {
        String sql = "Select from candidate where id=" + data.getId();
        dao.execute(sql, (rs) -> {
            return null;
        });
    }

    @Override
    public Candidate getById(long id) {
        String sql = "Select from candidate where id=" + id;
        Candidate candidate = (Candidate) dao.execute(sql, candidateResultSetProcessor).get(0);
        return candidate;
    }

    @Override
    public List<Candidate> getById(long... id) {
        String prefix = "";
        String sql = "Select * from candidate where id in (";
        StringBuilder stringBuilder = new StringBuilder(sql);
        for (long candidateId : id) {
            stringBuilder.append(prefix);
            prefix = ",";
            stringBuilder.append(candidateId);
        }
        stringBuilder.append(")");
        List<Candidate> candidates = dao.execute(stringBuilder.toString(), candidateResultSetProcessor);
        return candidates;
    }

    @Override
    public List<Candidate> getAll() {
        String sql = "Select * from candidate";
        List<Candidate> candidates = dao.execute(sql, candidateResultSetProcessor);
        return candidates;
    }
}
