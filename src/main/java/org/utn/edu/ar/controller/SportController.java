package org.utn.edu.ar.controller;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import org.utn.edu.ar.Constants;
import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.persistence.memoryStorage.SportStorage;
import org.utn.edu.ar.model.service.SportService;
import java.util.List;

/**
 * Created by leandro.mora 20/09/15.
 */
@Api(
        name = "bookingMatches",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE}
)
public class SportController {

    private SportService service = new SportService(new SportStorage());

    @ApiMethod(path = "/sports", httpMethod = "get")
    public List<Sport> listAllSports() { return service.getAllSports(); }
}
