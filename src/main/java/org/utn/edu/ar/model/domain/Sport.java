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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sport sport = (Sport) o;

        return !(name != null ? !name.equals(sport.name) : sport.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
