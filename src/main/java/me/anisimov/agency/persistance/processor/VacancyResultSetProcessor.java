package me.anisimov.agency.persistance.processor;

import me.anisimov.agency.domain.Vacancy;
import me.anisimov.agency.persistance.resolver.VacancySkillsResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class VacancyResultSetProcessor implements ResultSetProcessor<Vacancy> {
    @Autowired
    private VacancySkillsResolver vacancySkillsResolver;
    @Override
    public Vacancy process(ResultSet resultSet) throws SQLException {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(resultSet.getLong("id"));
        vacancy.setPosition(resultSet.getString("position"));
        vacancy.setSalary(resultSet.getInt("salary"));
        vacancy.setDescription(resultSet.getString("description"));
        vacancy.setRequiredExperience(resultSet.getByte("required_experience"));
        vacancy.setSkills(vacancySkillsResolver.resolve(vacancy));
        return vacancy;
    }
}
