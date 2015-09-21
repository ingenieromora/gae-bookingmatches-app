package org.utn.edu.ar.model.service;

import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.persistence.ISportStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leandro.mora on 20/09/15.
 */
public class SportService {

    private ISportStorage storage;

    public SportService(ISportStorage storage) { this.storage = storage; }

    /**
     * Get all sports. In case the storage returns null, then returns an empty list.
     * @return List<Sport>
     */
    public List<Sport> getAllSports() {
        List<Sport> outputList = storage.getAllSports();
        if(outputList != null){ return outputList; }
        return new ArrayList<Sport>();
    }

    public void setStorage(ISportStorage storage) {
        this.storage = storage;
    }
}
