package me.anisimov.agency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.anisimov.agency.util.annotation.Column;
import me.anisimov.agency.util.annotation.Table;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="work_experience")
public class WorkExperience {
    @Column(name="id")
    private Long id;
    @Column(name="work_place")
    private String workPlace;
    @Column(name="basic_description")
    private String basicDescription;
}
