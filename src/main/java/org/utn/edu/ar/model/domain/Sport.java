package org.utn.edu.ar.model.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Sport {

    @Id private Long id;
    private String name;

    public Sport(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Sport(){}

    public Sport(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
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
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }
}
