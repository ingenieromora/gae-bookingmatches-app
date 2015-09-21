package org.utn.edu.ar.model.persistence.memoryStorage;

import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.persistence.ISportStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leandro.mora on 20/09/15.
 */
public class SportStorage implements ISportStorage{
    private List<Sport> sportList = new ArrayList<>();

    public SportStorage() {}

    public SportStorage(List<Sport> sportList) { this.sportList = sportList; }

    @Override
    public List<Sport> getAllSports() { return sportList; }
}
