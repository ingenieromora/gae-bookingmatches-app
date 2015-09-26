package org.utn.edu.ar.model.persistence;

import org.utn.edu.ar.model.domain.Match;

import java.util.List;

/**
 * Created by juan pablo.
 */
public interface IMatchStorage {

    public List<Match> getAllMatches();

    public Match getMatchById(Integer id);

    public void createMatch(String sportName, int playersNeeded);

    public boolean exists(Integer id);

    public void updateMatch(Integer id, String sportName, int playersNeeded);

    public void deleteMatch(Integer id);
}
