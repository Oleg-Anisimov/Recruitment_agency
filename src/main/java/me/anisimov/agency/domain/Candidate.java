package me.anisimov.agency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.anisimov.agency.util.annotation.Column;
import me.anisimov.agency.util.annotation.ManyToMany;
import me.anisimov.agency.util.annotation.Table;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "candidate")
public class Candidate {
    @Column(name="id")
    private Long id;
    @Column(name="surname")
    private String surname;
    @Column(name="name")
    private String name;
    @Column(name="middlename")
    private String middleName;
    @Column(name="citizen_ship")
    private String citizenShip;
    @Column(name="required_experience")
    private Byte requiredExperience;
    @Column(name="work_places")
    private List<String> workPlaces = new ArrayList<>();
    @ManyToMany(intermediate = "candidate_skills",targetClass = Skills.class)
    private List<Skills> keySkills = new ArrayList<>();
    @Column(name="desired_salary")
    private Integer desiredSalary;
    @Column(name="desired_career")
    private String desiredCareer;
    @Column(name="basic_description")
    private String basicDescription;

}
