package me.anisimov.agency.persistance.repository;

import me.anisimov.agency.domain.AgencyEmployee;
import me.anisimov.agency.persistance.DAO;
import me.anisimov.agency.persistance.processor.AgencyEmployeeResultSetProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AgencyEmployeeRepository implements CRUDRepository<AgencyEmployee> {
    private static final String COMMA = ",";
    @Autowired
    private DAO dao;

    @Override
    public void create(AgencyEmployee data) throws SQLException {
        String sql = "Insert into employee(id,name,surname,middlename,citizen_ship,work_experience,birth_date,residence_place,phone_number) " +
                "Values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = dao.getConnection().prepareStatement(sql);
        preparedStatement.setLong(1, data.getId());
        preparedStatement.setString(2, data.getName());
        preparedStatement.setString(3, data.getSurname());
        preparedStatement.setString(4, data.getMiddleName());
        preparedStatement.setString(5, data.getCitizenShip());
        preparedStatement.setInt(6, data.getRequiredExperience());
        preparedStatement.setString(7, String.valueOf(data.getBirthDate()));
        preparedStatement.setString(8, data.getResidencePlace());
        preparedStatement.setString(9, data.getPhoneNumber());
        dao.execute(preparedStatement.toString(), (rs) -> {
            return null;
        });
    }

    @Override
    public void update(long id, AgencyEmployee data) {

    }

    @Override
    public void delete(long id) {
        String sql = "Delete from employee where id=" + id;
        dao.execute(sql, (rs) -> {
            return null;
        });
    }

    @Override
    public void delete(AgencyEmployee data) {
        String sql = "Delete from employee where id=" + data.getId();
        dao.execute(sql, (rs) -> {
            return null;
        });
    }

    @Override
    public AgencyEmployee getById(long id) {
        String sql = "Select from employee where id=" + id;
        AgencyEmployee agencyEmployee = (AgencyEmployee) dao.execute(sql, new AgencyEmployeeResultSetProcessor()).get(0);
        return agencyEmployee;
    }

    @Override
    public List<AgencyEmployee> getById(long... id) {
        String prefix = "";
        String sql = "Select * from employee where id in (";
        StringBuilder stringBuilder = new StringBuilder(sql);
        for (long agencyEmployeeId : id) {
            stringBuilder.append(prefix);
            prefix = ",";
            stringBuilder.append(agencyEmployeeId);
        }
        stringBuilder.append(")");
        List<AgencyEmployee> agencyEmployees = dao.execute(stringBuilder.toString(), new AgencyEmployeeResultSetProcessor());
        return agencyEmployees;
    }

    @Override
    public List<AgencyEmployee> getAll() {
        String sql = "Select * from employee";
        List<AgencyEmployee> agencyEmployees = dao.execute(sql, new AgencyEmployeeResultSetProcessor());
        return agencyEmployees;
    }
}
