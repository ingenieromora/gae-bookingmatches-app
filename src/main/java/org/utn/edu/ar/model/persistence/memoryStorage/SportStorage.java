package org.utn.edu.ar.model.persistence.memoryStorage;

import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.persistence.ISportStorage;

import java.util.Iterator;
import java.util.List;

/**
 * Created by leandro.mora on 20/09/15.
 */
public class SportStorage implements ISportStorage {

    private List<Sport> sportList;

    public SportStorage() {
    }

    public SportStorage(List<Sport> sportList) {
        this.sportList = sportList;
    }

    @Override
    public List<Sport> getAllSports() {
        return sportList;
    }

    @Override
    public Sport getSportById(Integer id) {
        Sport outputSport = null;
        for (Sport currentSport : sportList) {
            if (id.equals(currentSport.getId())) {
                outputSport = currentSport;
            }
        }
        return outputSport;
    }

    @Override
    public boolean exists(String sportName) {
        boolean found = false;
        Iterator<Sport> itSport = sportList.iterator();
        while (itSport.hasNext() && !found) {
            Sport currentSport = itSport.next();
            if (sportName.toLowerCase().equals(currentSport.getName().toLowerCase())) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public void updateSport(Integer sportId, String sportName) {
        removeSport(sportId);
        createSport(sportName);
    }

    @Override
    public void createSport(String sportName) {
        int lastId = getLastId();
        Sport currentSport = new Sport(lastId, sportName);
        sportList.add(currentSport);
    }

    @Override
    public boolean exists(Integer sportId) {
        boolean found = false;
        Iterator<Sport> itSport = sportList.iterator();
        while (itSport.hasNext() && !found) {
            Sport currentSport = itSport.next();
            if (sportId.equals(currentSport.getId())) {
                found = true;
            }
        }
        return found;
    }

    public void removeSport(Integer sportId) {
        Sport sportToRemove = new Sport(sportId);
        sportList.remove(sportToRemove);
    }

    private int getLastId() {
        int lastInt = 0;

        for (Sport currentSport : sportList) {
            if (currentSport.getId() > lastInt) {
                lastInt = currentSport.getId();
            }
        }

        return lastInt + 1;
    }
}
