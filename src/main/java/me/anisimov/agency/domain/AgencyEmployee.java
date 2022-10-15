package me.anisimov.agency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.anisimov.agency.util.annotation.Column;
import me.anisimov.agency.util.annotation.Table;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class AgencyEmployee {
    @Column(name = "id")
    private Long id;
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
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "residence_place")
    private String residencePlace;
    @Column(name = "phone_number")
    private String phoneNumber;
}
