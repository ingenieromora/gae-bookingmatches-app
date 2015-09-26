package org.utn.edu.ar.model.service;

import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.persistence.IMatchStorage;

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

    public Match getMatchById(Integer id) throws MatchNotFoundException {
        Match match = storage.getMatchById(id);
        if (match == null) {
            throw new MatchNotFoundException(id);
        }
        return match;
    }

    public void createMatch(String sportName, int playersNeeded) {
        storage.createMatch(sportName, playersNeeded);
    }

    public boolean exists(Integer id) {
        return storage.exists(id);
    }

    public void updateMatch(Integer id, String sportName, int playersNeeded) throws MatchNotFoundException {
        if (!exists(id)) {
            throw new MatchNotFoundException(id);
        }
        storage.updateMatch(id, sportName, playersNeeded);
    }

    public void deleteMatch(Integer id) throws MatchNotFoundException {
        if (!exists(id)) {
            throw new MatchNotFoundException(id);
        }
        storage.deleteMatch(id);
    }
}
