package me.anisimov.agency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
    private Long id;
    private String surname;
    private String name;
    private String middleName;
    private String citizenShip;
    private Byte requiredExperience;
    private List<String> workPlaces = new ArrayList<>();
    private List<String> keySkills = new ArrayList<>();
    private Integer desiredSalary;
    private String desiredCareer;
    private String basicDescription;

}
