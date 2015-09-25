package org.utn.edu.ar.model.service;

import com.google.api.server.spi.response.NotFoundException;
import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.exceptions.sport.SportNameAlreadyExistException;
import org.utn.edu.ar.model.exceptions.sport.SportAlreadyExistException;
import org.utn.edu.ar.model.exceptions.sport.SportNotFoundException;
import org.utn.edu.ar.model.persistence.ISportStorage;

import java.util.List;

/**
 * Created by leandro.mora on 20/09/15.
 */
public class SportService {

    private ISportStorage storage;

    public SportService(ISportStorage storage) { this.storage = storage; }

    public SportService() {}

    /**
     * Get all sports. In case the storage returns null, throws NotFoundException.
     * @return List<Sport>
     */
    public List<Sport> getAllSports() throws SportNotFoundException {
        List<Sport> outputList = storage.getAllSports();
        if(outputList == null){
            throw new SportNotFoundException("There are not sports stored");
        }
        return outputList;
    }

    public Sport getSportById(Integer id) throws SportNotFoundException {
        Sport storedSport = storage.getSportById(id);
        if(storedSport == null){
            throw new SportNotFoundException(id);
        }
        return storedSport;
    }

    public void setStorage(ISportStorage storage) {
        this.storage = storage;
    }

    public void createSport(String sportName) throws SportNameAlreadyExistException {
        if(storage.exists(sportName)){
            throw new SportNameAlreadyExistException("sport", sportName);
        }
        storage.createSport(sportName);
    }

    public void updateSport(Integer sportId, String sportName) throws SportNotFoundException {
        if(!storage.exists(sportId)){
            throw new SportNotFoundException(sportId);
        }
        storage.updateSport(sportId, sportName);
    }

    public void removeSport(Integer sportId) throws SportNotFoundException {
        if(!storage.exists(sportId)){
            throw new SportNotFoundException(sportId);
        }
        storage.removeSport(sportId);
    }
}
