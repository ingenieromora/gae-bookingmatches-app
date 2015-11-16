package org.utn.edu.ar.model.persistence.gaeDatastore;

import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.exceptions.player.PlayerAlreadyExistsException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.IPlayerStorage;

import java.util.List;

/**
 * Created by leandro.mora on 15/11/15.
 */
public class PlayerStorage implements IPlayerStorage {
    public static final String ENTITY_NAME = "Player";
    public static final String ATTRIBUTE_FB_ID = "fbID";

    @Override
    public List<Player> getAll() {
        return null;
    }

    @Override
    public Player getById(Integer id) throws PlayerNotFoundException {
        return null;
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public boolean exists(String fbId) {
        return false;
    }

    @Override
    public Player create(String fbId) throws PlayerAlreadyExistsException {
        return null;
    }

    @Override
    public void update(Integer id, String fbId) throws PlayerNotFoundException {

    }

    @Override
    public void remove(Integer id) throws PlayerNotFoundException {

    }

    @Override
    public Player getByFacebookId(String id) throws PlayerNotFoundException {
        return null;
    }
}
