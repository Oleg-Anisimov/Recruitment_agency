package me.anisimov.agency.domain;

import java.util.Map;

public abstract class BaseEntity {
    public abstract Map<String,Object> toMap();
}
