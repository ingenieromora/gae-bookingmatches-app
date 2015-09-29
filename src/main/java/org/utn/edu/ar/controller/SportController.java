package org.utn.edu.ar.controller;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import org.utn.edu.ar.Constants;
import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.exceptions.sport.SportNameAlreadyExistException;
import org.utn.edu.ar.model.exceptions.sport.SportNotFoundException;
import org.utn.edu.ar.model.persistence.memoryStorage.SportStorage;
import org.utn.edu.ar.model.service.SportService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leandro.mora 20/09/15.
 */
@Api(
        name = "helloworld",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE}
)
public class SportController {

    private SportService service = new SportService(new SportStorage(new ArrayList<Sport>()));

    @ApiMethod(path = "sports.listSports", httpMethod = "get")
    public List<Sport> listAllSports() throws SportNotFoundException { return service.getAllSports(); }

    @ApiMethod(name = "sports.getSportById", httpMethod = "get")
    public Sport getSport(@Named("id") Integer id) throws NotFoundException {
        try {
            return service.getSportById(id);
        } catch (SportNotFoundException e) {
            throw new NotFoundException("Sport with "+id+" does not exist");
        }
    }

    @ApiMethod(name = "sports.add", httpMethod = "post")
    public void createSport(@Named("name") String name) throws ConflictException {
        try {
            service.createSport(name);
        } catch (SportNameAlreadyExistException e) {
            throw new ConflictException(e);
        }
    }

    @ApiMethod(name = "sports.update", httpMethod = "put")
    public void updateSport(@Named("id") Integer id, @Named("name") String name) throws ConflictException {
        try {
            service.updateSport(id, name);
        } catch (SportNotFoundException e){
            throw new ConflictException(e);
        }
    }


    @ApiMethod(name = "sports.remove", httpMethod = "delete")
    public void removeSport(@Named("id") Integer id) throws NotFoundException {
        try {
            service.removeSport(id);
        } catch (Exception e) {
            throw new NotFoundException(e);
        }
    }
}
