package org.utn.edu.ar.model.exceptions.sport;

public class SportNameAlreadyExistException extends Exception{

    public SportNameAlreadyExistException(String name){
        super("There is already a sport with name "+ name);
    }
}
