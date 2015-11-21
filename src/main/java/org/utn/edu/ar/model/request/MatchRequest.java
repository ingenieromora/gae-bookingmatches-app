package org.utn.edu.ar.model.request;


import org.utn.edu.ar.util.Coordinates;

import java.util.Date;

public class MatchRequest {
    private Date date;
    private Coordinates location;
    private Long sportId;
    private Integer playersNeeded;
    private String createdBy;

    public MatchRequest(){}

    public MatchRequest(Date date, Coordinates location, Long sportId, Integer playersNeeded, String createdBy) {

        this.date = date;
        this.location = location;
        this.sportId = sportId;
        this.playersNeeded = playersNeeded;
        this.createdBy = createdBy;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

    public Long getSportId() {
        return sportId;
    }

    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }

    public Integer getPlayersNeeded() {
        return playersNeeded;
    }

    public void setPlayersNeeded(Integer playersNeeded) {
        this.playersNeeded = playersNeeded;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchRequest that = (MatchRequest) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (sportId != null ? !sportId.equals(that.sportId) : that.sportId != null) return false;
        if (playersNeeded != null ? !playersNeeded.equals(that.playersNeeded) : that.playersNeeded != null)
            return false;
        return !(createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null);

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (sportId != null ? sportId.hashCode() : 0);
        result = 31 * result + (playersNeeded != null ? playersNeeded.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MatchRequest{" +
                "date=" + date +
                ", location=" + location +
                ", sportId=" + sportId +
                ", playersNeeded=" + playersNeeded +
                ", createdBy=" + createdBy +
                '}';
    }
}
