package org.utn.edu.ar.model.persistence;

import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.exceptions.player.PlayerAlreadyExistsException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;

import java.util.List;

/**
 * Created by leandro.mora on 20/09/15.
 */
public interface IPlayerStorage {

    public List<Player> getAll();

    public Player getById(Integer id) throws PlayerNotFoundException;

    public boolean exists(Integer id);

    public boolean exists(String fbId);

    public Player create(String fbId) throws PlayerAlreadyExistsException;

    public void update(Integer id, String fbId) throws PlayerNotFoundException;

    public void remove(Integer id) throws PlayerNotFoundException;

    public Player getByFacebookId(String id) throws PlayerNotFoundException;
}
