package org.utn.edu.ar.model.persistence;

import org.joda.time.DateTime;
import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.domain.Sport;

import java.util.List;

/**
 * Created by juan pablo.
 */
public interface IMatchStorage {

    public List<Match> getAllMatches();

    public Match getMatchById(int id);

    public void createMatch(Sport sport, int playersNeeded, DateTime date, Player creator, double latitude, double longitude);

    public boolean exists(int id);

    public void updateMatch(int id, Sport sport, int playersNeeded, DateTime date, Player creator, double latitude, double longitude);

    public void deleteMatch(int id);
}
