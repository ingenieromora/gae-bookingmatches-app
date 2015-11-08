package org.utn.edu.ar.model.persistence.memoryStorage;

import com.google.appengine.repackaged.com.google.api.client.util.Lists;
import com.google.appengine.repackaged.com.google.common.base.Function;
import com.google.appengine.repackaged.com.google.common.collect.FluentIterable;
import com.google.common.collect.Ordering;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.domain.Recommendation;
import org.utn.edu.ar.model.persistence.IRecommendationStorage;
import org.utn.edu.ar.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class RecommendationStorage implements IRecommendationStorage {

    private List<Recommendation> recommendations = Lists.newArrayList();

    public RecommendationStorage(){}

    public List<Recommendation> getAll(){ return this.getRecommendations(); }

    public List<Recommendation> getForReceiver(String playerId){
        List<Recommendation> out = new ArrayList<>();

        for(Recommendation r : recommendations){
            if(r.getReceiverId().equals(playerId))
                out.add(r);
        }

        return out;
    }

    public List<Recommendation> getForEmitter(String playerId){
        List<Recommendation> out = new ArrayList<>();

        for(Recommendation r : recommendations){
            if(r.getEmitterId().equals(playerId))
                out.add(r);
        }

        return out;
    }

    public Recommendation create(Recommendation r) {
        r.setId(nextId());
        recommendations.add(r);
        return r;
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
        try {
            return Utils.successor.apply(
                    Ordering.<Integer>natural().max(
                            FluentIterable.from(recommendations).transform(
                                    new Function<Recommendation, Integer>() {
                                        @Override
                                        public Integer apply(final Recommendation player) {
                                            return player.getId();
                                        }
                                    })));
        } catch (NoSuchElementException e) { return 1; }
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
}