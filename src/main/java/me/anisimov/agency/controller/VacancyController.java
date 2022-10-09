package me.anisimov.agency.controller;

import me.anisimov.agency.domain.Vacancy;
import me.anisimov.agency.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vacancy")
public class VacancyController {
    @Autowired
    VacancyService vacancyService;
    @GetMapping
    public List<Vacancy> getAll(){
       return vacancyService.getAll();
    }
}
