package me.anisimov.agency.persistance.processor;

import me.anisimov.agency.domain.Candidate;
import me.anisimov.agency.persistance.resolver.CandidateSkillsResolver;
import me.anisimov.agency.persistance.resolver.CandidateWorkPlaceAndBasicDescriptionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class CandidateResultSetProcessor implements ResultSetProcessor<Candidate> {
    @Autowired
    CandidateSkillsResolver candidateSkillsResolver;
    @Autowired
    CandidateWorkPlaceAndBasicDescriptionResolver candidateWorkPlaceAndBasicDescriptionResolver;
    @Override
    public Candidate process(ResultSet resultSet) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setId(resultSet.getLong("id"));
        candidate.setName(resultSet.getString("name"));
        candidate.setSurname(resultSet.getString("surname"));
        candidate.setMiddleName(resultSet.getString("middlename"));
        candidate.setCitizenShip(resultSet.getString("citizen_ship"));
        candidate.setRequiredExperience(resultSet.getByte("required_experience"));
        candidate.setDesiredSalary(resultSet.getInt("desired_salary"));
        candidate.setDesiredCareer(resultSet.getString("desired_career"));
        candidate.setKeySkills(candidateSkillsResolver.resolve(candidate));
        candidate.setWorkPlaces(candidateWorkPlaceAndBasicDescriptionResolver.resolve(candidate));
        return candidate;
    }
}
