package org.utn.edu.ar.model.domain;

import org.joda.time.DateTime;
import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Match {

    private int id;
    private Sport sport;
    private int playersNeeded;
    private DateTime date;
    private Player creator;
    private Pair<Double, Double> location;
    private List<Player> starters;
    private List<Player> alternates;

    public Match(int id, Sport sport, int playersNeeded, DateTime date, Player creator, Pair<Double, Double> location) {
        this.id = id;
        this.sport = sport;
        this.playersNeeded = playersNeeded;
        this.date = date;
        this.creator = creator;
        this.location = location;
        this.starters = new ArrayList<Player>();
        this.alternates = new ArrayList<Player>();
    }


    public void addPlayer(Player player) throws PlayerAlreadyConfirmedException {
        if(starters.size() < playersNeeded && !starters.contains(player))
            starters.add(player);
        else {
            if(!alternates.contains(player))
                alternates.add(player);
            else throw new PlayerAlreadyConfirmedException(player);
        }
    }

    public void removePlayer(String fbId) throws PlayerNotFoundException {
        for(Player p : starters){
            if(p.getFbId().equals(fbId)){
                starters.remove(p);
                return;
            }
        }

        for(Player p : alternates){
            if(p.getFbId().equals(fbId)){
                alternates.remove(p);
                return;
            }
        }

        throw new PlayerNotFoundException(fbId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public int getPlayersNeeded() {
        return playersNeeded;
    }

    public void setPlayersNeeded(int playersNeeded) {
        this.playersNeeded = playersNeeded;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public Player getCreator() {
        return creator;
    }

    public void setCreator(Player creator) {
        this.creator = creator;
    }

    public List<Player> getStarters() {
        return starters;
    }

    public void setStarters(List<Player> starters) {
        this.starters = starters;
    }

    public List<Player> getAlternates() {
        return alternates;
    }

    public void setAlternates(List<Player> alternates) {
        this.alternates = alternates;
    }

    public Pair<Double, Double> getLocation() {
        return location;
    }

    public void setLocation(Pair<Double, Double> location) {
        this.location = location;
    }
}
