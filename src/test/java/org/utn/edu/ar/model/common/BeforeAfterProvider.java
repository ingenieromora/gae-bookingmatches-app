package org.utn.edu.ar.model.common;

import org.junit.After;
import org.junit.Before;
import org.utn.edu.ar.model.MatchService;
import org.utn.edu.ar.model.PlayerService;
import org.utn.edu.ar.model.RecommendationService;
import org.utn.edu.ar.model.SportService;
import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.player.PlayerAlreadyExistsException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.exceptions.sport.SportNameAlreadyExistException;
import org.utn.edu.ar.model.exceptions.sport.SportNotFoundException;
import org.utn.edu.ar.model.persistence.memoryStorage.MatchesStorage;
import org.utn.edu.ar.model.persistence.memoryStorage.PlayersStorage;
import org.utn.edu.ar.model.persistence.memoryStorage.RecommendationStorage;
import org.utn.edu.ar.model.persistence.memoryStorage.SportStorage;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.model.request.RecommendationRequest;
import org.utn.edu.ar.util.Coordinates;

import java.util.Date;

/**
 * Created by tom on 11/19/15.
 */
public class BeforeAfterProvider {

  protected static final String TOM = "Tom";
  protected static final String LEO = "Leo";
  protected static final String NICO = "Nico";
  protected static final String JUAN = "Juan";
  protected static final String FOOTBALL = "Football";
  protected static final String RUGBY = "Rugby";
  protected static Match M1 = null;

  protected PlayerService playerService = PlayerService.getInstance();
  protected SportService sportService = SportService.getInstance();
  protected MatchService matchService = MatchService.getInstance();
  protected RecommendationService recommendationService = RecommendationService.getInstance();

  @Before
  public void setup()
          throws PlayerAlreadyExistsException,
          SportNameAlreadyExistException,
          SportNotFoundException,
          PlayerNotFoundException,
          MatchNotFoundException {

    playerService.setStorage(new PlayersStorage());
    sportService.setStorage(new SportStorage());
    matchService.setStorage(new MatchesStorage());
    recommendationService.setStorage(new RecommendationStorage());

    // Create some players
    playerService.create(TOM);
    playerService.create(LEO);
    playerService.create(NICO);
    playerService.create(JUAN);

    // Create some sports
    sportService.createSport(FOOTBALL);
    sportService.createSport(RUGBY);

    // Create a Match
    Integer sportId = 1;
    Integer playersNeeded = 2;
    String creator = TOM;
    MatchRequest req = new MatchRequest(
            new Date(),
            new Coordinates(123.1d, 1234.2d),
            sportId,
            playersNeeded,
            creator
    );

    M1 = matchService.createMatch(req);

    // Create a few recommendations
    RecommendationRequest r1 = new RecommendationRequest(1, TOM, LEO);
    RecommendationRequest r2 = new RecommendationRequest(1, LEO, NICO);
    recommendationService.create(r1);
    recommendationService.create(r2);
  }

  @After
  public void cleanUp()
          throws MatchNotFoundException,
          PlayerNotFoundException,
          SportNotFoundException {

    // This is kind of like a CASCADE delete.
    recommendationService.delete(1);
    recommendationService.delete(2);

    matchService.deleteMatch(1);

    playerService.remove(1);
    playerService.remove(2);
    playerService.remove(3);

    sportService.removeSport(1);
    sportService.removeSport(2);
  }
}
