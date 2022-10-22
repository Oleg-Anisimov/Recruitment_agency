package me.anisimov.agency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.anisimov.agency.util.annotation.Column;
import me.anisimov.agency.util.annotation.ManyToMany;
import me.anisimov.agency.util.annotation.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vacancy")
public class Vacancy extends BaseEntity {
    @Column(name = "created")
    private LocalDateTime created = LocalDateTime.now();
    @Column(name = "updated")
    private LocalDateTime updated = LocalDateTime.now();
    @Column(name = "position")
    private String position;
    @Column(name = "required_experience")
    private Byte requiredExperience;

    @ManyToMany(intermediate = "vacancy_skills", targetClass = Skills.class)
    private List<Skills> skills = new ArrayList<>();
    @Column(name = "salary")
    private Integer salary;
    @Column(name = "employment_type")
    private EmploymentType employmentType;
    @Column(name = "description")
    private String description;

    public Vacancy(long id, LocalDateTime created, LocalDateTime updated, byte requiredExperience, int salary, String description, String position, EmploymentType employmentType) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.requiredExperience = requiredExperience;
        this.description = description;
        this.position = position;
        this.salary = salary;
        this.employmentType = employmentType;
    }

    public Vacancy(LocalDateTime created, LocalDateTime updated, byte requiredExperience, int salary, String description, String position, EmploymentType employmentType) {
        this.employmentType = employmentType;
        this.created = created;
        this.updated = updated;
        this.requiredExperience = requiredExperience;
        this.description = description;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> vacancyParams = new HashMap<>();
        vacancyParams.put("created", this.created);
        vacancyParams.put("updated", this.updated);
        vacancyParams.put("salary", this.salary);
        vacancyParams.put("required_experience", this.requiredExperience);
        vacancyParams.put("description", this.description);
        vacancyParams.put("position", this.position);
        vacancyParams.put("employment_type", this.employmentType);
        return vacancyParams;
    }

//    public Vacancy(Long id, String position, Integer salary) {
//        this.id = id;
//        this.position = position;
//        this.salary = salary;
//    }
}

