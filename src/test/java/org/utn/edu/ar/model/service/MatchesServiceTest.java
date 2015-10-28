package org.utn.edu.ar.model.service;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.utn.edu.ar.model.MatchService;
import org.utn.edu.ar.model.PlayerService;
import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.model.exceptions.player.PlayerAlreadyExistsException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.memoryStorage.MatchesStorage;
import org.utn.edu.ar.model.persistence.memoryStorage.PlayersStorage;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.util.Coordinates;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan pablo.
 */
public class MatchesServiceTest {

    private MatchService service;

    private MatchRequest rq = new MatchRequest(DateTime.now(), new Coordinates(4.0, 4.0), 1, 7, "1");

    @Before
    public void setup() {
        Match m1 = new Match(1, 1, 7, DateTime.now(), null, new Coordinates(1.0, 1.0));
        Match m2 = new Match(2, 1, 7, null, null, new Coordinates(2.0, 2.0));
        Match m3 = new Match(3, 2, 7, DateTime.now(), null, new Coordinates(3.0, 3.0));

        List<Match> matches = new ArrayList<>();
        matches.add(m1);
        matches.add(m2);
        matches.add(m3);

        List<Player> playerList = new ArrayList<Player>();
        Player tom = new Player(1, "tom");
        Player nico = new Player(2, "nico");
        Player leo = new Player(3, "leo");

        playerList.add(tom);
        playerList.add(nico);
        playerList.add(leo);

        PlayerService playerService = new PlayerService(new PlayersStorage(playerList));

        service = new MatchService(new MatchesStorage(matches), playerService);
    }

    @Test
    public void testGetAllMatchs() {
        Assert.assertEquals(3, service.getAllMatches().size());
    }

    @Test
    public void testGetMatchById() throws MatchNotFoundException {
        Match m2 = new Match(2, 1, 7, null, null, new Coordinates(2.0, 2.0));
        Assert.assertEquals(m2.toString(), service.getMatchById(2).toString());
    }

    @Test(expected = MatchNotFoundException.class)
    public void testGetMatchByIdNotFound() throws MatchNotFoundException {
        service.getMatchById(5);
    }

    @Test
    public void testCreateMatch() {
        Match created = service.createMatch(rq);
        Assert.assertEquals(4, service.getAllMatches().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateMatchWithLessParameterThanExpected() {
        MatchRequest rq2 = new MatchRequest(null, new Coordinates(4.0, 4.0), 1, 7, "1");
        service.createMatch(rq2);
    }

    @Test
    public void testUpdateMatch() throws MatchNotFoundException {
        service.updateMatch(2, 1, 5, DateTime.now(), null, new Coordinates(2.0, 5.0));
        Assert.assertEquals(5, service.getMatchById(2).getPlayersNeeded());
        Assert.assertEquals(5.0, service.getMatchById(2).getLocation().getLongitude(), 0.0);
    }

    @Test(expected = MatchNotFoundException.class)
    public void testUpdateMatchNotFound() throws MatchNotFoundException {
        service.updateMatch(5, 1, 5, DateTime.now(), null, new Coordinates(2.0, 5.0));
    }

    @Test
    public void testDeleteMatch() throws MatchNotFoundException {
        service.deleteMatch(3);
        Assert.assertEquals(2, service.getAllMatches().size());
    }


    @Test(expected = MatchNotFoundException.class)
    public void testDeleteMatchNotFound() throws MatchNotFoundException {
        service.deleteMatch(5);
    }


    @Test
    public void testAddExistentPlayerToMatch() throws MatchNotFoundException, PlayerAlreadyConfirmedException, PlayerNotFoundException, PlayerAlreadyExistsException {

        service.addPlayerToMatch(1, "tom");

        Player outputPlayer = service.getMatchById(1).getStarters().get(0);

        Player expectedPlayer = new Player(1, "tom");

        Assert.assertEquals(outputPlayer, expectedPlayer);
    }

    @Test
    public void testAddNonExistentPlayerToMatch() throws MatchNotFoundException, PlayerAlreadyConfirmedException, PlayerNotFoundException, PlayerAlreadyExistsException {

        service.addPlayerToMatch(1, "manuginobili");

        Player outputPlayer = service.getMatchById(1).getStarters().get(0);

        Player expectedPlayer = new Player(4, "manuginobili");

        Assert.assertEquals(outputPlayer, expectedPlayer);
    }

    @Test
    public void testRemovePlayerToMatch() throws MatchNotFoundException, PlayerAlreadyConfirmedException, PlayerNotFoundException, PlayerAlreadyExistsException {

        service.addPlayerToMatch(1, "messi");

        service.addPlayerToMatch(1, "romero");

        service.removePlayer(1, "messi");

        Player outputPlayer = service.getMatchById(1).getStarters().get(0);

        Player expectedPlayer = new Player(5, "romero");

        Assert.assertEquals(outputPlayer, expectedPlayer);
        int number_starters = service.getMatchById(1).getStarters().size();
        Assert.assertEquals(1, number_starters);
    }

}

