package org.utn.edu.ar.model.service;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.utn.edu.ar.model.MatchService;
import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.persistence.memoryStorage.MatchesStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan pablo.
 */
public class MatchesServiceTest {

    private MatchService service;

    private Match m1 = new Match(1, null, 7, DateTime.now(), null, 1.0, 1.0);
    private Match m2 = new Match(2, null, 7, DateTime.now(), null, 2.0, 2.0);
    private Match m3 = new Match(3, null, 7, DateTime.now(), null, 3.0, 3.0);

    @Before
    public void setup() {
        List<Match> matches = new ArrayList<>();
        matches.add(m1);
        matches.add(m2);
        matches.add(m3);
        service = new MatchService(new MatchesStorage(matches));
    }

    @Test
    public void testGetAllMatchs() {
        Assert.assertEquals(3, service.getAllMatches().size());
    }

    @Test
    public void testGetMatchById() throws MatchNotFoundException {
        Assert.assertEquals(m2, service.getMatchById(2));
    }

    @Test(expected = MatchNotFoundException.class)
    public void testGetMatchByIdNotFound() throws MatchNotFoundException {
        service.getMatchById(5);
    }

    @Test
    public void testCreateMatch() {
        service.createMatch(null, 7, null, 4.0, 4.0);
        Assert.assertEquals(4, service.getAllMatches().size());
    }

    @Test
    public void testUpdateMatch() throws MatchNotFoundException {
        service.updateMatch(2, null, 5, DateTime.now(), null, 2.0, 5.0);
        Assert.assertEquals(5, service.getMatchById(2).getPlayersNeeded());
        Assert.assertEquals(5.0, service.getMatchById(2).getLongitude(), 0.0);
    }

    @Test(expected = MatchNotFoundException.class)
    public void testUpdateMatchNotFound() throws MatchNotFoundException {
        service.updateMatch(5, null, 5, DateTime.now(), null, 2.0, 5.0);
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
}
