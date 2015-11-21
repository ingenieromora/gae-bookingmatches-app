package org.utn.edu.ar.controller;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.googlecode.objectify.ObjectifyService;
import org.utn.edu.ar.Constants;
import org.utn.edu.ar.model.PlayerService;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.exceptions.player.PlayerAlreadyExistsException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;

import org.utn.edu.ar.model.request.FacebookIdRequest;
import org.utn.edu.ar.model.request.ValidateRequest;
import org.utn.edu.ar.model.response.MessageResponse;

import java.util.*;
import java.util.logging.Logger;

@Api(
      name = "players",
      scopes = Constants.EMAIL_SCOPE,
      clientIds = Constants.WEB_CLIENT_ID
)
public class PlayersController {

  private static final Logger logger = Logger.getLogger(PlayersController.class.getName());

  static {
    ObjectifyService.begin();
    ObjectifyService.register(Player.class);
  }

  private PlayerService service = PlayerService.getInstance();

  @ApiMethod(
          name = "getAll",
          path = "players/all",
          httpMethod = HttpMethod.GET
  )
  public List<Player> getAll() throws PlayerNotFoundException { return service.getAll(); }

  @ApiMethod(
          name = "getById",
          path = "players",
          httpMethod = HttpMethod.GET
  )
  public Player getPlayerById(@Nullable @Named("id") Long id,
                              @Nullable @Named("fbId") String fbId) throws NotFoundException, PlayerNotFoundException {
    Player out = null;
    if(id != null) out = service.getById(id);
    if(fbId != null) out = service.getByFacebookId(fbId);
    return out;
  }

  @ApiMethod(
          name = "add",
          path = "players",
          httpMethod = HttpMethod.POST
  )
  public Player createPlayer(FacebookIdRequest rq) throws ConflictException {
    try {
        return service.create(rq.getFbId());
    } catch (PlayerAlreadyExistsException e) {
        throw new ConflictException(e);
    }
  }

  @ApiMethod(
          name = "update",
          path = "players/{id}",
          httpMethod = HttpMethod.PUT
  )
  public void updatePlayer(@Named("id") Long id, FacebookIdRequest rq) throws ConflictException {
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
  public void removePlayer(@Named("id") Long id) throws NotFoundException {
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
  public MessageResponse validatePlayerWithFB(final ValidateRequest rq) throws PlayerAlreadyExistsException{
    return service.validate(rq);
  }
}
