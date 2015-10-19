package org.utn.edu.ar.model;

import org.utn.edu.ar.model.domain.Recommendation;
import org.utn.edu.ar.model.persistence.IRecommendationStorage;

import java.util.List;

/**
 * Created by tom on 10/12/15.
 */
public class RecommendationService {

    private IRecommendationStorage storage;

    public RecommendationService(){}

    public RecommendationService(IRecommendationStorage st){ this.storage = st; }

    public void create(Recommendation rec){
        storage.create(rec);
    }

    public List<Recommendation> getAll(){
       return storage.getAll();
    }

    public Recommendation getById(Integer id){
        return storage.getById(id);
    }

    public void delete(Integer id){
        storage.delete(id);
    }

    public List<Recommendation> getForEmitter(Integer playerId){
        return storage.getForEmitter(playerId);
    }

    public List<Recommendation> getForReceiver(Integer playerId){
        return storage.getForReceiver(playerId);
    }
}
