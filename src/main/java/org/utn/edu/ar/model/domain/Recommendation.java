package org.utn.edu.ar.model.domain;

/**
 * Created by tomas.duhourq on 9/29/15.
 */
public class Recommendation {

    private int id;
    private Match match;
    private Player emitter;
    private Player receiver;

    public Recommendation(){}

    public Recommendation(Player anEmitter, Player aReceiver, Match aMatch) {
        emitter = anEmitter;
        receiver = aReceiver;
        match = aMatch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Player getEmitter() {
        return emitter;
    }

    public void setEmitter(Player emitter) {
        this.emitter = emitter;
    }

    public Player getReceiver() {
        return receiver;
    }

    public void setReceiver(Player receiver) {
        this.receiver = receiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recommendation that = (Recommendation) o;

        if (id != that.id) return false;
        if (match != null ? !match.equals(that.match) : that.match != null) return false;
        if (emitter != null ? !emitter.equals(that.emitter) : that.emitter != null) return false;
        return !(receiver != null ? !receiver.equals(that.receiver) : that.receiver != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (match != null ? match.hashCode() : 0);
        result = 31 * result + (emitter != null ? emitter.hashCode() : 0);
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        return result;
    }
}
