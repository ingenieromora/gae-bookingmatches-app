package org.utn.edu.ar.model.service;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.utn.edu.ar.model.PlayerService;
import org.utn.edu.ar.model.SportService;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.persistence.memoryStorage.PlayersStorage;
import org.utn.edu.ar.model.persistence.memoryStorage.SportStorage;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by tom on 10/3/15.
 */
public class PlayersServiceTest {

//    PlayerService service;
//
//    //TODO Complete tests
//
//    @Before
//    public void setup(){
//        List<Player> playerList = new ArrayList<Player>();
//        Player tom = new Player(1, "tom");
//        Player nico = new Player(2, "nico");
//        Player leo = new Player(3, "leo");
//
//        playerList.add(tom);
//        playerList.add(nico);
//        playerList.add(leo);
//
//        service = new PlayerService(new PlayersStorage(playerList));
//    }
//
//    @Test
//    public void getFacebookById() throws PlayerNotFoundException {
//
//        assertEquals(new Player(2, "nico"), service.getByFacebookId("nico"));
//
//    }
}
