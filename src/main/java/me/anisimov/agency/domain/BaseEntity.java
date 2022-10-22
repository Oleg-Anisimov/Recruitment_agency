package me.anisimov.agency.domain;

import me.anisimov.agency.util.annotation.Column;

import java.util.Map;

public abstract class BaseEntity {
    public abstract Map<String,Object> toMap();
    @Column(name="id")
    public Long id;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }


}
