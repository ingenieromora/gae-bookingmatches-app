package org.utn.edu.ar.model;

import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.exceptions.player.PlayerAlreadyExistsException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.IPlayerStorage;
import org.utn.edu.ar.model.persistence.memoryStorage.PlayersStorage;
import org.utn.edu.ar.model.request.Facebook;
import org.utn.edu.ar.model.request.ValidateRequest;
import org.utn.edu.ar.model.response.MessageResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tom on 9/29/15.
 */
public class PlayerService {

  /** The map holding Access Token -> FbId pairs, used as a cache to know if a player has authenticated or not.*/
  private static Map<String, String> authenticationCache = new HashMap<String, String>();

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

  public boolean existsOnCache(String accessToken) { return authenticationCache.containsKey(accessToken); }

  public MessageResponse validate(final ValidateRequest rq) throws PlayerAlreadyExistsException {
      String out;
      // Search inside cache if we haven't stored that Pair already.
      if(authenticationCache.containsKey(rq.getFbId()) &&
              authenticationCache.get(rq.getAccessToken()).equals(rq.getFbId()))
          out = "OK";
      else {
          out = Facebook.authenticate(rq);
          if(out.equals("OK"))
              authenticationCache.put(rq.getAccessToken(), rq.getFbId());
      }

      if(!exists(rq.getFbId())) create(rq.getFbId());

      MessageResponse response = new MessageResponse();
      response.setMessage(out);
      return response;
  }

  public void setStorage(final PlayersStorage aPlayerStorage) { storage = aPlayerStorage; }
}
