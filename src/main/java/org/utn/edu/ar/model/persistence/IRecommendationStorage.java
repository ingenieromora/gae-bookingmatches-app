package org.utn.edu.ar.model.persistence;

import org.utn.edu.ar.model.domain.Recommendation;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.request.RecommendationRequest;

import java.util.List;

public interface IRecommendationStorage {

    public List<Recommendation> getForEmitter(String playerId);

    public List<Recommendation> getForReceiver(String playerId);

    public Recommendation create(RecommendationRequest rec) throws PlayerNotFoundException, MatchNotFoundException;

    public void delete(Long id);

    public Recommendation getById(Long id);

    public List<Recommendation> getAll();

}
