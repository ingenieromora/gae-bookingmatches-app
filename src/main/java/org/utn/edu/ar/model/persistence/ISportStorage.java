package org.utn.edu.ar.model.persistence;

import org.utn.edu.ar.model.domain.Sport;

import java.util.List;

public interface ISportStorage {

    public List<Sport> getAllSports();

    public Sport getSportById(Long id);

    public boolean exists(Long sportId);
    public boolean exists(String sportName);

    public Sport createSport(String sportName);

    public void updateSport(Long sportId, String sportName);

    public void removeSport(Long sportId);
}
