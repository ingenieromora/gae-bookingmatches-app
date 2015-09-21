package org.utn.edu.ar.model.persistence;

import org.utn.edu.ar.model.domain.Sport;

import java.util.List;

/**
 * Created by leandro.mora on 20/09/15.
 */
public interface ISportStorage {

    public List<Sport> getAllSports();
}
