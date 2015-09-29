package org.utn.edu.ar.controller;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import org.utn.edu.ar.Constants;
import org.utn.edu.ar.model.PlayerService;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.exceptions.player.PlayerAlreadyExistsException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.memoryStorage.PlayersStorage;

import java.util.ArrayList;
import java.util.List;

@Api(
        name = "players",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE}
)
public class PlayersController {

    private PlayerService service = new PlayerService(new PlayersStorage(new ArrayList<Player>()));

    @ApiMethod(name = "players.getAll", path = "/players", httpMethod = "get")
    public List<Player> getAll() throws PlayerNotFoundException { return service.getAll(); }

    @ApiMethod(name = "players.getById", path = "/players/{id}", httpMethod = "get")
    public Player getPlayer(@Named("id") Integer id) throws NotFoundException {
        try {
            return service.getById(id);
        } catch (PlayerNotFoundException e) {
            throw new NotFoundException("Player with "+id+" does not exist");
        }
    }

    @ApiMethod(name = "players.add", httpMethod = "post")
    public void createPlayer(@Named("fbId") String fbId) throws ConflictException {
        try {
            service.create(fbId);
        } catch (PlayerAlreadyExistsException e) {
            throw new ConflictException(e);
        }
    }

    @ApiMethod(name = "players.update", httpMethod = "put")
    public void updatePlayer(@Named("id") Integer id, @Named("fbId") String name) throws ConflictException {
        try {
            service.update(id, name);
        } catch (PlayerNotFoundException e) {
            throw new ConflictException(e);
        }
    }

    @ApiMethod(name = "players.remove", httpMethod = "delete")
    public void removePlayer(@Named("id") Integer id) throws NotFoundException {
        try {
            service.remove(id);
        } catch (Exception e) {
            throw new NotFoundException(e);
        }
    }
}
