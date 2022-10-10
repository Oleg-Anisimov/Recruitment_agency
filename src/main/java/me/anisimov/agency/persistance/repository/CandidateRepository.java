package me.anisimov.agency.persistance.repository;

import me.anisimov.agency.domain.Candidate;
import me.anisimov.agency.persistance.DAO;
import me.anisimov.agency.persistance.processor.CandidateResultSetProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CandidateRepository implements CRUDRepository<Candidate> {
    @Autowired
    private DAO dao;

    @Override
    public void create(Candidate data) throws SQLException {
        String sql = "Insert into candidate(id,name,surname,middlename,citizen_ship,required_experience,work_places,key_skills,desired_salary,desired_career,basic_description) " +
                "Values (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = dao.getConnection().prepareStatement(sql);
        preparedStatement.setLong(1,data.getId());
        preparedStatement.setString(2, data.getName());
        preparedStatement.setString(3, data.getSurname());
        preparedStatement.setString(4, data.getMiddleName());
        preparedStatement.setString(5, data.getCitizenShip());
        preparedStatement.setInt(6, data.getRequiredExperience());
        preparedStatement.setString(7, String.valueOf(data.getWorkPlaces()));
        preparedStatement.setArray(8, (Array) data.getKeySkills());
        preparedStatement.setInt(9, data.getDesiredSalary());
        preparedStatement.setString(10, data.getDesiredCareer());
        preparedStatement.setString(11, data.getBasicDescription());
        dao.execute(preparedStatement.toString(), (rs) -> {
            return null;
        });
    }

    @Override
    public void update( Candidate data) {

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
        Candidate candidate = (Candidate) dao.execute(sql, new CandidateResultSetProcessor()).get(0);
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
        List<Candidate> candidates = dao.execute(stringBuilder.toString(), new CandidateResultSetProcessor());
        return candidates;
    }

    @Override
    public List<Candidate> getAll() {
        String sql = "Select * from candidate";
        List<Candidate> candidates = dao.execute(sql, new CandidateResultSetProcessor());
        return candidates;
    }
}
