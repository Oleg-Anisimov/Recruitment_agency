package me.anisimov.agency.persistance.resolver;

import java.util.List;

public interface Resolver<ENTITY,TARGET>{
  List<TARGET> resolve(ENTITY entity);
}
