package org.utn.edu.ar.model.domain;

/**
 * Created by leomora on 20/09/15.
 */
public class Sport {

    private String name;
    private int id;

    public Sport(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Sport(){}

    public Sport(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    /**
     * Two sports are equals if its id is the same.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sport)) return false;

        Sport sport = (Sport) o;

        if (id != sport.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
