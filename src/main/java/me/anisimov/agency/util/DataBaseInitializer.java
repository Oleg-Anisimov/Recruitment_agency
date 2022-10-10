package me.anisimov.agency.util;

import lombok.extern.slf4j.Slf4j;
import me.anisimov.agency.persistance.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("dev")
public class DataBaseInitializer {
    @Autowired
    DAO dao;
    public void init(){
        log.info("************");
//        dao.execute("Truncate vacancy",(rs)->{return null;});
//        dao.execute("Truncate candidate",(rs)->{return null;});
//        dao.execute("Truncate employee",(rs)->{return null;});
    }
}
