package org.utn.edu.ar.model.exceptions.player;

/**
 * Created by tom on 9/29/15.
 */
public class PlayerNotFoundException extends Exception {

    public PlayerNotFoundException(Integer id){
        super("Player with id: " + id + " not found.");
    }
    public PlayerNotFoundException(String fbId){
        super("Player with fbId: " + fbId + " not found either on starters nor alternates.");
    }
}
