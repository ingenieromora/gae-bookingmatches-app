package org.utn.edu.ar.model.persistence.memoryStorage;

import com.google.appengine.repackaged.com.google.api.client.util.Lists;
import com.google.appengine.repackaged.com.google.common.base.Flag;
import com.google.appengine.repackaged.com.google.common.base.Function;
import com.google.appengine.repackaged.com.google.common.base.Predicate;
import com.google.appengine.repackaged.com.google.common.collect.FluentIterable;
import com.google.common.collect.Ordering;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.exceptions.player.PlayerAlreadyExistsException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.IPlayerStorage;
import org.utn.edu.ar.util.Utils;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by tom on 9/28/15.
 */
public class PlayersStorage implements IPlayerStorage {

    private List<Player> players = Lists.newArrayList();

    public PlayersStorage(){}

    public PlayersStorage(List<Player> l){ this.players = l; }

    public List<Player> getAll(){ return players; }

    public Player getById(Integer id) throws PlayerNotFoundException {
        for(Player p : players){
            if(p.getId() == id)
                return p;
        }
        throw new PlayerNotFoundException(id);
    }

    public Player create(String fbId) throws PlayerAlreadyExistsException {
      if(exists(fbId))
        throw new PlayerAlreadyExistsException(fbId);

      Player toAdd = new Player(nextId(), fbId);
      players.add(toAdd);
      return toAdd;
    }

    public void update(Integer id, String fbId) throws PlayerNotFoundException {
        for(Player p: players){
            if(p.getId() == id){
                p.setFbId(fbId);
                return;
            }
        }
        throw new PlayerNotFoundException(id);
    }

    public void remove(Integer id) throws PlayerNotFoundException {
        for(Player p : players){
            if(p.getId() == id){
                players.remove(p);
                return;
            }
        }
        throw new PlayerNotFoundException(id);
    }

    @Override
    public Player getByFacebookId(String fbId) throws PlayerNotFoundException {
        for(Player p: players){
            if(p.getFbId().equals(fbId)){
                return p;
            }
        }
        throw new PlayerNotFoundException(fbId);
    }

    public Integer nextId() {
      try {
        return Utils.successor.apply(
                Ordering.<Integer>natural().max(
                        FluentIterable.from(players).transform(
                                new Function<Player, Integer>() {
                                  @Override
                                  public Integer apply(final Player player) {
                                    return player.getId();
                                  }
                                })));
      } catch (NoSuchElementException e) { return 1; }
    }

    public boolean exists(final String fbId){
        return FluentIterable.from(players).anyMatch(new Predicate<Player>() {
          @Override
          public boolean apply(final Player player) {
            return player.getFbId().equals(fbId);
          }
        });
    }

    public boolean exists(final Integer id){
        return FluentIterable.from(players).anyMatch(new Predicate<Player>() {
          @Override
          public boolean apply(final Player player) {
            return player.getId() == id;
          }
        });
    }


}
