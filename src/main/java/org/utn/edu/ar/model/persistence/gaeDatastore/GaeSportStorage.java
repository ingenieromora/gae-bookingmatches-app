package org.utn.edu.ar.model.persistence.gaeDatastore;

import java.util.ArrayList;
import java.util.Date;

import com.google.appengine.api.datastore.*;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.*;
import com.googlecode.objectify.util.Closeable;
import org.utn.edu.ar.model.PlayerService;
import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.exceptions.sport.SportNotFoundException;
import org.utn.edu.ar.model.persistence.ISportStorage;
import org.utn.edu.ar.model.persistence.memoryStorage.SportStorage;
import com.googlecode.objectify.util.Closeable;
import org.utn.edu.ar.controller.SportController;

import java.util.List;
import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by norchow on 22/11/15.
 */
public class GaeSportStorage extends SportStorage implements ISportStorage{
    static {
        ObjectifyService.begin();
        ObjectifyService.register(Sport.class);
    }

    @Override
    public List<Sport> getAllSports() {
        return ofy().load().type(Sport.class).list();
    }

    @Override
    public Sport getSportById(Long id) {
        return ofy().load().type(Sport.class).id(id).now();
    }

    public Sport getSportByName(String name) {
        return ofy().load().type(Sport.class).filter("name", name).first().now();
    }

    @Override
    public boolean exists(String sportName) {
        return (getSportByName(sportName) != null);
    }

    @Override
    public boolean exists(Long id) {
        return (getSportById(id) != null);
    }

    @Override
    public Sport createSport(String sportName) {
        Sport currentSport = new Sport(sportName);
        ofy().save().entity(currentSport).now();
        return ofy().load().entity(currentSport).now();
    }

    public void removeSport(Long sportId) {
        ofy().delete().type(Sport.class).id(sportId);
    }
}
