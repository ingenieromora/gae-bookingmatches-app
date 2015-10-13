package org.utn.edu.ar.controller;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import org.utn.edu.ar.Constants;
import org.utn.edu.ar.model.RecommendationService;
import org.utn.edu.ar.model.domain.Recommendation;
import org.utn.edu.ar.model.persistence.memoryStorage.RecommendationStorage;

import java.util.ArrayList;
import java.util.List;

@Api(
        name = "recommendations",
        scopes = Constants.EMAIL_SCOPE,
        clientIds = Constants.WEB_CLIENT_ID
)
public class RecommendationController {

    private RecommendationService service = new RecommendationService(new RecommendationStorage(new ArrayList<Recommendation>()));

    @ApiMethod(
            name = "create",
            path = "recommendations",
            httpMethod = HttpMethod.POST
    )
    public void create(Recommendation rec) {
            service.create(rec);
    }

    @ApiMethod(
            name = "delete",
            path = "recommendations/{id}",
            httpMethod = HttpMethod.DELETE
    )
    public void delete(@Named("id") Integer id) {
        service.delete(id);
    }

    @ApiMethod(
            name = "getAll",
            path = "recommendations",
            httpMethod = HttpMethod.GET
    )
    public List<Recommendation> getAll(@Nullable @Named("origin") Integer originId,
                                       @Nullable @Named("destination") Integer destinationId){
        if(originId != null) return service.getForEmitter(originId);
        if(destinationId != null) return service.getForReceiver(destinationId);
        return service.getAll();
    }

    @ApiMethod(
            name = "getById",
            path = "recommendations/{id}",
            httpMethod = HttpMethod.GET
    )
    public Recommendation getById(@Named("id") Integer id){
        return service.getById(id);
    }
}
