package org.utn.edu.ar.model.domain;

import org.joda.time.DateTime;

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

    public Match(int id, Sport sport, int playersNeeded, DateTime date, Player creator, double latitude, double longitude) {
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
}
