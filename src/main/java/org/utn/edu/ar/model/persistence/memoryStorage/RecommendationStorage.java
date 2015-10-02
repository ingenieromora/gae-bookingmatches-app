package org.utn.edu.ar.model.persistence.memoryStorage;

import org.utn.edu.ar.model.domain.Recommendation;
import org.utn.edu.ar.model.persistence.IRecommendationStorage;

import java.util.ArrayList;
import java.util.List;

public class RecommendationStorage implements IRecommendationStorage {

    private List<Recommendation> recommendations;

    public List<Recommendation> getFor(Integer playerId){
        List<Recommendation> out = new ArrayList<>();

        for(Recommendation r : recommendations){
            if(r.getIdReceiver() == playerId)
                out.add(r);
        }

        return out;
    }

    public void create(Recommendation r) {
        recommendations.add(r);

    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
}