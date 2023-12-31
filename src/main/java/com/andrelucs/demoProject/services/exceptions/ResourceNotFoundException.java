package com.andrelucs.demoProject.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1l;

    public ResourceNotFoundException(Object id){
        super("Resource not found. Id: "+id);
    }
    public ResourceNotFoundException(Object id, String table){
        super("Resource not found. Id: "+id+". Table:"+table);
    }
}
