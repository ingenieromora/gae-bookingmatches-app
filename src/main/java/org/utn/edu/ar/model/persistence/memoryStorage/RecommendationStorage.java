package org.utn.edu.ar.model.persistence.memoryStorage;

import org.utn.edu.ar.model.domain.Recommendation;
import org.utn.edu.ar.model.persistence.IRecommendationStorage;

import java.util.ArrayList;
import java.util.List;

public class RecommendationStorage implements IRecommendationStorage {

    private List<Recommendation> recommendations;

    public RecommendationStorage(){}

    public RecommendationStorage(List<Recommendation> l){ this.recommendations = l; }

    public List<Recommendation> getAll(){ return this.getRecommendations(); }

    public List<Recommendation> getForReceiver(Integer playerId){
        List<Recommendation> out = new ArrayList<>();

        for(Recommendation r : recommendations){
            if(r.getReceiverId() == playerId)
                out.add(r);
        }

        return out;
    }

    public List<Recommendation> getForEmitter(Integer playerId){
        List<Recommendation> out = new ArrayList<>();

        for(Recommendation r : recommendations){
            if(r.getEmitterId() == playerId)
                out.add(r);
        }

        return out;
    }

    public void create(Recommendation r) {
        r.setId(nextId());
        recommendations.add(r);
    }

    public void delete(Integer id){
        for(Recommendation r : recommendations){
            if(id == r.getId()){
                recommendations.remove(r);
                break;
            }
        }
    }

    public Recommendation getById(Integer id){
        for(Recommendation r : recommendations){
            if(r.getId() == id)
                return r;
        }
        return null;
    }


    public int nextId(){
        int max = 0;

        for(Recommendation r : recommendations){
            if(max < r.getId()) max = r.getId();
        }

        return max + 1;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
}