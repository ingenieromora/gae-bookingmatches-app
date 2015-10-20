package org.utn.edu.ar.controller;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import org.utn.edu.ar.Constants;
import org.utn.edu.ar.model.PlayerService;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.exceptions.player.PlayerAlreadyExistsException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.memoryStorage.PlayersStorage;
import org.utn.edu.ar.model.request.Facebook;
import org.utn.edu.ar.model.request.FacebookIdRequest;
import org.utn.edu.ar.model.request.ValidateRequest;
import org.utn.edu.ar.model.response.MessageResponse;

import java.util.*;

@Api(
        name = "players",
        scopes = Constants.EMAIL_SCOPE,
        clientIds = Constants.WEB_CLIENT_ID
)
public class PlayersController {

    private PlayerService service = new PlayerService(new PlayersStorage(buildMockedPlayers()));

    /** The map holding FbId -> Access Token pairs, used as a cache to know if a player has authenticated or not.*/
    private Map<String, String> authenticationCache = new HashMap<String, String>();

    @ApiMethod(
            name = "getAll",
            path = "players",
            httpMethod = HttpMethod.GET
    )
    public List<Player> getAll() throws PlayerNotFoundException { return service.getAll(); }

    @ApiMethod(
            name = "getById",
            path = "players/{id}",
            httpMethod = HttpMethod.GET
    )
    public Player getPlayer(@Named("id") Integer id) throws NotFoundException {
        try {
            return service.getById(id);
        } catch (PlayerNotFoundException e) {
            throw new NotFoundException("Player with "+id+" does not exist");
        }
    }

    @ApiMethod(
            name = "add",
            path = "players",
            httpMethod = HttpMethod.POST
    )
    public void createPlayer(FacebookIdRequest rq) throws ConflictException {
        try {
            service.create(rq.getFbId());
        } catch (PlayerAlreadyExistsException e) {
            throw new ConflictException(e);
        }
    }

    @ApiMethod(
            name = "update",
            path = "players/{id}",
            httpMethod = HttpMethod.PUT
    )
    public void updatePlayer(@Named("id") Integer id, FacebookIdRequest rq) throws ConflictException {
        try {
            service.update(id, rq.getFbId());
        } catch (PlayerNotFoundException e) {
            throw new ConflictException(e);
        }
    }

    @ApiMethod(
            name = "remove",
            path = "players/{id}",
            httpMethod = HttpMethod.DELETE
    )
    public void removePlayer(@Named("id") Integer id) throws NotFoundException {
        try {
            service.remove(id);
        } catch (Exception e) {
            throw new NotFoundException(e);
        }
    }


    @ApiMethod(
            name = "validate",
            path = "players/validate",
            httpMethod = HttpMethod.POST
    )
    public MessageResponse validatePlayerWithFB(final ValidateRequest rq){
      String out;
      // Search inside cache if we haven't stored that Pair already.
      if(authenticationCache.containsKey(rq.getFbId()) &&
         authenticationCache.get(rq.getFbId()).equals(rq.getAccessToken()))
        out = "OK";
      else {
        out = Facebook.authenticate(rq);
        if(out.equals("OK"))
          authenticationCache.put(rq.getFbId(), rq.getAccessToken());
      }
      MessageResponse response = new MessageResponse();
      response.setMessage(out);
      return response;
    }


    private List<Player> buildMockedPlayers(){
        Player p1 = new Player(1, "Leo");
        Player p2 = new Player(2, "Tom");
        Player p3 = new Player(3, "Nico");
        List<Player> l = new ArrayList<>();
        l.add(p1);
        l.add(p2);
        l.add(p3);
        return l;
    }
}
