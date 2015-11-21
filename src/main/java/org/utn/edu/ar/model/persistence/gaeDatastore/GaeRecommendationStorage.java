package org.utn.edu.ar.model.persistence.gaeDatastore;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
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

}
