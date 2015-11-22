package org.utn.edu.ar.model.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import org.utn.edu.ar.model.SportService;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.exceptions.sport.SportNotFoundException;
import org.utn.edu.ar.model.request.MatchRequest;
import org.utn.edu.ar.util.Coordinates;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Match {

    @Id private Long id;
    private Sport sport;
    private int playersNeeded;
    @Index private Player createdBy;
    private Date date;
    private Coordinates location;
    private List<Player> starters;
    private List<Player> alternates;

    public Match(){}

    public Match(MatchRequest rq, Sport inputSport, Player inputCreatedBy) throws SportNotFoundException, PlayerNotFoundException {
        this.sport = inputSport;
        this.playersNeeded = rq.getPlayersNeeded();
        this.date = rq.getDate();
        this.createdBy = inputCreatedBy;
        this.location = rq.getLocation();
        this.starters = new ArrayList<Player>();
        this.alternates = new ArrayList<Player>();
    }

    public Boolean hasPlayer(Player player){
        return starters.contains(player) || alternates.contains(player);
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setSport(Long sportId) throws SportNotFoundException {
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
