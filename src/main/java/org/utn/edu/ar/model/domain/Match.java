package org.utn.edu.ar.model.domain;

import org.utn.edu.ar.model.PlayerService;
import org.utn.edu.ar.model.SportService;
import org.utn.edu.ar.model.exceptions.match.PlayerAlreadyConfirmedException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.exceptions.sport.SportNotFoundException;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.util.Coordinates;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Match {

    private int id;
    private Sport sport;
    private int playersNeeded;
    private Player createdBy;
    private Date date;
    private Coordinates location;
    private List<Player> starters;
    private List<Player> alternates;

    public Match(int id, int sportId, int playersNeeded, Date date, String createdBy, Coordinates location)
            throws SportNotFoundException, PlayerNotFoundException {
        this.id = id;
        //TODO el Deporte tienen que ser pasados por parametro
        this.sport = SportService.getInstance().getSportById(sportId);
        this.playersNeeded = playersNeeded;
        this.date = date;
        //TODO el Jugador creador tienen que ser pasados por parametro
        this.createdBy = PlayerService.getInstance().getByFacebookId(createdBy);
        this.location = location;
        this.starters = new ArrayList<Player>();
        this.alternates = new ArrayList<Player>();
    }

    public Match(MatchRequest rq) throws SportNotFoundException, PlayerNotFoundException {
        //TODO el Deporte tienen que ser pasados por parametro
        this.sport = SportService.getInstance().getSportById(rq.getSportId());
        this.playersNeeded = rq.getPlayersNeeded();
        this.date = rq.getDate();
        //TODO el Jugador creador tienen que ser pasados por parametro
        this.createdBy = PlayerService.getInstance().getByFacebookId(rq.getCreatedBy());
        this.location = rq.getLocation();
        this.starters = new ArrayList<Player>();
        this.alternates = new ArrayList<Player>();
    }

    public void addPlayer(Player player) throws PlayerAlreadyConfirmedException {
        //TODO removerlo de aca, no aplica para un storage que NO sea en memoria
        if(starters.contains(player) || alternates.contains(player)) {
            throw new PlayerAlreadyConfirmedException(player);
        }
        if(starters.size() < playersNeeded && !starters.contains(player))
            starters.add(player);
        else {
            if(!alternates.contains(player) )
                alternates.add(player);
        }
    }


    public void removePlayer(String fbId) throws PlayerNotFoundException {
        //TODO removerlo de aca, no aplica para un storage que NO sea en memoria
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

    public Boolean hasPlayer(Player player){
        return starters.contains(player) || alternates.contains(player);
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

    public Player getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Player createdBy) {
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

    public Sport getSport() {
        return sport;
    }

    public void setSport(int sportId) throws SportNotFoundException {
        this.sport = SportService.getInstance().getSportById(sportId);
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", sport=" + sport +
                ", playersNeeded=" + playersNeeded +
                ", date=" + date +
                ", createdBy=" + createdBy +
                ", location=" + location +
                ", starters=" + starters +
                ", alternates=" + alternates +
                '}';
    }
}
