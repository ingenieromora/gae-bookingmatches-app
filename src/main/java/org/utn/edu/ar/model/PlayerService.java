package org.utn.edu.ar.model;

import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.exceptions.player.PlayerAlreadyExistsException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.IPlayerStorage;

import java.util.List;

/**
 * Created by tom on 9/29/15.
 */
public class PlayerService {

    private IPlayerStorage storage;

    public PlayerService(IPlayerStorage storage){ this.storage = storage; }

    public PlayerService(){}

    public List<Player> getAll(){ return storage.getAll(); }

    public Player getById(Integer id) throws PlayerNotFoundException {
        return storage.getById(id);
    }

    public Player getByFacebookId(String fbId) throws PlayerNotFoundException {
        return storage.getByFacebookId(fbId);
    }

    public void create(String fbId) throws PlayerAlreadyExistsException {
        storage.create(fbId);
    }

    public void update(Integer id, String fbId) throws PlayerNotFoundException {
        storage.update(id, fbId);
    }

    public void remove(Integer id) throws PlayerNotFoundException {
        storage.remove(id);
    }

    public boolean exists(String fbId) { return storage.exists(fbId); }
}
