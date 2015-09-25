package org.utn.edu.ar.model.persistence.mysqlStorage;

import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.persistence.ISportStorage;

import java.util.List;

/**
 * Created by leandro.mora on 20/09/15.
 */
public class SportStorage implements ISportStorage{

    @Override
    public List<Sport> getAllSports() {
        //TODO CODE IT
        return null;
    }

    @Override
    public Sport getSportById(Integer id) {
        //TODO CODE IT
        return null;
    }

    @Override
    public boolean exists(String sportName) {
        //TODO CODE IT
        return false;
    }

    @Override
    public void createSport(String sportName) {
        //TODO CODE IT
    }

    @Override
    public boolean exists(Integer sportId) {
        //TODO CODE IT
        return false;
    }

    @Override
    public void updateSport(Integer sportId, String sportName) {
        //TODO CODE IT
    }

    @Override
    public void removeSport(Integer sportId) {

    }

}
