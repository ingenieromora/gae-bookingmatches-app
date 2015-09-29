package org.utn.edu.ar.model.service;

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

    public Player getById(Integer id){ return storage.getById(id); }

    public void create(Integer id, String fbId) throws PlayerAlreadyExistsException {
        storage.create(fbId);
    }

    public void update(Integer id, String fbId) throws PlayerNotFoundException {
        storage.update(id, fbId);
    }

    public void remove(Integer id){
        storage.remove(id);
    }
}
