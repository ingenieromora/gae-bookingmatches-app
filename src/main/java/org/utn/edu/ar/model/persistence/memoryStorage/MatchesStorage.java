package org.utn.edu.ar.model.persistence.memoryStorage;

import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.IMatchStorage;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.util.Coordinates;

import java.util.ArrayList;
import java.util.Date;
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
        Match match = null;
        for (Match m : matches) {
            if (m.getId() == id) match = m;
        }
        return match;
    }

    @Override
    public List<Match> getMatchByCreatedBy(String createdBy) {
        List<Match> createdBymatches = new ArrayList<>();

        for (Match m : matches) {
            if (m.getCreatedBy().equalsIgnoreCase(createdBy)) createdBymatches.add(m);
        }
        return createdBymatches;
    }

    @Override
    public Match createMatch(MatchRequest rq) {
        Match match = new Match(rq);
        match.setId(nextId());
        matches.add(match);
        return match;
    }

    @Override
    public boolean exists(int id) {
        for (Match m : matches) {
            if (m.getId() == id) return true;
        }
        return false;
    }

    @Override
    public void updateMatch(int id, Integer sportId, Integer playersNeeded, Date date, String createdBy, Coordinates location) {
        Match match = getMatchById(id);
        if ( sportId != null ) match.setSportId(sportId);
        if (playersNeeded != null) match.setPlayersNeeded(playersNeeded);
        if (date != null) match.setDate(date);
        if (createdBy != null) match.setCreatedBy(createdBy);
        if (location != null) match.setLocation(location);
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
