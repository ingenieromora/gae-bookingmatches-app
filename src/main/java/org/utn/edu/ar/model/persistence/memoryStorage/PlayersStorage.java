package org.utn.edu.ar.model.persistence.memoryStorage;

import com.google.appengine.repackaged.com.google.api.client.util.Lists;
import com.google.appengine.repackaged.com.google.common.base.Function;
import com.google.appengine.repackaged.com.google.common.base.Optional;
import com.google.appengine.repackaged.com.google.common.base.Predicate;
import com.google.appengine.repackaged.com.google.common.collect.FluentIterable;
import com.google.appengine.repackaged.com.google.common.collect.Iterables;
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

  public Player getById(final Long id) throws PlayerNotFoundException {
    try {
      return Iterables.find(players, new Predicate<Player>() {
        @Override
        public boolean apply(final Player player) {
          return player.getId() == id;
        }
      });
    } catch (Exception e) {
      throw new PlayerNotFoundException(id);
    }
  }

  public Player create(String fbId) throws PlayerAlreadyExistsException {
    if(exists(fbId))
      throw new PlayerAlreadyExistsException(fbId);

    Player toAdd = new Player(nextId(), fbId);
    players.add(toAdd);
    return toAdd;
  }

  public void update(Long id, String fbId) throws PlayerNotFoundException {
      for(Player p: players){
          if(p.getId() == id){
              p.setFbId(fbId);
              return;
          }
      }
      throw new PlayerNotFoundException(id);
  }

  public void remove(Long id) throws PlayerNotFoundException {
      for(Player p : players){
          if(p.getId() == id){
              players.remove(p);
              return;
          }
      }
      throw new PlayerNotFoundException(id);
  }


  @Override
  public Player getByFacebookId(final String fbId) throws PlayerNotFoundException {
    Player out;
    Optional<Player> optPlayer = Iterables.tryFind(players, new Predicate<Player>() {
      @Override
      public boolean apply(final Player player) {
        return player.getFbId().equals(fbId);
      }
    });
    if(optPlayer.isPresent()) out = optPlayer.get();
    else {
      out = new Player(nextId(), fbId);
      players.add(out);
    }
    return out;
  }

  public Long nextId() {
    try {
      return Utils.successor.apply(
        Ordering.<Long>natural().max(
          FluentIterable.from(players).transform(
            new Function<Player, Long>() {
              @Override
              public Long apply(final Player player) {
                return player.getId();
              }
            })));
    } catch (NoSuchElementException e) { return 1l; }
  }

  private boolean existsGeneric(final Predicate<Player> predicate) {
    return FluentIterable.from(players).anyMatch(predicate);
  }

  public boolean exists(final String fbId){
      return existsGeneric(new Predicate<Player>() {
        @Override
        public boolean apply(final Player player) {
          return player.getFbId().equals(fbId);
        }
      });
  }

  public boolean exists(final Long id){
      return existsGeneric(new Predicate<Player>() {
        @Override
        public boolean apply(final Player player) {
          return player.getId() == id;
        }
      });
  }
}
