package org.utn.edu.ar.model.domain;

import org.joda.time.DateTime;
import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan pablo.
 */
public class Match {

    private int id;
    private Sport sport;
    private int playersNeeded;
    private DateTime date;
    private Player creator;
    private double latitude;
    private double longitude;
    private List<Player> starters;
    private List<Player> alternates;

    public Match(int id, Sport sport, int playersNeeded, DateTime date, Player creator, double latitude, double longitude) {
        this.id = id;
        this.sport = sport;
        this.playersNeeded = playersNeeded;
        this.date = date;
        this.creator = creator;
        this.latitude = latitude;
        this.longitude = longitude;
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
            if(p.getFacebookId().equals(fbId)){
                starters.remove(p);
                return;
            }
        }

        for(Player p : alternates){
            if(p.getFacebookId().equals(fbId)){
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
}
