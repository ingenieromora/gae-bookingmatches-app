package org.utn.edu.ar.model.exceptions.sport;

/**
 * Created by leandro.mora on 25/09/15.
 */
public class SportAlreadyExistException extends Exception {

    public SportAlreadyExistException(Integer sportId){
        super("Sport with id "+sportId+ " already exists");
    }

}
