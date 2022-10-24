package me.anisimov.agency.persistance.repository;

import me.anisimov.agency.domain.Vacancy;
import me.anisimov.agency.persistance.DAO;
import me.anisimov.agency.persistance.processor.VacancyResultSetProcessor;
import me.anisimov.agency.util.PersistenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.sql.SQLException;
import java.util.List;



@Repository
public class VacancyRepository implements CRUDRepository<Vacancy> {
    private static final String COMMA = ",";
    @Autowired
    private DAO dao;
    @Autowired
    private VacancyResultSetProcessor vacancyResultSetProcessor;
    @Autowired
    private PersistenceUtil persistenceUtil;

    @Override
    public void create(Vacancy data) throws SQLException {
        String sql = persistenceUtil.buildSqlInsert(data);
        dao.execute(sql, (rs) -> {
            return null;
        });
    }

    @Override
    public void update(Vacancy data) throws SQLException {
        String sql = persistenceUtil.buildSqlUpdate(data);
        dao.execute(sql, (rs) -> {
            return null;
        });
    }

    @Override
    public void delete(long id) {
        String sql = "Delete from vacancy where id=" + id;
        dao.execute(sql, (rs) -> {
            return null;
        });
    }

    @Override
    public void delete(Vacancy data) {
        String sql = "Delete from vacancy where id=" + data.getId();
        dao.execute(sql, (rs) -> {
            return null;
        });
    }

    @Override
    public Vacancy getById(long id) {
        String sqlId = "Select * from vacancy where id=" + id;
        Vacancy vacancy = (Vacancy) dao.execute(sqlId, vacancyResultSetProcessor).get(0);
        return vacancy;
    }

    @Override
    public List<Vacancy> getById(long... id) {
        String prefix = "";
        String sql = "Select * from vacancy where id in (";
        StringBuilder stringBuilder = new StringBuilder(sql);
        for (long vacancyId : id) {
            stringBuilder.append(prefix);
            prefix = ",";
            stringBuilder.append(vacancyId);
        }
        stringBuilder.append(")");
        List<Vacancy> vacancies = dao.execute(stringBuilder.toString(), vacancyResultSetProcessor);
        return vacancies;
    }

    @Override
    public List<Vacancy> getAll() {
        String sql = "Select * from vacancy";
        List<Vacancy> vacancies = dao.execute(sql, vacancyResultSetProcessor);
        return vacancies;
    }
}
//        Другой вариант метода create
//        String sql = "Insert into vacancy (id,created,updated,required_experience,salary,employment_type,description,position) Values(";
//        StringBuilder stringBuilder = new StringBuilder(sql);
//        stringBuilder.append(data.getId())
//                .append(COMMA)
//                .append("'" + data.getCreated().toString() + "'")
//                .append(COMMA)
//                .append("'" + data.getUpdated().toString() + "'")
//                .append(COMMA)
//                .append(data.getRequiredExperience())
//                .append(COMMA)
//                .append(data.getSalary())
//                .append(COMMA)
//                .append("'" + data.getEmploymentType() + "'")
//                .append(COMMA)
//                .append("'" + data.getDescription() + "'")
//                .append(COMMA)
//                .append("'" + data.getPosition() + "'")
//                .append(");");
//        dao.execute(stringBuilder.toString(), (rs) -> {
//            return null;
//        });