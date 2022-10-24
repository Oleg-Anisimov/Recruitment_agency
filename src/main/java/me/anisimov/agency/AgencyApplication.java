package me.anisimov.agency;

import lombok.extern.slf4j.Slf4j;
import me.anisimov.agency.domain.*;
import me.anisimov.agency.persistance.DAO;
import me.anisimov.agency.persistance.processor.AgencyEmployeeResultSetProcessor;
import me.anisimov.agency.persistance.processor.CandidateResultSetProcessor;
import me.anisimov.agency.persistance.processor.VacancyResultSetProcessor;
import me.anisimov.agency.persistance.repository.AgencyEmployeeRepository;
import me.anisimov.agency.persistance.repository.CandidateRepository;
import me.anisimov.agency.persistance.repository.UserRepository;
import me.anisimov.agency.persistance.repository.VacancyRepository;
//import me.anisimov.agency.util.PersistenceUtil;
import me.anisimov.agency.util.DataBaseInitializer;
import me.anisimov.agency.util.PersistenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@SpringBootApplication
@Slf4j
public class AgencyApplication implements CommandLineRunner {
    @Autowired
    private DAO dao;
    @Autowired
    private VacancyRepository vacancyRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private AgencyEmployeeRepository agencyEmployeeRepository;
    @Autowired
    private PersistenceUtil persistenceUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Optional<DataBaseInitializer> dataBaseInitializer;


    public static void main(String[] args) {
        SpringApplication.run(AgencyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        dataBaseInitializer.ifPresent(dataBaseInitializer -> {
            try {
                dataBaseInitializer.init();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

//      String s = persistenceUtil.buildSqlInsert(new Vacancy(8l,LocalDateTime.now(), LocalDateTime.now(), (byte) 3, 300, "ytryrtyrt", "javaDeveloper", EmploymentType.FULL_TIME));
//        dao.execute(s, (rs) -> {
//            return null;
//        });
//        log.info(s);;
//        vacancyRepository.create(new Vacancy(LocalDateTime.now(), LocalDateTime.now(), (byte) 8, 4300, "ikliioiol", "Developer", EmploymentType.FULL_TIME));
//        List vacancyExecute = vacancyRepository.getAll();
//        vacancyExecute.forEach(o -> {
//            log.info(((Vacancy) o).toString());
//        });
//        List all = candidateRepository.getAll();
//        all.forEach(o->{log.info(((Candidate) o).toString());});
//        vacancyRepository.update(new Vacancy(LocalDateTime.now(), LocalDateTime.now(), (byte) 3, 30000, "ytryrtyrt", "javaDeveloper", EmploymentType.FULL_TIME));
//        persistenceUtil.toMap(new Vacancy(8l,LocalDateTime.now(), LocalDateTime.now(), (byte) 3, 300, "ytryrtyrt", "javaDeveloper", EmploymentType.FULL_TIME));
//       vacancyRepository.toMap(new Vacancy(LocalDateTime.now(), LocalDateTime.now(), (byte) 3, 300, "ytryrtyrt", "javaDeveloper", EmploymentType.FULL_TIME));

//       vacancyRepository.create(new Vacancy(LocalDateTime.now(), LocalDateTime.now(), (byte) 3, 30000, "ytryrtyrt", "javaDeveloper", EmploymentType.FULL_TIME));
//        List vacancyExecute = vacancyRepository.getAll();
//        vacancyExecute.forEach(o -> {
//            log.info(((Vacancy) o).toString());
//        });

//        String s = persistenceUtil.convertToSql(new Vacancy());
//        log.info(s);
//        Vacancy vacancy = vacancyRepository.getById(333);
//        vacancy.setSalary(200);
//        vacancyRepository.update(vacancy);
//        User user = new User("email@mail.ru","11111111111",passwordEncoder.encode("1234"),true);
//     userRepository.create(user);
//        List userExecute = userRepository.getAll();
//        userExecute.forEach(o -> {
//            log.info(((User) o).toString());
//        });
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
