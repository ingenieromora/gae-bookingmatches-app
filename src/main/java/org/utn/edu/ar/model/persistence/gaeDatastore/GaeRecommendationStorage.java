package org.utn.edu.ar.model.persistence.gaeDatastore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.*;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.*;
import com.googlecode.objectify.util.Closeable;
import static com.googlecode.objectify.ObjectifyService.ofy;

import org.utn.edu.ar.model.RecommendationService;
import org.utn.edu.ar.model.domain.Recommendation;
import org.utn.edu.ar.model.persistence.IRecommendationStorage;
import org.utn.edu.ar.model.persistence.memoryStorage.RecommendationStorage;
import org.utn.edu.ar.model.request.RecommendationRequest;

import org.utn.edu.ar.model.MatchService;
import org.utn.edu.ar.model.PlayerService;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;

/**
 * Created by norchow on 23/11/15.
 */
public class GaeRecommendationStorage extends RecommendationStorage implements IRecommendationStorage{
    static {
        ObjectifyService.begin();
        ObjectifyService.register(Recommendation.class);
    }

    public List<Recommendation> getAll(){
        return ofy().load().type(Recommendation.class).list();
    }


     /** Get all recommendations for the given receiver id. */
    public List<Recommendation> getForReceiver(final String playerId){
        return ofy().load().type(Recommendation.class).filter("receiver.fbId", playerId).list();
    }

    /** Get all recommendations for the given emitter id. */
    public List<Recommendation> getForEmitter(final String playerId){
        return ofy().load().type(Recommendation.class).filter("emitter.fbId", playerId).list();
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
        r.setMatch(matchService.getMatchById(req.getMatchId()));
        r.setEmitter(playerService.getByFacebookId(req.getEmitterId()));
        r.setReceiver(playerService.getByFacebookId(req.getReceiverId()));

        ofy().save().entity(r).now();
        return ofy().load().entity(r).now();
    }

    public void delete(final Long id) {
        ofy().delete().type(Recommendation.class).id(id);
    }

    public Recommendation getById(final Long id){
        return ofy().load().type(Recommendation.class).id(id).now();
    }
}
