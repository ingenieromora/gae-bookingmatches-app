package org.utn.edu.ar.model.domain;

/**
 * Created by tomas.duhourq on 9/29/15.
 */
public class Recommendation {

    private int idEmitter;
    private int idReceiver;
    private int idMatch;

    public Recommendation(){}

    public Recommendation(int idEmitter, int idReceiver, int idMatch) {
        this.idEmitter = idEmitter;
        this.idReceiver = idReceiver;
        this.idMatch = idMatch;
    }

    public int getIdEmitter() {
        return idEmitter;
    }

    public void setIdEmitter(int idEmitter) {
        this.idEmitter = idEmitter;
    }

    public int getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(int idReceiver) {
        this.idReceiver = idReceiver;
    }

    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recommendation that = (Recommendation) o;

        if (idEmitter != that.idEmitter) return false;
        if (idReceiver != that.idReceiver) return false;
        return idMatch == that.idMatch;

    }

    @Override
    public int hashCode() {
        int result = idEmitter;
        result = 31 * result + idReceiver;
        result = 31 * result + idMatch;
        return result;
    }
}
