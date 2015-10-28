package org.utn.edu.ar.model.domain;

/**
 * Created by tomas.duhourq on 9/29/15.
 */
public class Recommendation {

    private int id;
    private String matchId;
    private String emitterId;
    private String receiverId;

    public Recommendation(){}

    public Recommendation(String emitterId, String receiverId, String matchId) {
        this.emitterId = emitterId;
        this.receiverId = receiverId;
        this.matchId = matchId;
    }

    public String getEmitterId() {
        return emitterId;
    }

    public void setEmitterId(String emitterId) {
        this.emitterId = emitterId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recommendation that = (Recommendation) o;

        if (emitterId != that.emitterId) return false;
        if (receiverId != that.receiverId) return false;
        return matchId == that.matchId;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + matchId.hashCode();
        result = 31 * result + emitterId.hashCode();
        result = 31 * result + receiverId.hashCode();
        return result;
    }
}
