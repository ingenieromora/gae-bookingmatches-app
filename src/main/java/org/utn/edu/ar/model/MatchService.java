package org.utn.edu.ar.model;

import org.joda.time.DateTime;
import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.IMatchStorage;
import org.utn.edu.ar.util.Pair;

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

    public void createMatch(Sport sport, int playersNeeded, Player creator, Pair<Double, Double> location) {
        storage.createMatch(sport, playersNeeded, DateTime.now(), creator, location);
    }

    public boolean exists(int id) {
        return storage.exists(id);
    }

    public void updateMatch(int id, Sport sport, int playersNeeded, org.joda.time.DateTime date, Player creator, Pair<Double, Double> location)
            throws MatchNotFoundException {

        if (!exists(id)) {
            throw new MatchNotFoundException(id);
        }
        storage.updateMatch(id, sport, playersNeeded, date, creator, location);
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
