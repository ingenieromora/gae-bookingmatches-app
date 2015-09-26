package org.utn.edu.ar.model.domain;

import java.time.LocalDate;

/**
 * Created by juan pablo.
 */
public class Match {

    private int id;
    private Sport sport;
    private int playersNeeded;
    private LocalDate date;
    private Player creator;
    private double latitude;
    private double longitude;

    public Match(int id, Sport sport, int playersNeeded, LocalDate date, Player creator, double latitude, double longitude) {
        this.id = id;
        this.sport = sport;
        this.playersNeeded = playersNeeded;
        this.date = date;
        this.creator = creator;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
}
