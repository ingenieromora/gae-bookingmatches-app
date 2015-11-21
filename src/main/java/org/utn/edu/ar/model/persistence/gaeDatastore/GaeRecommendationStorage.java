package org.utn.edu.ar.model.persistence.gaeDatastore;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Query;
import org.utn.edu.ar.model.domain.Recommendation;
import org.utn.edu.ar.model.persistence.IRecommendationStorage;
import org.utn.edu.ar.model.persistence.memoryStorage.RecommendationStorage;

import java.util.List;

/**
 * Created by tom on 11/21/15.
 */
public class GaeRecommendationStorage extends RecommendationStorage implements IRecommendationStorage {
  private static final String ENTITY_NAME_RECOMMENDATION = "RECOMMENDATION";
  private static final String ATTRIBUTE_MATCH = "match";
  private static final String ATTRIBUTE_EMITTER = "emitter";
  private static final String ATTRIBUTE_RECEIVER = "receiver";

  private final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

  public List<Recommendation> getAll() {
//    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//
//    Query q = new Query(ENTITY_NAME_RECOMMENDATION);
//
//    PreparedQuery pq = datastore.prepare(q);
//
//    List<Match> matchesList = new ArrayList<Match>();
////        for (Entity result : pq.asIterable()) {
////            String createdBy = (String) result.getProperty(ATTRIBUTE_CREATED_BY);
////            Coordinates location = (Coordinates) result.getProperty(ATTRIBUTE_LOCATION);
////            Date date = (Date) result.getProperty(ATTRIBUTE_DATE);
////            Key key = result.getKey();
////            Match match = new Match(key.getId(), );
////            matchesList.add(match);
////        }
//
//    return matchesList;
//  }
  }
}
