package org.utn.edu.ar.model;

import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.IMatchStorage;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.util.Coordinates;

import java.util.List;

/**
 * Created by juan pablo.
 */
public class MatchService {

    private IMatchStorage storage;

    public MatchService(IMatchStorage storage) {
        this.storage = storage;
    }

    public List<Match> getAllMatches() {
        return storage.getAllMatches();
    }

    public Match getMatchById(int id) throws MatchNotFoundException {
        Match match = storage.getMatchById(id);
        if (match == null) throw new MatchNotFoundException(id);
        return match;
    }

    public void createMatch(MatchRequest rq) {
        storage.createMatch(rq);
    }

    public boolean exists(int id) {
        return storage.exists(id);
    }

    public void updateMatch(int id, Integer sportId, int playersNeeded, org.joda.time.DateTime date, Integer creator, Coordinates location)
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
}
