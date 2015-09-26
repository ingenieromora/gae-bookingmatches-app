package org.utn.edu.ar.model.service;

import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.persistence.IMatchStorage;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by juan pablo.
 */
public class MatchService {

    private IMatchStorage storage;

    public MatchService(IMatchStorage storage) {
        this.storage = storage;
    }

    public void setStorage(IMatchStorage storage) {
        this.storage = storage;
    }

    public List<Match> getAllMatches() throws MatchNotFoundException {
        List<Match> output = storage.getAllMatches();
        if (output == null) {
            throw new MatchNotFoundException("There are no matches stored");
        }
        return output;
    }

    public Match getMatchById(int id) throws MatchNotFoundException {
        Match match = storage.getMatchById(id);
        if (match == null) {
            throw new MatchNotFoundException(id);
        }
        return match;
    }

    public void createMatch(Sport sport, int playersNeeded, Player creator, double latitude, double longitude) {
        storage.createMatch(sport, playersNeeded, LocalDate.now(), creator, latitude, longitude);
    }

    public boolean exists(int id) {
        return storage.exists(id);
    }

    public void updateMatch(int id, Sport sport, int playersNeeded, Player creator, double latitude, double longitude)
            throws MatchNotFoundException {

        if (!exists(id)) {
            throw new MatchNotFoundException(id);
        }
        storage.updateMatch(id, sport, playersNeeded, LocalDate.now(), creator, latitude, longitude);
    }

    public void deleteMatch(int id) throws MatchNotFoundException {
        if (!exists(id)) {
            throw new MatchNotFoundException(id);
        }
        storage.deleteMatch(id);
    }
}
