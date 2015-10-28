package org.utn.edu.ar.model.persistence;

import org.utn.edu.ar.model.domain.Recommendation;

import java.util.List;

/**
 * Created by tomas.duhourq on 9/29/15.
 */
public interface IRecommendationStorage {

    public List<Recommendation> getForEmitter(String playerId);

    public List<Recommendation> getForReceiver(String playerId);

    public Recommendation create(Recommendation rec);

    public void delete(Integer id);

    public Recommendation getById(Integer id);

    public List<Recommendation> getAll();

}
