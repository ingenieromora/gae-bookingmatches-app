package org.utn.edu.ar.model.domain;
/**
 * Created by juan pablo.
 */
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
}
