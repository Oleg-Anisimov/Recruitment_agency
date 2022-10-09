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
@Table(name="skills")
public class Skills {
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
}
