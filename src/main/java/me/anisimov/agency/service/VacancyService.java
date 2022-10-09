package me.anisimov.agency.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.anisimov.agency.domain.Vacancy;
import me.anisimov.agency.persistance.repository.VacancyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyRepository vacancyRepository;
    public List<Vacancy> getAll(){
        return vacancyRepository.getAll();
    }
}
