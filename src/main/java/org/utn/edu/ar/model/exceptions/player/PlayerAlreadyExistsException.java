package org.utn.edu.ar.model.exceptions.player;

/**
 * Created by tom on 9/28/15.
 */
public class PlayerAlreadyExistsException extends Exception {

    public PlayerAlreadyExistsException(String fbId){
        super("There is already a player with fbId "+ fbId);
    }
}
