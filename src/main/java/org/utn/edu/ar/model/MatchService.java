package org.utn.edu.ar.model;

import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.model.exceptions.player.PlayerAlreadyExistsException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.IMatchStorage;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.util.Coordinates;

import java.util.List;

/**
 * Created by juan pablo, leandro.mora
 */
public class MatchService {

    private IMatchStorage storage;
    private PlayerService playerService;

    public MatchService(IMatchStorage storage, PlayerService playerService) {

        this.storage = storage;
        this.playerService = playerService;
    }

    public List<Match> getAllMatches() {
        return storage.getAllMatches();
    }

    public Match getMatchById(int id) throws MatchNotFoundException {
        Match match = storage.getMatchById(id);
        if (match == null) throw new MatchNotFoundException(id);
        return match;
    }

    public Match getMatchByCreatedBy(int createdBy) throws MatchNotFoundException {
        Match match = storage.getMatchByCreatedBy(createdBy);
        if (match == null) throw new MatchNotFoundException(createdBy);
        return match;
    }

    public Match createMatch(MatchRequest rq) {
        return storage.createMatch(rq);
    }

    public boolean exists(int id) {
        return storage.exists(id);
    }


    public void updateMatch(int id, Integer sportId, Integer playersNeeded, org.joda.time.DateTime date, Integer creator, Coordinates location)
            throws MatchNotFoundException {

        if (!exists(id)) {
            throw new MatchNotFoundException(id);
        }
        storage.updateMatch(id, sportId, playersNeeded, date, creator, location);
    }

    public void deleteMatch(int id) throws MatchNotFoundException {
        if (!exists(id)) throw new MatchNotFoundException(id);
        storage.deleteMatch(id);
    }

    public void removePlayer(Integer matchId, String fbId)
            throws MatchNotFoundException, PlayerNotFoundException {
        storage.removePlayer(matchId, fbId);
    }

    public void addPlayerToMatch(Integer matchId, String playerFbId) throws MatchNotFoundException, PlayerAlreadyExistsException, PlayerNotFoundException, PlayerAlreadyConfirmedException {
        if (!exists(matchId)) throw new MatchNotFoundException(matchId);

        Player createdPlayer = getPlayer(playerFbId);

        storage.addPlayer(matchId, createdPlayer);
    }

    private Player getPlayer(String playerFbId) throws PlayerAlreadyExistsException, PlayerNotFoundException {
        if( !playerService.exists(playerFbId) ) { playerService.create(playerFbId); }

        Player createdPlayer = playerService.getByFacebookId(playerFbId);

        return createdPlayer;
    }


}
