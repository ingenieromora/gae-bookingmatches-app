package org.utn.edu.ar.model.persistence;

import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.domain.Sport;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by juan pablo.
 */
public interface IMatchStorage {

    public List<Match> getAllMatches();

    public Match getMatchById(int id);

    public void createMatch(Sport sport, int playersNeeded, LocalDate date, Player creator, double latitude, double longitude);

    public boolean exists(int id);

    public void updateMatch(int id, Sport sport, int playersNeeded, LocalDate date, Player creator, double latitude, double longitude);

    public void deleteMatch(int id);
}
