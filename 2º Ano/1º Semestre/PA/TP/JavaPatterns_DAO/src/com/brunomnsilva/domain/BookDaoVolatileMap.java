package com.brunomnsilva.domain;

import com.brunomnsilva.dao.DaoException;

import java.util.*;

/**
 * @author brunomnsilva
 */
public class BookDaoVolatileMap implements BookDao {

    /* in-memory database */
    protected Map<String,Book> books;

    public BookDaoVolatileMap() {
        books = new HashMap<>();
    }

    @Override
    public Book get(String key) {
        return books.get(key);
    }

    @Override
    public Collection<Book> getAll() {
        /* return a shallow-copy list */
        return new ArrayList<>(books.values());
    }

    @Override
    public void save(Book instance) throws DaoException  {
        if(books.get(instance.getIsbn()) != null) throw new DaoException("A book already exists with ISBN " + instance.getIsbn());
        books.put(instance.getIsbn(), instance);
    }

    @Override
    public void update(Book instance) throws DaoException {
        if(books.get(instance.getIsbn()) == null) throw new DaoException("A book already exists with ISBN " + instance.getIsbn());

        /* update logic here: remove old and insert new */
        books.remove(instance.getIsbn());
        books.put(instance.getIsbn(), instance);
    }

    @Override
    public Book delete(String key) {
        /* Do not iterate collection and remove simultaneously */
        return books.remove(key);
    }

    @Override
    public int count() {
        return books.size();
    }

    @Override
    public Collection<Book> getAllFromAuthorSearch(String queryString) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthor().toLowerCase().contains(queryString.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public Collection<Book> getAllFromYearRange(int yearStart, int yearEnd) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getYear() >= yearStart && book.getYear() <= yearEnd) {
                result.add(book);
            }
        }
        return result;
    }
}
