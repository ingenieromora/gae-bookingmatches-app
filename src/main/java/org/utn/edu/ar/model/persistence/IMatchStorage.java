package org.utn.edu.ar.model.persistence;

import org.joda.time.DateTime;
import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.util.Coordinates;

import java.util.List;

/**
 * Created by juan pablo, leandro.mora
 */
public interface IMatchStorage {

    public List<Match> getAllMatches();

    public Match getMatchById(int id);

    public Match createMatch(MatchRequest rq);

    public boolean exists(int id);

    public void updateMatch(int id, Integer sportId, Integer playersNeeded, DateTime date, String createdBy, Coordinates location);

    public void deleteMatch(int id);

    public void removePlayer(Integer matchId, String fbId)
            throws MatchNotFoundException, PlayerNotFoundException;

    public void addPlayer(Integer matchId, Player playerFbId) throws PlayerAlreadyConfirmedException;

    public Match getMatchByCreatedBy(String createdBy);
}
