package org.utn.edu.ar.model.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.utn.edu.ar.model.domain.Sport;
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
        service = new SportService(new SportStorage());
    }

    @Test
    public void testGetAllSports(){
        Sport futbol = new Sport(1, "Futbol");
        Sport rugby = new Sport(2, "Rugby");

        List<Sport> sportList = new ArrayList<>();
        sportList.add(futbol);
        sportList.add(rugby);

        service.setStorage(new SportStorage(sportList));
        Assert.assertEquals(2, service.getAllSports().size());
        Assert.assertEquals("Futbol", service.getAllSports().get(0).getName());
    }

    @Test
    public void testGetAllSportsWithEmptyList(){
        service.setStorage(new SportStorage());
        Assert.assertEquals(0,service.getAllSports().size());
    }

}
