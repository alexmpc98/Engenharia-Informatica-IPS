package com.brunomnsilva.domain;

import com.brunomnsilva.dao.DaoException;

import java.util.*;

/**
 * @author brunomnsilva
 */
public class BookDaoFactory {

    public static BookDao create(String type) {
        switch(type.toLowerCase()) {
            case "volatilelist":
                return new BookDaoVolatileList();
            case "volatilemap":
                return new BookDaoVolatileMap();
            case "serialization":
                return new BookDaoSerialization();
            case "textfiles":
                return new BookDaoTextFiles();
            default:
                throw new UnsupportedOperationException("Type not supported: " + type);
        }
    }
}
