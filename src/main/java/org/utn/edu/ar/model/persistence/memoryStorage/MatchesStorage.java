package org.utn.edu.ar.model.persistence.memoryStorage;

import org.joda.time.DateTime;
import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.IMatchStorage;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.util.Coordinates;

import java.util.List;

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
    public void createMatch(MatchRequest rq) {
        Match match = new Match(rq);
        match.setId(nextId());
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
    public void updateMatch(int id, Integer sportId, int playersNeeded, DateTime date, Integer createdBy, Coordinates location) {
        Match match = getMatchById(id);
        match.setSportId(sportId);
        match.setPlayersNeeded(playersNeeded);
        match.setDate(date);
        match.setCreatedBy(createdBy);
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

    @Override
    public void addPlayer(Integer matchId, Player player) throws PlayerAlreadyConfirmedException {
        Match match = getMatchById(matchId);

        match.addPlayer(player);

    }

    private Integer nextId(){
        int lastInt = 0;

        for (Match match : matches) {
            if (match.getId() > lastInt) {
                lastInt = match.getId();
            }
        }

        return lastInt + 1;
    }
}
