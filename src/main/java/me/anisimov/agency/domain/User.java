package me.anisimov.agency.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.anisimov.agency.util.annotation.Column;
import me.anisimov.agency.util.annotation.Table;

import java.util.HashMap;
import java.util.Map;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "activated")
    private Boolean activated;

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> userParams = new HashMap<>();
        userParams.put("id",this.id);
        userParams.put("email", this.email);
        userParams.put("phone", this.phone);
        userParams.put("password", this.password);
        userParams.put("activated", this.activated);
        return userParams;
    }
}
