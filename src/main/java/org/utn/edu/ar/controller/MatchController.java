package org.utn.edu.ar.controller;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.NotFoundException;
import org.utn.edu.ar.Constants;
import org.utn.edu.ar.model.MatchService;
import org.utn.edu.ar.model.PlayerService;
import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.model.exceptions.player.PlayerAlreadyExistsException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.memoryStorage.MatchesStorage;
import org.utn.edu.ar.model.persistence.memoryStorage.PlayersStorage;
import org.utn.edu.ar.model.request.FacebookIdRequest;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.model.request.NameRequest;

import java.util.ArrayList;
import java.util.List;

@Api(
        name = "matches",
        scopes = Constants.EMAIL_SCOPE,
        clientIds = Constants.WEB_CLIENT_ID
)
public class MatchController {

    private MatchService service = new MatchService(new MatchesStorage(new ArrayList<Match>()),new PlayerService(new PlayersStorage(new ArrayList<Player>())));

    @ApiMethod(
            name = "add",
            path = "matches",
            httpMethod = HttpMethod.POST
    )
    public void create(MatchRequest rq) {
        service.createMatch(rq);
    }


    @ApiMethod(
            name = "addPlayerToMatch",
            path = "matches/{id}/inscriptions",
            httpMethod = HttpMethod.POST
    )
    public void addPlayerToMatch(@Named("id") Integer matchId, NameRequest fbId) throws NotFoundException {
        try {
            service.addPlayerToMatch(matchId, fbId.getName());
        } catch (MatchNotFoundException e) {
            throw new NotFoundException("Match "+matchId+" does not exist.");
        } catch (PlayerAlreadyConfirmedException e) {
            e.printStackTrace();
        } catch (PlayerNotFoundException e) {
            e.printStackTrace();
        } catch (PlayerAlreadyExistsException e) {
            e.printStackTrace();
        }
    }


    @ApiMethod(
            name = "removePlayer",
            path = "matches/{id}/inscriptions/{fbId}",
            httpMethod = HttpMethod.DELETE
    )
    public void removePlayer(@Named("id") Integer matchId, @Named("fbId") String fbId) throws NotFoundException {
        try {
            service.removePlayer(matchId, fbId);
        }
        catch(PlayerNotFoundException e){
            throw new NotFoundException("Player "+fbId+" does not exist in match "+matchId);
        } catch (MatchNotFoundException e) {
            throw new NotFoundException("Match "+matchId.toString()+" does not exist.");
        }
    }


    @ApiMethod(
            name = "getAllMatches",
            path = "matches",
            httpMethod = HttpMethod.GET
    )
    public List<Match> getAllMatches(){
        return service.getAllMatches();
    }


    @ApiMethod(
            name = "getMatchesById",
            path = "matches/{id}",
            httpMethod = HttpMethod.GET
    )
    public Match getMatchesById(@Named("id") Integer id) throws NotFoundException {
        try {
            return service.getMatchById(id);
        } catch (MatchNotFoundException e) {
            throw new NotFoundException("Match "+id+" does not exist.");
        }
    }

    @ApiMethod(
            name = "getMatchByCreatedBy",
            //path = "matches",
            httpMethod = HttpMethod.GET
    )
    public Match getMatchByCreatedBy(@Named("createdBy") Integer id) throws NotFoundException {
        try {
            return service.getMatchByCreatedBy(id);
        } catch (MatchNotFoundException e) {
            throw new NotFoundException("Match "+id+" does not exist.");
        }
    }

    @ApiMethod(
            name = "matches.updateMatches",
            path = "matches/{id}",
            httpMethod = HttpMethod.PUT
    )
    public void updateMatches(@Named("id") Integer id, MatchRequest request) throws NotFoundException {
        try {
            service.updateMatch(id, request.getSportId(), request.getPlayersNeeded(),
                    request.getDate(),request.getCreatedBy(), request.getLocation());
        } catch (MatchNotFoundException e) {
            throw new NotFoundException("Match "+id+" does not exist.");
        }
    }

    @ApiMethod(
            name = "matches.deleteMatch",
            path = "matches/{id}",
            httpMethod = HttpMethod.DELETE
    )
    public void deleteMatch(@Named("id") Integer id) throws NotFoundException {
        try {
            service.deleteMatch(id);
        } catch (MatchNotFoundException e) {
            throw new NotFoundException("Match "+id+" does not exist.");
        }
    }
}
