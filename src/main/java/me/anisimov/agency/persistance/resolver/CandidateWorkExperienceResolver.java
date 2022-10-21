package me.anisimov.agency.persistance.resolver;

import me.anisimov.agency.domain.Candidate;
import me.anisimov.agency.domain.WorkExperience;
import me.anisimov.agency.persistance.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CandidateWorkExperienceResolver implements Resolver<Candidate, WorkExperience> {
    @Autowired
    DAO dao;

    @Override
    public List<WorkExperience> resolve(Candidate candidate) {
        String prefix = "";
        String sql = "Select * from work_experience where id=" + candidate.getId();
        List workExperience = dao.execute(sql, (rs) -> {
            return rs.getString("id");
        });
        if(workExperience.isEmpty())
            return Collections.EMPTY_LIST;
        sql = "Select * from work_experience where id In (";
        StringBuilder stringBuilder = new StringBuilder(sql);
        for (int i = 0; i < workExperience.size(); i++) {
            stringBuilder.append(prefix);
            prefix = ",";
            stringBuilder.append(workExperience.get(i));
        }
        stringBuilder.append(");");
        return dao.execute(stringBuilder.toString(), (rs) -> {
            return new WorkExperience(rs.getLong("id"), rs.getString("work_place"),rs.getString("basic_description"));
        });
    }
}
