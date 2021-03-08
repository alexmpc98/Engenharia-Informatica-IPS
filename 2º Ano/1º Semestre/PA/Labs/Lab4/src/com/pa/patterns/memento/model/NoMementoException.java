package com.pa.patterns.memento.model;

public class NoMementoException extends RuntimeException{
    public NoMementoException(){
        super("There is no Memento!");
    }
    public NoMementoException(String message){
        super(message);
    }
}

