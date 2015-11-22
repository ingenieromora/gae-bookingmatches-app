package org.utn.edu.ar.model.persistence.gaeDatastore;

import java.util.ArrayList;
import java.util.Date;

import com.google.appengine.api.datastore.*;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.*;
import com.googlecode.objectify.util.Closeable;
import org.utn.edu.ar.controller.MatchController;
import org.utn.edu.ar.model.PlayerService;
import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.exceptions.sport.SportNotFoundException;
import org.utn.edu.ar.model.persistence.IMatchStorage;
import org.utn.edu.ar.model.persistence.memoryStorage.MatchesStorage;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.util.Coordinates;


import java.util.List;
import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by leandro.mora on 07/11/15.
 */
public class GaeMatchesStorage extends MatchesStorage implements IMatchStorage{
    static {
        ObjectifyService.begin();
        ObjectifyService.register(Match.class);
    }

    @Override
    public Match createMatch(final MatchRequest rq, final Sport sport, final Player creator)
            throws SportNotFoundException, PlayerNotFoundException {
        Match match = new Match(rq, sport, creator);
        ofy().save().entity(match).now();
        return ofy().load().entity(match).now();
    }

    @Override
    public List<Match> getMatchByCreatedBy(final String fbId) throws PlayerNotFoundException {
        // TODO - NO DEVUELVE NADA - REVISAR INDICE!!! 2) QUizas conviene crear entidad "MatchFromPlayers" y hacer el get de alli a partir de la key principal
        return ofy().load().type(Match.class).filter("createdBy", fbId).list();
    }
}
