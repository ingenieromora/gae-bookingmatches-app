package org.utn.edu.ar.model.domain;

/**
 * Created by tomas.duhourq on 9/29/15.
 */
public class Recommendation {

    private int id;
    private int matchId;
    private int emitterId;
    private int receiverId;

    public Recommendation(){}

    public Recommendation(int emitterId, int receiverId, int matchId) {
        this.emitterId = emitterId;
        this.receiverId = receiverId;
        this.matchId = matchId;
    }

    public int getEmitterId() {
        return emitterId;
    }

    public void setEmitterId(int emitterId) {
        this.emitterId = emitterId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
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
        int result = emitterId;
        result = 31 * result + receiverId;
        result = 31 * result + matchId;
        return result;
    }
}
