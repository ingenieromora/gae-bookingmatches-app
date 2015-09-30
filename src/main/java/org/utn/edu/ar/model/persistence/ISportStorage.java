package org.utn.edu.ar.model.persistence;

import org.utn.edu.ar.model.domain.Sport;

import java.util.List;

public interface ISportStorage {

    public List<Sport> getAllSports();

    public Sport getSportById(Integer id);

    public boolean exists(String sportName);

    public void createSport(String sportName);

    public boolean exists(Integer sportId);

    public void updateSport(Integer sportId, String sportName);

    public void removeSport(Integer sportId);
}
