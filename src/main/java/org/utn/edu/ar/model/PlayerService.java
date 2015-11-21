package org.utn.edu.ar.model;

import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.exceptions.player.PlayerAlreadyExistsException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.IPlayerStorage;
import org.utn.edu.ar.model.persistence.memoryStorage.PlayersStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tom on 9/29/15.
 */
public class PlayerService {
    private static PlayerService instance = null;

    private IPlayerStorage storage;

    public PlayerService(IPlayerStorage storage){ this.storage = storage; }

    public static PlayerService getInstance() {
        if (instance == null) {
            synchronized (PlayerService.class) {
                if (instance == null) {
                    instance = new PlayerService(new PlayersStorage());
                }
            }
        }
        return instance;
    }

    public List<Player> getAll(){ return storage.getAll(); }

    public Player getById(Integer id) throws PlayerNotFoundException {
        return storage.getById(id);
    }

    public Player getByFacebookId(String fbId) throws PlayerNotFoundException {
        return storage.getByFacebookId(fbId);
    }

    public Player create(String fbId) throws PlayerAlreadyExistsException {
        return storage.create(fbId);
    }

    public void update(Integer id, String fbId) throws PlayerNotFoundException {
        storage.update(id, fbId);
    }

    public void remove(Integer id) throws PlayerNotFoundException {
        storage.remove(id);
    }

    public boolean exists(String fbId) { return storage.exists(fbId); }
}
