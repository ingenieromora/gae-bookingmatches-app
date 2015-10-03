package org.utn.edu.ar.model.exceptions.match;

import org.utn.edu.ar.model.domain.Player;

/**
 * Created by tomas.duhourq on 9/29/15.
 */
public class PlayerAlreadyConfirmedException extends Exception {

    public PlayerAlreadyConfirmedException(Player player){
        super("Player with facebookId " + player.getFbId() +
                " has already been confirmed for the match.");
    }
}
