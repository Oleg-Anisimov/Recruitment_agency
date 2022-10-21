package me.anisimov.agency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.anisimov.agency.util.annotation.Column;
import me.anisimov.agency.util.annotation.Table;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class AgencyEmployee extends BaseEntity {
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

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> agencyEmployeeParams = new HashMap<>();
        agencyEmployeeParams.put("id", this.id);
        agencyEmployeeParams.put("surname", this.surname);
        agencyEmployeeParams.put("name", this.name);
        agencyEmployeeParams.put("middlename", this.middleName);
        agencyEmployeeParams.put("citizen_ship", this.citizenShip);
        agencyEmployeeParams.put("required_experience", this.requiredExperience);
        agencyEmployeeParams.put("birth_date", this.birthDate);
        agencyEmployeeParams.put("residence_place", this.residencePlace);
        agencyEmployeeParams.put("phoneNumber", this.phoneNumber);
        return agencyEmployeeParams;
    }
}
