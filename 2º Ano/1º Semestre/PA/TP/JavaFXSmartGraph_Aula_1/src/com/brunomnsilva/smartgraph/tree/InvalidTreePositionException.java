package com.brunomnsilva.smartgraph.tree;

/**
 *
 */
public class InvalidTreePositionException extends RuntimeException {

    public InvalidTreePositionException() {
        super("Invalid tree position. Does it belong to this tree?");
    }

    public InvalidTreePositionException(String s) {
        super(s);
    }
}
