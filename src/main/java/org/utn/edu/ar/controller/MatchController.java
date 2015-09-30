package org.utn.edu.ar.controller;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.NotFoundException;
import org.utn.edu.ar.Constants;
import org.utn.edu.ar.model.MatchService;
import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.memoryStorage.MatchesStorage;

import java.util.ArrayList;

@Api(
        name = "matches",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = Constants.WEB_CLIENT_ID
)
public class MatchController {

    private MatchService service = new MatchService(new MatchesStorage(new ArrayList<Match>()));

    @ApiMethod(name = "matches.removePlayer", httpMethod = "delete")
    public void deletePlayer(@Named("id") Integer matchId, @Named("fbId") String fbId) throws NotFoundException {
        try {
            service.removePlayer(matchId, fbId);
        }
        catch(PlayerNotFoundException e){
            throw new NotFoundException("Player "+fbId+" does not exist in match "+matchId);
        } catch (MatchNotFoundException e) {
            throw new NotFoundException("Match "+matchId+" does not exist.");
        }
    }
}
