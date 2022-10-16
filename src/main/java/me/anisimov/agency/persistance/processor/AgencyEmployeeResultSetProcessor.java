package me.anisimov.agency.persistance.processor;

import me.anisimov.agency.domain.AgencyEmployee;
import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
@Component
public class AgencyEmployeeResultSetProcessor implements ResultSetProcessor<AgencyEmployee>{
    @Override
    public AgencyEmployee process(ResultSet resultSet) throws SQLException {
        AgencyEmployee agencyEmployee = new AgencyEmployee();
        agencyEmployee.setId(resultSet.getLong("id"));
        agencyEmployee.setName(resultSet.getString("name"));
        agencyEmployee.setSurname(resultSet.getString("surname"));
        agencyEmployee.setCitizenShip(resultSet.getString("citizen_ship"));
        agencyEmployee.setBirthDate(LocalDate.parse(resultSet.getString("birth_date")));
        agencyEmployee.setResidencePlace(resultSet.getString("residence_place"));
        agencyEmployee.setPhoneNumber(resultSet.getString("phone_number"));
        return agencyEmployee;
    }

}
