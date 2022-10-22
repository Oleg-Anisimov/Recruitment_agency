package me.anisimov.agency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.anisimov.agency.util.annotation.Column;
import me.anisimov.agency.util.annotation.ManyToMany;
import me.anisimov.agency.util.annotation.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "candidate")
public class Candidate extends BaseEntity {
    @Column(name = "surname")
    private String surname;
    @Column(name = "name")
    private String name;
    @Column(name = "middlename")
    private String middleName;
    @Column(name = "citizen_ship")
    private String citizenShip;
    @Column(name = "required_experience")
    private Byte requiredExperience;
    @ManyToMany(intermediate = "work_experience", targetClass = WorkExperience.class)
    private List<WorkExperience> workPlaces = new ArrayList<>();
    @ManyToMany(intermediate = "candidate_skills", targetClass = Skills.class)
    private List<Skills> keySkills = new ArrayList<>();
    @Column(name = "desired_salary")
    private Integer desiredSalary;
    @Column(name = "desired_career")
    private String desiredCareer;

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> candidateParams = new HashMap<>();
        candidateParams.put("id", this.id);
        candidateParams.put("surname", this.surname);
        candidateParams.put("middlename", this.middleName);
        candidateParams.put("citizen_ship", this.middleName);
        candidateParams.put("required_experience", this.requiredExperience);
        candidateParams.put("desired_salary", this.desiredSalary);
        candidateParams.put("desired_career", this.desiredCareer);
        return candidateParams;
    }
}
