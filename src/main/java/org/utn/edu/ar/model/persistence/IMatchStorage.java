package org.utn.edu.ar.model.persistence;

import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.exceptions.sport.SportNotFoundException;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.util.Coordinates;

import java.util.Date;
import java.util.List;

/**
 * Created by juan pablo, leandro.mora
 */
public interface IMatchStorage {

    public List<Match> getAllMatches();

    public Match getMatchById(Long id);

    public Match createMatch(MatchRequest rq, Sport sport, Player createdBy) throws SportNotFoundException, PlayerNotFoundException;

    public boolean exists(Long id);

    public void updateMatch(Long id, Long sportId, Integer playersNeeded, Date date, String createdBy, Coordinates location) throws SportNotFoundException, PlayerNotFoundException;

    public void deleteMatch(Long id);

    public void removePlayer(Long matchId, String fbId)
            throws MatchNotFoundException, PlayerNotFoundException;

    public void addPlayer(Long matchId, Player playerFbId) throws PlayerAlreadyConfirmedException;

    public Boolean hasPlayer(Long matchId, Player playerFbId);

    public List<Match> getMatchByCreatedBy(String createdBy) throws PlayerNotFoundException;

    public List<Match> getMatchesInscriptionsBy(String fbId) throws PlayerNotFoundException;
}
