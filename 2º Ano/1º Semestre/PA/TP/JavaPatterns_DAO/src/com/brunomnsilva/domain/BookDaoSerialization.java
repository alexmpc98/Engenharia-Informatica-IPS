package com.brunomnsilva.domain;

import com.brunomnsilva.dao.DaoException;

import java.io.*;
import java.util.List;

/**
 * @author brunomnsilva
 */
public class BookDaoSerialization extends BookDaoVolatileList {

    private static final String STORAGE_FILENAME = "storage/books.data";

    public BookDaoSerialization() {
        super();
        readStorage();
    }

    private void saveStorage() {
        try {
            FileOutputStream fileOut = new FileOutputStream(STORAGE_FILENAME);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject( this.books );
            out.close();
            fileOut.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void readStorage() {
        try {
            FileInputStream fileIn = new FileInputStream(STORAGE_FILENAME);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.books = (List<Book>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void save(Book instance) throws DaoException {
        super.save(instance);

        saveStorage();
    }

    @Override
    public void update(Book instance) throws DaoException {
        super.update(instance);

        saveStorage();
    }

    @Override
    public Book delete(String key) {
        Book deleted = super.delete(key);
        saveStorage();
        return deleted;
    }
}
