package me.anisimov.agency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AgencyEmployee {
    private Long id;
    private String surname;
    private String name;
    private String middleName;
    private String citizenShip;
    private Byte requiredExperience;
    private LocalDate birthDate;
    private String residencePlace;
    private String phoneNumber;
}
