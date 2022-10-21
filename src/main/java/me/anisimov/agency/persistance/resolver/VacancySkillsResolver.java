package me.anisimov.agency.persistance.resolver;

import me.anisimov.agency.domain.Skills;
import me.anisimov.agency.domain.Vacancy;
import me.anisimov.agency.persistance.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class VacancySkillsResolver implements Resolver<Vacancy, Skills> {
    @Autowired
    private DAO dao;

    @Override
    public List<Skills> resolve(Vacancy vacancy) {
        String prefix = "";
        String sql = "Select * from vacancy_skills where vacancy_id=" + vacancy.getId();
        List skills = dao.execute(sql, (rs) -> {
            return rs.getInt("skill_id");
        });
        if (skills.isEmpty())
            return Collections.EMPTY_LIST;
        sql = "Select * from skills where id In (";
        StringBuilder stringBuilder = new StringBuilder(sql);
        for (int i = 0; i < skills.size(); i++) {
            stringBuilder.append(prefix);
            prefix = ",";
            stringBuilder.append(skills.get(i));
        }
        stringBuilder.append(");");
        return dao.execute(stringBuilder.toString(), (rs) -> {
            return new Skills(rs.getLong("id"), rs.getString("name"));
        });
    }
}
