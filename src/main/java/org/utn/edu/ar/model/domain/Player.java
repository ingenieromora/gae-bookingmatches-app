package org.utn.edu.ar.model.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Player {

    @Id private int id;
    private String fbId;

    public Player(int id, String fbId) {
        this.id = id;
        this.fbId = fbId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (id != player.id) return false;
        return !(fbId != null ? !fbId.equals(player.fbId) : player.fbId != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fbId != null ? fbId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", fbId='" + fbId + '\'' +
                '}';
    }
}
