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

    public Match getMatchById(Integer id);

    public void createMatch(Sport sport, int playersNeeded, LocalDate date, Player creator, double latitude, double longitude);

    public boolean exists(Integer id);

    public void updateMatch(Integer id, Sport sport, int playersNeeded, LocalDate date, Player creator, double latitude, double longitude);

    public void deleteMatch(Integer id);
}
