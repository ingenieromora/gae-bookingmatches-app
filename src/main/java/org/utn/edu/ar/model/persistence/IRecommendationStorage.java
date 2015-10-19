package org.utn.edu.ar.model.persistence;

import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.domain.Recommendation;

import java.util.List;

/**
 * Created by tomas.duhourq on 9/29/15.
 */
public interface IRecommendationStorage {

    public List<Recommendation> getForEmitter(Integer playerId);

    public List<Recommendation> getForReceiver(Integer playerId);

    public void create(Recommendation rec);

    public void delete(Integer id);

    public Recommendation getById(Integer id);

    public List<Recommendation> getAll();

}
