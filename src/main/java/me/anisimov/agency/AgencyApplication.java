package me.anisimov.agency;

import lombok.extern.slf4j.Slf4j;
import me.anisimov.agency.domain.AgencyEmployee;
import me.anisimov.agency.domain.Candidate;
import me.anisimov.agency.domain.Vacancy;
import me.anisimov.agency.persistance.DAO;
import me.anisimov.agency.persistance.processor.AgencyEmployeeResultSetProcessor;
import me.anisimov.agency.persistance.processor.CandidateResultSetProcessor;
import me.anisimov.agency.persistance.processor.VacancyResultSetProcessor;
import me.anisimov.agency.persistance.repository.AgencyEmployeeRepository;
import me.anisimov.agency.persistance.repository.CandidateRepository;
import me.anisimov.agency.persistance.repository.VacancyRepository;
//import me.anisimov.agency.util.PersistenceUtil;
import me.anisimov.agency.util.PersistenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
@Slf4j
public class AgencyApplication implements CommandLineRunner {
    @Autowired
    DAO dao;
    @Autowired
    VacancyRepository vacancyRepository;
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    AgencyEmployeeRepository agencyEmployeeRepository;
    @Autowired
    PersistenceUtil persistenceUtil;
    public static void main(String[] args) {
        SpringApplication.run(AgencyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        String s = persistenceUtil.buildSqlInsert(new Vacancy(LocalDateTime.now(), LocalDateTime.now(), (byte) 3, 300, "ytryrtyrt", "javaDeveloper"));
//        dao.execute(s, (rs) -> {
//            return null;
//        });
//        log.info(s);

//        vacancyRepository.create(new Vacancy(4l, LocalDateTime.now(), LocalDateTime.now(), (byte) 3, 30000, "ytryrtyrt", "javaDeveloper"));
//        List vacancyExecute = vacancyRepository.getById(3l,1l);
//        vacancyExecute.forEach(o -> {
//            log.info(((Vacancy) o).toString());
//        });
//
//        String s = persistenceUtil.convertToSql(new Vacancy());
//        log.info(s);

       List vacancies = vacancyRepository.getAll();
                vacancies.forEach(o -> {
            log.info(((Vacancy) o).toString());
        });

//        List candidateExecute = dao.execute("Select * from candidate", new CandidateResultSetProcessor());
//        candidateExecute.forEach(o -> {
//            log.info(((Candidate) o).toString());
//        });
//        List agencyEmployeeExecute = dao.execute("Select * from employee", new AgencyEmployeeResultSetProcessor());
//        agencyEmployeeExecute.forEach(o -> {
//            log.info(((AgencyEmployee) o).toString());
//        });

    }
}
