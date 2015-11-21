package org.utn.edu.ar.model;

import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.model.exceptions.player.PlayerAlreadyExistsException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.exceptions.sport.SportNotFoundException;
import org.utn.edu.ar.model.persistence.IMatchStorage;
import org.utn.edu.ar.model.persistence.gaeDatastore.GaeMatchesStorage;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.util.Coordinates;

import java.util.Date;
import java.util.List;

/**
 * Created leandro.mora
 */
public class MatchService {

    private static MatchService instance = null;

    private IMatchStorage storage;
    private PlayerService playerService;

    public MatchService(IMatchStorage storage, PlayerService playerService) {

        this.storage = storage;
        this.playerService = playerService;
    }

    public static MatchService getInstance() {
        if (instance == null) {
            synchronized (MatchService.class) {
                if (instance == null) {
                    instance = new MatchService(
                            new GaeMatchesStorage(),
                            PlayerService.getInstance());
                }
            }
        }
        return instance;
    }

    public List<Match> getAllMatches() {
        return storage.getAllMatches();
    }

    public Match getMatchById(Long id) throws MatchNotFoundException {
        Match match = storage.getMatchById(id);
        if (match == null) throw new MatchNotFoundException(id);
        return match;
    }

    public List<Match> getMatchByCreatedBy(String createdBy) throws PlayerNotFoundException {
        return storage.getMatchByCreatedBy(createdBy);
    }

    public List<Match> getMatchesInscriptionsBy(String fbId) throws PlayerNotFoundException {
        return storage.getMatchesInscriptionsBy(fbId);
    }

    public Match createMatch(MatchRequest rq) throws SportNotFoundException, PlayerNotFoundException {
        if( !validParams(rq) ) throw new IllegalArgumentException("The match received less number of arguments than expected");
        Sport sport = SportService.getInstance().getSportById(rq.getSportId());
        Player createdBy = PlayerService.getInstance().getByFacebookId(rq.getCreatedBy());

        return storage.createMatch(rq, sport, createdBy);
    }

    private boolean validParams(MatchRequest rq) {
        return ((rq.getCreatedBy() != null) && (rq.getDate() != null)
                && (rq.getLocation() != null) && (rq.getPlayersNeeded() != null)
                && (rq.getSportId() != null));
    }

    public boolean exists(Long id) {
        return storage.exists(id);
    }


    public void updateMatch(Long id, Long sportId, Integer playersNeeded, Date date, String createdBy, Coordinates location)
            throws MatchNotFoundException, SportNotFoundException, PlayerNotFoundException {

        if (!exists(id)) {
            throw new MatchNotFoundException(id);
        }
        storage.updateMatch(id, sportId, playersNeeded, date, createdBy, location);
    }

    public void deleteMatch(Long id) throws MatchNotFoundException {
        if (!exists(id)) throw new MatchNotFoundException(id);
        storage.deleteMatch(id);
    }

    public void removePlayer(Long matchId, String fbId)
            throws MatchNotFoundException, PlayerNotFoundException {
        storage.removePlayer(matchId, fbId);
    }

    public void addPlayerToMatch(Long matchId, String playerFbId) throws MatchNotFoundException, PlayerAlreadyExistsException, PlayerNotFoundException, PlayerAlreadyConfirmedException {
        if (!exists(matchId)) throw new MatchNotFoundException(matchId);

        Player createdPlayer = getPlayer(playerFbId);

        storage.addPlayer(matchId, createdPlayer);
    }

    private Player getPlayer(String playerFbId) throws PlayerAlreadyExistsException, PlayerNotFoundException {
        PlayerService playerService = PlayerService.getInstance();
        if( !playerService.exists(playerFbId) ) { playerService.create(playerFbId); }

        return playerService.getByFacebookId(playerFbId);
    }

    public Boolean hasPlayer(Long matchId, String fbId) throws PlayerAlreadyExistsException, PlayerNotFoundException{
        Player player = getPlayer(fbId);
        return storage.hasPlayer(matchId, player);
    }

    public void setStorage(final IMatchStorage aMatchStorage) { storage = aMatchStorage; }
}
