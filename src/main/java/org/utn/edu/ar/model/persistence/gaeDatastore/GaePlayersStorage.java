package org.utn.edu.ar.model.persistence.gaeDatastore;

import java.util.ArrayList;
import java.util.Date;

import com.google.appengine.api.datastore.*;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.*;
import com.googlecode.objectify.util.Closeable;
import org.utn.edu.ar.model.PlayerService;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.exceptions.player.PlayerAlreadyExistsException;
import org.utn.edu.ar.model.persistence.IPlayerStorage;
import org.utn.edu.ar.model.persistence.memoryStorage.PlayersStorage;

import java.util.List;
import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by norchow on 22/11/15.
 */
public class GaePlayersStorage extends PlayersStorage implements IPlayerStorage{
  static {
      ObjectifyService.begin();
      ObjectifyService.register(Player.class);
  }

  public List<Player> getAll(){
    return ofy().load().type(Player.class).list();
  }

  public Player getById(final Long id) throws PlayerNotFoundException {
    try {
      return ofy().load().type(Player.class).id(id).now();
    } catch (Exception e) {
      throw new PlayerNotFoundException(id);
    }
  }

  public Player create(String fbId) throws PlayerAlreadyExistsException {
    Player toAdd = new Player(fbId);
    ofy().save().entity(toAdd).now();
    return ofy().load().entity(toAdd).now();
  }

  public void remove(Long id) throws PlayerNotFoundException {
      ofy().delete().type(Player.class).id(id);
  }

  @Override
  public Player getByFacebookId(final String fbId) throws PlayerNotFoundException {
    Player optPlayer = ofy().load().type(Player.class).filter("fbId", fbId).first().now();
    if(optPlayer != null){
      return optPlayer;
    } else {
      try{
        return create(fbId);
      }catch(Exception e){
        return null;
      }
    }
  }

  public boolean exists(final String fbId){
    try{
      return (getByFacebookId(fbId) != null);
    }catch(PlayerNotFoundException e){
      return false;
    }
  }

  public boolean exists(final Long id){
    try{
      return (getById(id) != null);
    } catch(PlayerNotFoundException e){
      return false;
    }
  }
}
