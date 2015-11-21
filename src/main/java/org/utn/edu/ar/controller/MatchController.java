package org.utn.edu.ar.controller;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.NotFoundException;
import com.googlecode.objectify.ObjectifyService;
import org.utn.edu.ar.Constants;
import org.utn.edu.ar.model.MatchService;
import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.model.exceptions.player.PlayerAlreadyExistsException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.exceptions.sport.SportNotFoundException;
import org.utn.edu.ar.model.request.FacebookIdRequest;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.model.response.MessageResponse;

import java.util.List;

@Api(
        name = "matches",
        scopes = Constants.EMAIL_SCOPE,
        clientIds = Constants.WEB_CLIENT_ID
)
public class MatchController {

    private MatchService service = MatchService.getInstance();

    static {
        ObjectifyService.begin();
        ObjectifyService.register(Match.class);
    }

    @ApiMethod(
            name = "add",
            path = "matches",
            httpMethod = HttpMethod.POST
    )
    public Match create(MatchRequest rq) throws SportNotFoundException, PlayerNotFoundException {
        return service.createMatch(rq);
    }


    @ApiMethod(
            name = "addPlayerToMatch",
            path = "matches/{id}/inscriptions",
            httpMethod = HttpMethod.POST
    )
    public void addPlayerToMatch(@Named("id") Long matchId, FacebookIdRequest fbId) throws NotFoundException {
        try {
            service.addPlayerToMatch(matchId, fbId.getFbId());
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
    public void removePlayer(@Named("id") Long matchId, @Named("fbId") String fbId) throws NotFoundException {
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
            name = "hasPlayer",
            path = "matches/{id}/inscriptions/{fbId}",
            httpMethod = HttpMethod.GET
    )
    public MessageResponse hasPlayer(@Named("id") Long matchId, @Named("fbId") String fbId) throws PlayerAlreadyExistsException, PlayerNotFoundException{
        String response;
        if(service.hasPlayer(matchId, fbId)){
            response = "YES";
        }else{
            response = "NO";
        }
        return new MessageResponse(response);
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
            name = "getMatchById",
            path = "matches/{id}",
            httpMethod = HttpMethod.GET
    )
    public Match getMatchById(@Named("id") Long id) throws NotFoundException {
        try {
            return service.getMatchById(id);
        } catch (MatchNotFoundException e) {
            throw new NotFoundException("Match "+id+" does not exist.");
        }
    }

    @ApiMethod(
            name = "getMatchByCreatedBy",
            path = "matches/createdBy/{createdBy}",
            httpMethod = HttpMethod.GET
    )
    public List<Match> getMatchByCreatedBy(@Named("createdBy") String id) throws PlayerNotFoundException {
        return service.getMatchByCreatedBy(id);
    }

    @ApiMethod(
            name = "getMatchesInscriptionsBy",
            path = "matches/inscriptions/enrolled/{fbId}",
            httpMethod = HttpMethod.GET
    )
    public List<Match> getMatchesInscriptionsBy(@Named("fbId") String fbId) throws PlayerNotFoundException {
        return service.getMatchesInscriptionsBy(fbId);
    }

    @ApiMethod(
            name = "matches.updateMatches",
            path = "matches/{id}",
            httpMethod = HttpMethod.PUT
    )
    public void updateMatches(@Named("id") Long id, MatchRequest request) throws NotFoundException, SportNotFoundException {
        try {
            service.updateMatch(id, request.getSportId(), request.getPlayersNeeded(),
                    request.getDate(),request.getCreatedBy(), request.getLocation());
        } catch (MatchNotFoundException e) {
            throw new NotFoundException("Match "+id+" does not exist.");
        } catch (PlayerNotFoundException e) {
            throw new NotFoundException("Player with creator with fbId"+request.getCreatedBy()+" does not exist.");
        }
    }

    @ApiMethod(
            name = "matches.deleteMatch",
            path = "matches/{id}",
            httpMethod = HttpMethod.DELETE
    )
    public void deleteMatch(@Named("id") Long id) throws NotFoundException {
        try {
            service.deleteMatch(id);
        } catch (MatchNotFoundException e) {
            throw new NotFoundException("Match "+id+" does not exist.");
        }
    }
}
