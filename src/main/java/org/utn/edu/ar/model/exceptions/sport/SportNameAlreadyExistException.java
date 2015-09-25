package org.utn.edu.ar.model.exceptions.sport;

/**
 * Created by leandro.mora on 25/09/15.
 */
public class SportNameAlreadyExistException extends Exception{

    public SportNameAlreadyExistException(String entity, String name){
        super("There is already a " +entity+ " with name "+ name);
    }
}
