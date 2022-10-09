package me.anisimov.agency.persistance.processor;

import me.anisimov.agency.domain.Candidate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CandidateResultSetProcessor implements ResultSetProcessor<Candidate> {
    @Override
    public Candidate process(ResultSet resultSet) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setId(resultSet.getLong("id"));
        candidate.setName(resultSet.getString("name"));
        candidate.setSurname(resultSet.getString("surname"));
        candidate.setCitizenShip(resultSet.getString("citizen_ship"));
        candidate.setDesiredSalary(resultSet.getInt("desired_salary"));
        candidate.setDesiredCareer(resultSet.getString("desired_career"));
        return candidate;
    }
}
