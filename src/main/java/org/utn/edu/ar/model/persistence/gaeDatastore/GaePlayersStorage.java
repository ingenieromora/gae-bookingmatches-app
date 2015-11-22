package org.utn.edu.ar.model.persistence.gaeDatastore;

import org.utn.edu.ar.model.persistence.IPlayerStorage;
import org.utn.edu.ar.model.persistence.memoryStorage.PlayersStorage;

/**
 * Created by leandro.mora on 21/11/15.
 */
public class GaePlayersStorage extends PlayersStorage implements IPlayerStorage {
    public static final String ENTITY_NAME = "SPORT";
    public static final String ATTRIBUTE_SPORT_NAME = "SPORT_NAME";
    public static final String ATTRIBUTE_SPORT_ID = "SPORT_ID";

}
