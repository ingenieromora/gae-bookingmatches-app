package org.utn.edu.ar.model;

import org.utn.edu.ar.model.domain.Recommendation;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.IRecommendationStorage;
import org.utn.edu.ar.model.persistence.memoryStorage.RecommendationStorage;
import org.utn.edu.ar.model.persistence.gaeDatastore.GaeRecommendationStorage;
import org.utn.edu.ar.model.request.RecommendationRequest;

import java.util.List;

/**
 * Created by tom on 10/12/15.
 */
public class RecommendationService {

    private static RecommendationService instance = null;

    private IRecommendationStorage storage;

    private RecommendationService(){}

    public RecommendationService(IRecommendationStorage st){ this.storage = st; }

    public static RecommendationService getInstance() {
        if (instance == null) {
            synchronized (RecommendationService.class) {
                if (instance == null) {
                    instance = new RecommendationService(new GaeRecommendationStorage());
                }
            }
        }
        return instance;
    }

    public Recommendation create(final RecommendationRequest rec)
            throws MatchNotFoundException, PlayerNotFoundException {
        return storage.create(rec);
    }

    public List<Recommendation> getAll(){
       return storage.getAll();
    }

    public Recommendation getById(Long id){
        return storage.getById(id);
    }

    public void delete(Long id){
        storage.delete(id);
    }

    public List<Recommendation> getForEmitter(String playerId){
        return storage.getForEmitter(playerId);
    }

    public List<Recommendation> getForReceiver(String playerId){
        return storage.getForReceiver(playerId);
    }

    public void setStorage(final RecommendationStorage aRecommendationStorage){ storage = aRecommendationStorage; }
}
