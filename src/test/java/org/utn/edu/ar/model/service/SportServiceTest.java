package org.utn.edu.ar.model.service;

import com.google.api.server.spi.response.NotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.utn.edu.ar.model.SportService;
import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.exceptions.sport.SportNameAlreadyExistException;
import org.utn.edu.ar.model.exceptions.sport.SportNotFoundException;
import org.utn.edu.ar.model.persistence.memoryStorage.SportStorage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leandro.mora on 20/09/15.
 */
public class SportServiceTest {
    SportService service;

    @Before
    public void setup(){
        service = new SportService();
        Sport futbol = new Sport(1, "Futbol");
        Sport rugby = new Sport(2, "Rugby");

        List<Sport> sportList = new ArrayList<>();
        sportList.add(futbol);
        sportList.add(rugby);

        service.setStorage(new SportStorage(sportList));
    }

    @Test
    public void testGetAllSports() throws SportNotFoundException {
        Assert.assertEquals(2, service.getAllSports().size());
        Assert.assertEquals("Futbol", service.getAllSports().get(0).getName());
    }

    @Test(expected = SportNotFoundException.class)
    public void testNotFoundGetAllSport() throws SportNotFoundException {
        service.setStorage(new SportStorage());
        service.getAllSports();
    }

    @Test
    public void testGetSportById() throws SportNotFoundException {
        int inputId = 1;
        Assert.assertEquals("Futbol", service.getSportById(inputId).getName());
    }

    @Test(expected = SportNotFoundException.class)
    public void testNotFoundGetSportsById() throws SportNotFoundException {
        int inputId = 24;
        service.getSportById(inputId);
    }

    @Test
    public void testCreateSport() throws SportNameAlreadyExistException, SportNotFoundException {
        service.createSport("Tenis");
        Assert.assertEquals(3, service.getAllSports().size());
    }

    @Test(expected = SportNameAlreadyExistException.class)
    public void testNameAlreadeExistException() throws SportNameAlreadyExistException {
        service.createSport("Futbol");
    }

    @Test
    public void testUpdateSport() throws SportNotFoundException {
        service.updateSport(2,"Hockey");
        Assert.assertEquals("Hockey", service.getSportById(2).getName());
        Assert.assertEquals(2, service.getAllSports().size());
    }

    @Test( expected = SportNotFoundException.class )
    public void testNotFoundWhileUpdatingSport() throws SportNotFoundException {
        service.updateSport(4, "Hockey");
    }


    @Test
        public void testRemoveSport() throws SportNotFoundException, NotFoundException {
        service.removeSport(2);
        Assert.assertEquals(1, service.getAllSports().size());
    }


    @Test( expected = SportNotFoundException.class )
    public void testNotFoundWhileRemovingSport() throws SportNotFoundException {
        service.removeSport(4);
    }
}
