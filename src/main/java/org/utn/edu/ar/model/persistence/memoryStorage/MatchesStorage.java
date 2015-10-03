package org.utn.edu.ar.model.persistence.memoryStorage;

import org.joda.time.DateTime;
import org.utn.edu.ar.model.domain.Match;
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
public class MatchesStorage implements IMatchStorage {

    private List<Match> matches;

    public MatchesStorage(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public List<Match> getAllMatches() {
        return matches;
    }

    @Override
    public Match getMatchById(int id) {
//        return matches.stream().filter(m -> m.getId() == id).findFirst().get();
        Match match = null;
        for (Match m : matches) {
            if (m.getId() == id) match = m;
        }
        return match;
    }

    @Override
    public void createMatch(Sport sport, int playersNeeded, DateTime date, Player creator, Pair<Double, Double> location) {
        int id = matches.size() + 1;
        Match match = new Match(id, sport, playersNeeded, date, creator, location);
        matches.add(match);
    }

    @Override
    public boolean exists(int id) {
//        return matches.stream().anyMatch(m -> m.getId() == id);
        for (Match m : matches) {
            if (m.getId() == id) return true;
        }
        return false;
    }

    @Override
    public void updateMatch(int id, Sport sport, int playersNeeded, DateTime date, Player creator, Pair<Double, Double> location) {
        Match match = getMatchById(id);
        match.setSport(sport);
        match.setPlayersNeeded(playersNeeded);
        match.setDate(date);
        match.setCreator(creator);
        match.setLocation(location);
    }

    @Override
    public void deleteMatch(int id) {
        Match match = getMatchById(id);
        if (match != null) matches.remove(match);
    }

    @Override
    public void removePlayer(Integer matchId, String fbId)
            throws MatchNotFoundException, PlayerNotFoundException {
        Match match = null;
        for (Match m : matches){
            if(m.getId() == matchId){
                match = m;
                break;
            }
        }

        if(match == null) throw new MatchNotFoundException(matchId);

        match.removePlayer(fbId);
    }
}
