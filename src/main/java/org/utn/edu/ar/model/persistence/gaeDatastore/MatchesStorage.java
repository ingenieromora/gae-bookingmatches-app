package org.utn.edu.ar.model.persistence.gaeDatastore;

import java.util.ArrayList;
import java.util.Date;

import com.google.appengine.api.datastore.*;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import org.utn.edu.ar.model.PlayerService;
import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.exceptions.sport.SportNotFoundException;
import org.utn.edu.ar.model.persistence.IMatchStorage;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.util.Coordinates;


import java.util.List;

/**
 * Created by leandro.mora on 07/11/15.
 */
public class MatchesStorage implements IMatchStorage {
    private static final String ENTITY_NAME_MATCH = "MATCH";
    private static final String ATTRIBUTE_STARTERS = "starters";
    private static final String ATTRIBUTE_CREATED_BY = "created_by";
    private static final String ATTRIBUTE_PLAYERS_NEEDED = "players_needed";
    private static final String ATTRIBUTE_DATE = "date";
    private static final String ATTRIBUTE_LOCATION = "location";
    private static final String ATTRIBUTE_ALTERNATES= "alternates";

    @Override
    public List<Match> getAllMatches() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Query q = new Query(ENTITY_NAME_MATCH);

        PreparedQuery pq = datastore.prepare(q);

        List<Match> matchesList = new ArrayList<Match>();
//        for (Entity result : pq.asIterable()) {
//            String createdBy = (String) result.getProperty(ATTRIBUTE_CREATED_BY);
//            Coordinates location = (Coordinates) result.getProperty(ATTRIBUTE_LOCATION);
//            Date date = (Date) result.getProperty(ATTRIBUTE_DATE);
//            Key key = result.getKey();
//            Match match = new Match(key.getId(), );
//            matchesList.add(match);
//        }

        return matchesList;
    }

    @Override
    public Match getMatchById(int id) {
        return null;
    }

    @Override
    public Match createMatch(MatchRequest rq) throws SportNotFoundException, PlayerNotFoundException {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Match match = new Match(rq);

        Entity entityMatch = new Entity(ENTITY_NAME_MATCH);

        entityMatch.setProperty(ATTRIBUTE_CREATED_BY, match.getCreatedBy());

        entityMatch.setProperty(ATTRIBUTE_LOCATION, match.getLocation());

        entityMatch.setProperty(ATTRIBUTE_DATE, match.getDate());
//      TODO A la espera de la respuesta de Rossenbolt
//        //TODO HACER QUE CADA PLAYER SEA UNA ENTIDAD EN SI, Y EMBEBERLO
//        List<Player> starters = Lists.newArrayList(PlayerService.getInstance().
//                getByFacebookId(rq.getCreatedBy()));
//
//       for(Player player: starters){
//
//           EmbeddedEntity playerEmbedded = new EmbeddedEntity();
//           playerEmbedded.setProperty(PlayerStorage.ATTRIBUTE_FB_ID, player.getFbId());
//           playerEmbedded.setProperty("playerType", "starter");
//           playerEmbedded.setKey();
//       }

//        entityMatch.setProperty(ATTRIBUTE_STARTERS, starters);

        datastore.put(entityMatch);

        return match;
    }

    @Override
    public boolean exists(int id) {
        return false;
    }

    @Override
    public void updateMatch(int id, Integer sportId, Integer playersNeeded, Date date, String createdBy, Coordinates location) {

    }

    @Override
    public void deleteMatch(int id) {

    }

    @Override
    public void removePlayer(Integer matchId, String fbId) throws MatchNotFoundException, PlayerNotFoundException {

    }

    @Override
    public void addPlayer(Integer matchId, Player playerFbId) throws PlayerAlreadyConfirmedException {

    }

    @Override
    public List<Match> getMatchByCreatedBy(String createdBy) throws PlayerNotFoundException {
        return null;
    }
}
