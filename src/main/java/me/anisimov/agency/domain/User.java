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
@Table(name="users")
public class User {
    @Column(name="id")
    private Long id;
    @Column(name="email")
    private String email;
    @Column(name="phone")
    private String phone;
    @Column(name="password")
    private String password;
    @Column(name="activated")
    private Boolean activated;
}
