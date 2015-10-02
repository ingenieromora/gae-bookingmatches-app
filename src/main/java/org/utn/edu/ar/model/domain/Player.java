package org.utn.edu.ar.model.domain;

public class Player {

    private int id;
    private String facebookId;

    public Player(int id, String facebookId) {
        this.id = id;
        this.facebookId = facebookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (id != player.id) return false;
        return !(facebookId != null ? !facebookId.equals(player.facebookId) : player.facebookId != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (facebookId != null ? facebookId.hashCode() : 0);
        return result;
    }
}
