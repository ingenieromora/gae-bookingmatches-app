package org.utn.edu.ar.model.persistence.memoryStorage;

import com.google.appengine.repackaged.com.google.common.base.Function;
import com.google.appengine.repackaged.com.google.common.base.Predicate;
import com.google.appengine.repackaged.com.google.common.collect.FluentIterable;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.utn.edu.ar.model.PlayerService;
import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.exceptions.sport.SportNotFoundException;
import org.utn.edu.ar.model.persistence.IMatchStorage;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.util.Coordinates;
import org.utn.edu.ar.util.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class MatchesStorage implements IMatchStorage {

    private List<Match> matches = Lists.newArrayList();

    public MatchesStorage(){}

    @Override
    public List<Match> getAllMatches() {
        return matches;
    }

    @Override
    public Match getMatchById(int id) {
        Match match = null;
        for (Match m : matches) {
            if (m.getId() == id) match = m;
        }
        return match;
    }

    @Override
    public List<Match> getMatchByCreatedBy(final String createdBy) throws PlayerNotFoundException {
        return Lists.newArrayList(FluentIterable.from(matches).filter(new Predicate<Match>() {
            @Override
            public boolean apply(final Match match) {
                return match.getCreatedBy().getFbId().equals(createdBy);
            }
        }));
    }

    @Override
    public List<Match> getMatchesInscriptionsBy(final String fbId) throws PlayerNotFoundException {
        final Player player = PlayerService.getInstance().getByFacebookId(fbId);
        return Lists.newArrayList(FluentIterable.from(matches).filter(new Predicate<Match>() {
            @Override
            public boolean apply(final Match match) {
                return match.hasPlayer(player);
            }
        }));
    }

    @Override
    public Match createMatch(MatchRequest rq) throws SportNotFoundException, PlayerNotFoundException {
        Match match = new Match(rq);
        match.setId(nextId());
        match.setStarters(
          Lists.newArrayList(
            PlayerService.getInstance().getByFacebookId(
              rq.getCreatedBy())));
        matches.add(match);
        return match;
    }

    @Override
    public boolean exists(final int id) {
        return FluentIterable.from(matches).anyMatch(new Predicate<Match>() {
            @Override
            public boolean apply(final Match match) {
                return match.getId() == id;
            }
        });
    }

    @Override
    public void updateMatch(int id, Integer sportId, Integer playersNeeded, Date date, String createdBy, Coordinates location)
            throws SportNotFoundException, PlayerNotFoundException {
        Match match = getMatchById(id);
        if ( sportId != null ) match.setSport(sportId);
        if (playersNeeded != null) match.setPlayersNeeded(playersNeeded);
        if (date != null) match.setDate(date);
        if (createdBy != null) match.setCreatedBy(PlayerService.getInstance().getByFacebookId(createdBy));
        if (location != null) match.setLocation(location);
    }

    @Override
    public void deleteMatch(int id) {
        Match match = getMatchById(id);
        if (match != null) matches.remove(match);
    }

    @Override
    public void removePlayer(Integer matchId, String fbId)
            throws MatchNotFoundException, PlayerNotFoundException {
        Match match = null;
        for (Match m : matches){
            if(m.getId() == matchId){
                match = m;
                break;
            }
        }

        if(match == null) throw new MatchNotFoundException(matchId);

        match.removePlayer(fbId);
    }

    @Override
    public void addPlayer(Integer matchId, Player player) throws PlayerAlreadyConfirmedException {
        Match match = getMatchById(matchId);

        match.addPlayer(player);
    }

    @Override
    public Boolean hasPlayer(Integer matchId, Player player){
        Match match = getMatchById(matchId);

        return match.hasPlayer(player);
    }

    private Integer nextId(){
        try {
            return Utils.successor.apply(
                    Ordering.<Integer>natural().max(
                            FluentIterable.from(matches).transform(
                                    new Function<Match, Integer>() {
                                        @Override
                                        public Integer apply(final Match player) {
                                            return player.getId();
                                        }
                                    })));
        } catch (NoSuchElementException e) { return 1; }
    }
}
