package org.utn.edu.ar.model.exceptions.sport;

/**
 * Created by leandro.mora on 25/09/15.
 */
public class SportNotFoundException extends Exception {

    public SportNotFoundException(Integer id){
        super("Sport with id '"+id+"' does not exist");
    }

    public SportNotFoundException(String message){
        super(message);
    }
}
