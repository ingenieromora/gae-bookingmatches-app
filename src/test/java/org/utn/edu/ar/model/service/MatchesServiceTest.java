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

//    private MatchRequest rq = new MatchRequest(new Date(), new Coordinates(4.0, 4.0), 1, 7, "1");
//
//    @Test
//    public void getAllTest() {
//        Assert.assertEquals(1, matchService.getAllMatches().size());
//    }
//
//    @Test
//    public void testGetMatchById() throws MatchNotFoundException, SportNotFoundException, PlayerNotFoundException {
//        Assert.assertEquals(TOM, matchService.getMatchById(1).getCreatedBy());
//    }
//
//    @Test(expected = MatchNotFoundException.class)
//    public void testGetMatchByIdNotFound() throws MatchNotFoundException {
//        matchService.getMatchById(5);
//    }
//
//    @Test
//    public void testCreateMatch() throws SportNotFoundException, PlayerNotFoundException {
//        Match created = matchService.createMatch(rq);
//        Assert.assertEquals(2, matchService.getAllMatches().size());
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testCreateMatchWithLessParameterThanExpected() throws SportNotFoundException, PlayerNotFoundException {
//        MatchRequest rq2 = new MatchRequest(null, new Coordinates(4.0, 4.0), 1, 7, "1");
//        matchService.createMatch(rq2);
//    }
//
//    @Test
//    public void testUpdateMatch() throws MatchNotFoundException, SportNotFoundException, PlayerNotFoundException {
//        matchService.updateMatch(2, 1, 5, new Date(), null, new Coordinates(2.0, 5.0));
//        Assert.assertEquals(5, matchService.getMatchById(2).getPlayersNeeded());
//        Assert.assertEquals(5.0, matchService.getMatchById(2).getLocation().getLongitude(), 0.0);
//    }
//
//    @Test(expected = MatchNotFoundException.class)
//    public void testUpdateMatchNotFound() throws MatchNotFoundException, SportNotFoundException, PlayerNotFoundException {
//        matchService.updateMatch(5, 1, 5, new Date(), null, new Coordinates(2.0, 5.0));
//    }
//
//    @Test
//    public void testDeleteMatch() throws MatchNotFoundException {
//        matchService.deleteMatch(1);
//        Assert.assertEquals(0, matchService.getAllMatches().size());
//    }
//
//
//    @Test(expected = MatchNotFoundException.class)
//    public void testDeleteMatchNotFound() throws MatchNotFoundException {
//        matchService.deleteMatch(5);
//    }
//
//
//    @Test
//    public void testAddExistentPlayerToMatch() throws MatchNotFoundException, PlayerAlreadyConfirmedException, PlayerNotFoundException, PlayerAlreadyExistsException {
//
//        matchService.addPlayerToMatch(1, "tom");
//
//        Player outputPlayer = matchService.getMatchById(1).getStarters().get(0);
//
//        Player expectedPlayer = new Player(1, "tom");
//
//        Assert.assertEquals(outputPlayer, expectedPlayer);
//    }
//
//    @Test
//    public void testAddNonExistentPlayerToMatch() throws MatchNotFoundException, PlayerAlreadyConfirmedException, PlayerNotFoundException, PlayerAlreadyExistsException {
//
//        matchService.addPlayerToMatch(1, "manuginobili");
//
//        Player outputPlayer = matchService.getMatchById(1).getStarters().get(0);
//
//        Player expectedPlayer = new Player(4, "manuginobili");
//
//        Assert.assertEquals(outputPlayer, expectedPlayer);
//    }
//
//    @Test
//    public void testRemovePlayerToMatch() throws MatchNotFoundException, PlayerAlreadyConfirmedException, PlayerNotFoundException, PlayerAlreadyExistsException {
//
//      matchService.addPlayerToMatch(1, "messi");
//
//      matchService.addPlayerToMatch(1, "romero");
//
//      matchService.removePlayer(1, "messi");
//
//      Player outputPlayer = matchService.getMatchById(1).getStarters().get(0);
//
//      Player expectedPlayer = new Player(5, "romero");
//
//        Assert.assertEquals(outputPlayer, expectedPlayer);
//        int number_starters = matchService.getMatchById(1).getStarters().size();
//        Assert.assertEquals(1, number_starters);
//    }
}

