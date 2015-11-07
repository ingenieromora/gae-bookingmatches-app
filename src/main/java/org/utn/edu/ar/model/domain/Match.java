package org.utn.edu.ar.model.domain;

import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.util.Coordinates;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Match {

    private int id;
    private int sportId;
    private int playersNeeded;
    private String createdBy;
    private Date date;
    private Coordinates location;
    private List<Player> starters;
    private List<Player> alternates;

    public Match(int id, int sportId, int playersNeeded, Date date, String createdBy, Coordinates location) {
        this.id = id;
        this.sportId = sportId;
        this.playersNeeded = playersNeeded;
        this.date = date;
        this.createdBy = createdBy;
        this.location = location;
        this.starters = new ArrayList<Player>();
        this.alternates = new ArrayList<Player>();
    }

    public Match(MatchRequest rq) {
        this.sportId = rq.getSportId();
        this.playersNeeded = rq.getPlayersNeeded();
        this.date = rq.getDate();
        this.createdBy = rq.getCreatedBy();
        this.location = rq.getLocation();
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
        System.out.println("TITULARES: "+starters);
        System.out.println("SUPLENTES: "+alternates);
        System.out.println("A REMOVER:" + fbId);

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

    public int getPlayersNeeded() {
        return playersNeeded;
    }

    public void setPlayersNeeded(int playersNeeded) {
        this.playersNeeded = playersNeeded;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", sportId=" + sportId +
                ", playersNeeded=" + playersNeeded +
                ", date=" + date +
                ", createdBy=" + createdBy +
                ", location=" + location +
                ", starters=" + starters +
                ", alternates=" + alternates +
                '}';
    }
}
