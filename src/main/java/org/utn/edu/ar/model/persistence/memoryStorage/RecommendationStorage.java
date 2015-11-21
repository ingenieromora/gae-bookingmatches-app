package org.utn.edu.ar.model.persistence.memoryStorage;

import com.google.appengine.repackaged.com.google.api.client.util.Lists;
import com.google.appengine.repackaged.com.google.common.base.Function;
import com.google.appengine.repackaged.com.google.common.base.Predicate;
import com.google.appengine.repackaged.com.google.common.collect.FluentIterable;
import com.google.appengine.repackaged.com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;
import org.utn.edu.ar.model.MatchService;
import org.utn.edu.ar.model.PlayerService;
import org.utn.edu.ar.model.domain.Recommendation;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.IRecommendationStorage;
import org.utn.edu.ar.model.request.RecommendationRequest;
import org.utn.edu.ar.util.Utils;

import java.util.List;
import java.util.NoSuchElementException;

public class RecommendationStorage implements IRecommendationStorage {

    private List<Recommendation> recommendations = Lists.newArrayList();

    public RecommendationStorage(){}

    public List<Recommendation> getAll(){ return getRecommendations(); }


     /** Get all recommendations for the given receiver id. */
    public List<Recommendation> getForReceiver(final String playerId){
        return Lists.newArrayList(FluentIterable.from(recommendations)
        .filter(new Predicate<Recommendation>() {
            @Override
            public boolean apply(final Recommendation recommendation) {
                return recommendation.getReceiver().getFbId().equals(playerId);
            }
        }));
    }

    /** Get all recommendations for the given emitter id. */
    public List<Recommendation> getForEmitter(final String playerId){
        return Lists.newArrayList(FluentIterable.from(recommendations)
        .filter(new Predicate<Recommendation>() {
            @Override
            public boolean apply(final Recommendation recommendation) {
                return recommendation.getEmitter().getFbId().equals(playerId);
            }
        }));
    }

    /** Create a recommendation.
     *
     * The logic implies receiving a very simple request, but we must fetch
     * all 3 objects from the services.
     */
    public Recommendation create(final RecommendationRequest req)
            throws PlayerNotFoundException, MatchNotFoundException {
      PlayerService playerService = PlayerService.getInstance();
      MatchService matchService = MatchService.getInstance();
      Recommendation r = new Recommendation();
      r.setId(nextId());
      r.setMatch(matchService.getMatchById(req.getMatchId()));
      r.setEmitter(playerService.getByFacebookId(req.getEmitterId()));
      r.setReceiver(playerService.getByFacebookId(req.getReceiverId()));
      recommendations.add(r);
      return r;
    }

    public void delete(final Integer id) {
        Iterables.removeIf(recommendations, new Predicate<Recommendation>() {
          @Override
          public boolean apply(final Recommendation recommendation) {
            return recommendation.getId() == id;
          }
        });
    }

    public Recommendation getById(final Integer id){
        return Iterables.find(recommendations, new Predicate<Recommendation>() {
          @Override
          public boolean apply(final Recommendation recommendation) {
            return recommendation.getId() == id;
          }
        }, null);
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

    public void setRecommendations(List<Recommendation> someRecommendations) {
        recommendations = someRecommendations;
    }
}