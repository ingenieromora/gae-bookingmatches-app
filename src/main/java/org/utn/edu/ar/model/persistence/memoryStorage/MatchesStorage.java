package org.utn.edu.ar.model.persistence.memoryStorage;

import com.google.appengine.repackaged.com.google.common.base.Function;
import com.google.appengine.repackaged.com.google.common.base.Optional;
import com.google.appengine.repackaged.com.google.common.base.Predicate;
import com.google.appengine.repackaged.com.google.common.collect.FluentIterable;
import com.google.appengine.repackaged.com.google.common.collect.Iterables;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.utn.edu.ar.model.PlayerService;
import org.utn.edu.ar.model.domain.Match;
import org.utn.edu.ar.model.domain.Player;
import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.exceptions.sport.SportNotFoundException;
import org.utn.edu.ar.model.persistence.IMatchStorage;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.util.Coordinates;
import org.utn.edu.ar.util.Utils;

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
    public Match getMatchById(Long id) {
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
    public Match createMatch(MatchRequest rq, Sport sport, Player playerCreatedBy) throws SportNotFoundException, PlayerNotFoundException {
        Match match = new Match(rq, sport, playerCreatedBy);
        match.setId(nextId());
        match.setStarters(
          Lists.newArrayList(
            PlayerService.getInstance().getByFacebookId(
              rq.getCreatedBy())));
        matches.add(match);
        return match;
    }

    @Override
    public boolean exists(final Long id) {
        return FluentIterable.from(matches).anyMatch(new Predicate<Match>() {
            @Override
            public boolean apply(final Match match) {
                return match.getId() == id;
            }
        });
    }

    @Override
    public void updateMatch(Long id, Long sportId, Integer playersNeeded, Date date, String createdBy, Coordinates location)
            throws SportNotFoundException, PlayerNotFoundException {
        Match match = getMatchById(id);
        if ( sportId != null ) match.setSport(sportId);
        if (playersNeeded != null) match.setPlayersNeeded(playersNeeded);
        if (date != null) match.setDate(date);
        if (createdBy != null) match.setCreatedBy(PlayerService.getInstance().getByFacebookId(createdBy));
        if (location != null) match.setLocation(location);
    }

    @Override
    public void deleteMatch(Long id) {
        Match match = getMatchById(id);
        if (match != null) matches.remove(match);
    }

    @Override
    public void removePlayer(final Long matchId, final String fbId)
            throws MatchNotFoundException, PlayerNotFoundException {
        Optional<Match> optMatch = Iterables.tryFind(matches, new Predicate<Match>() {
            @Override
            public boolean apply(final Match match) {
                return match.getId() == (matchId);
            }
        });
        if(!optMatch.isPresent()) throw new MatchNotFoundException(matchId);
        else {
           removePlayer(optMatch.get(), fbId);
        }
    }

    @Override
    public void addPlayer(Long matchId, Player player) throws PlayerAlreadyConfirmedException {
        Match match = getMatchById(matchId);

        addPlayer(match, player);
    }

    protected void addPlayer(Match inputMatch, Player player) throws PlayerAlreadyConfirmedException {

        if(inputMatch.getStarters().contains(player) || inputMatch.getAlternates().contains(player)) {
            throw new PlayerAlreadyConfirmedException(player);
        }
        if(inputMatch.getStarters().size() < inputMatch.getPlayersNeeded() && !inputMatch.getStarters().contains(player))
            inputMatch.getStarters().add(player);
        else {
            if(!inputMatch.getAlternates().contains(player) )
                inputMatch.getAlternates().add(player);
        }
    }


    protected void removePlayer(Match inputMatch, String fbId) throws PlayerNotFoundException {
        for(Player p : inputMatch.getStarters()){
            if(p.getFbId().equals(fbId)){
                inputMatch.getStarters().remove(p);
                return;
            }
        }

        for(Player p : inputMatch.getAlternates()){
            if(p.getFbId().equals(fbId)){
                inputMatch.getAlternates().remove(p);
                return;
            }
        }

        throw new PlayerNotFoundException(fbId);
    }

    @Override
    public Boolean hasPlayer(Long matchId, Player player){
        Match match = getMatchById(matchId);

        return match.hasPlayer(player);
    }

    private Long nextId(){
        try {
            return Utils.successor.apply(
                    Ordering.<Long>natural().max(
                            FluentIterable.from(matches).transform(
                                    new Function<Match, Long>() {
                                        @Override
                                        public Long apply(final Match player) {
                                            return player.getId();
                                        }
                                    })));
        } catch (NoSuchElementException e) { return 1l; }
    }
}
