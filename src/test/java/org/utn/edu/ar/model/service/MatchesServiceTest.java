package org.utn.edu.ar.model.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.utn.edu.ar.model.MatchService;
import org.utn.edu.ar.model.PlayerService;
import org.utn.edu.ar.model.SportService;
import org.utn.edu.ar.model.common.BeforeAfterProvider;
import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.model.exceptions.player.PlayerAlreadyExistsException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.exceptions.sport.SportNameAlreadyExistException;
import org.utn.edu.ar.model.exceptions.sport.SportNotFoundException;
import org.utn.edu.ar.model.persistence.memoryStorage.MatchesStorage;
import org.utn.edu.ar.model.persistence.memoryStorage.PlayersStorage;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.util.Coordinates;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by juan pablo.
 */
public class MatchesServiceTest extends BeforeAfterProvider {

  @Test
  public void addPlayerToMatchTest()
          throws PlayerNotFoundException, PlayerAlreadyConfirmedException, MatchNotFoundException, PlayerAlreadyExistsException {
    matchService.addPlayerToMatch(1, LEO);
    Assert.assertEquals(2, matchService.getMatchById(1).getStarters().size());
  }

  @Test
  public void exceedLimitTest() throws PlayerAlreadyExistsException, PlayerNotFoundException, PlayerAlreadyConfirmedException, MatchNotFoundException {
    matchService.addPlayerToMatch(1, NICO);
    matchService.addPlayerToMatch(1, LEO);
    Assert.assertEquals(1, matchService.getMatchById(1).getAlternates().size());
  }

  @Test
  public void hasPlayerTest() throws PlayerAlreadyExistsException, PlayerNotFoundException, MatchNotFoundException, PlayerAlreadyConfirmedException {
    matchService.addPlayerToMatch(1, LEO);
    Assert.assertTrue(matchService.hasPlayer(1, LEO));
    Assert.assertFalse(matchService.hasPlayer(1, JUAN));
  }

  @Test
  public void removePlayerFromMatch() throws PlayerAlreadyExistsException, PlayerNotFoundException, PlayerAlreadyConfirmedException, MatchNotFoundException {
    matchService.addPlayerToMatch(1, LEO);
    matchService.removePlayer(1, LEO);
    Assert.assertEquals(1, matchService.getMatchById(1).getStarters().size());
  }
}

