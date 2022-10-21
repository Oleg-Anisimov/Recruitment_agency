package me.anisimov.agency.persistance.resolver;

import me.anisimov.agency.domain.Candidate;
import me.anisimov.agency.domain.Skills;
import me.anisimov.agency.persistance.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CandidateSkillsResolver implements Resolver<Candidate, Skills> {
    @Autowired
    DAO dao;

    @Override
    public List<Skills> resolve(Candidate candidate) {
        String prefix = "";
        String sql = "Select * from candidate_skills where candidate_id=" + candidate.getId();
        List skills = dao.execute(sql, (rs) -> {
            return rs.getInt("candidate_id");
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
