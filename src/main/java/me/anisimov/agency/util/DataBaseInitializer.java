package me.anisimov.agency.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.anisimov.agency.domain.Vacancy;
import me.anisimov.agency.persistance.DAO;
import me.anisimov.agency.persistance.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;


@Component
@Slf4j
@Profile("dev")
public class DataBaseInitializer {
    @Autowired
    DAO dao;
    @Autowired
    VacancyRepository vacancyRepository;
    public void init() throws IOException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        log.info("************");

        dao.execute("Truncate vacancy cascade",(rs)->{return null;});
        dao.execute("Truncate candidate cascade",(rs)->{return null;});
        dao.execute("Truncate employee",(rs)->{return null;});
        List<Vacancy> vacancies = getVacancies();
        for (int i = 0; i < vacancies.size() ; i++) {
            vacancyRepository.create(vacancies.get(i));
        }
        log.info(vacancies.toString());

    }
    private List<Vacancy> getVacancies() throws IOException {
        File resource = new File(this.getClass().getClassLoader().getResource("Vacancy.json").getFile());
        String json =new String(Files.readAllBytes(resource.toPath()));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        List<Vacancy> vacancies = objectMapper.readValue(json, new TypeReference<List<Vacancy>>() {
        });
        return vacancies;
    }
}
