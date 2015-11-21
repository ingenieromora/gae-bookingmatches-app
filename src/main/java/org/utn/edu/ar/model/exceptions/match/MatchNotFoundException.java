package org.utn.edu.ar.model.exceptions.match;

/**
 * Created by juan pablo.
 */
public class MatchNotFoundException extends Exception {

    public MatchNotFoundException(Long id) {
        super("Match with id " + id + " does not exist");
    }

    public MatchNotFoundException(String message) {
        super(message);
    }
}
