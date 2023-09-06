package com.andrelucs.demoProject.services.exceptions;

public class ConflictException extends RuntimeException{
    private static final long serialVersionUID = 1l;

    public ConflictException(Object id){
        super("That object already exists. Id: "+id);
    }
    public ConflictException(Object id, String table){
        super("That object already exists. Id: "+id+". Table:"+table);
    }

}
