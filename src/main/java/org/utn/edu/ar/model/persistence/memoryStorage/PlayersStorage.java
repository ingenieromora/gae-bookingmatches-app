package org.utn.edu.ar.model.persistence.memoryStorage;

import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.exceptions.player.PlayerAlreadyExistsException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.IPlayerStorage;

import java.util.List;

/**
 * Created by tom on 9/28/15.
 */
public class PlayersStorage implements IPlayerStorage {

    private List<Player> players;

    public PlayersStorage(){}

    public PlayersStorage(List<Player> l){ this.players = l; }

    public List<Player> getAll(){ return players; }

    public Player getById(Integer id){
        for(Player p : players){
            if(p.getId() == id)
                return p;
        }
        return null;
    }

    public void create(String fbId) throws PlayerAlreadyExistsException {
        if(!exists(fbId))
            players.add(new Player(nextId(), fbId));
        else
            throw new PlayerAlreadyExistsException(fbId);
    }

    public void update(Integer id, String fbId) throws PlayerNotFoundException {
        for(Player p: players){
            if(p.getId() == id){
                p.setFacebookId(fbId);
                return;
            }
        }
        throw new PlayerNotFoundException(id);
    }

    public void remove(Integer id){
        for(Player p : players){
            if(p.getId() == id){
                players.remove(p);
                break;
            }
        }
    }

    public Integer nextId(){
        int lastInt = 0;

        for (Player currentPlayer : players) {
            if (currentPlayer.getId() > lastInt) {
                lastInt = currentPlayer.getId();
            }
        }

        return lastInt + 1;
    }

    public boolean exists(String fbId){
        for(Player p : players){
            if(p.getFacebookId().equals(fbId))
                return true;
        }
        return false;
    }

    public boolean exists(Integer id){
        for(Player p : players){
            if(p.getId() == id)
                return true;
        }
        return false;
    }


}
