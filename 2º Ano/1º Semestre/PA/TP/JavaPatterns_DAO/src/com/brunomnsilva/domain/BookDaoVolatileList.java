package com.brunomnsilva.domain;

import com.brunomnsilva.dao.DaoException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author brunomnsilva
 */
public class BookDaoVolatileList implements BookDao {

    /* in-memory database */
    protected List<Book> books;

    public BookDaoVolatileList() {
        books = new ArrayList<>();
    }

    @Override
    public Book get(String key) {
        int index = findIndexOfKey(key);
        return (index != -1) ? books.get(index) : null;
    }

    @Override
    public Collection<Book> getAll() {
        /* return a shallow-copy list */
        return new ArrayList<>(books);
    }

    @Override
    public void save(Book instance) throws DaoException  {
        int existingIndexForKey = findIndexOfKey(instance.getIsbn());
        if(existingIndexForKey != -1) throw new DaoException("A book already exists with ISBN " + instance.getIsbn());

        books.add(instance);
    }

    @Override
    public void update(Book instance) throws DaoException {
        int existingIndexForKey = findIndexOfKey(instance.getIsbn());
        if(existingIndexForKey == -1) throw new DaoException("No book exists with ISBN " + instance.getIsbn());

        /* update logic here: remove old and insert new */
        books.remove(existingIndexForKey);
        books.add(instance);
    }

    @Override
    public Book delete(String key) {
        /* Do not iterate collection and remove simultaneously */
        int index = findIndexOfKey(key);
        return (index != -1 ? books.remove(index) : null);
    }

    @Override
    public int count() {
        return books.size();
    }

    private int findIndexOfKey(String key) {
        int index = 0;
        for(Book b : books) {
            if(b.getIsbn().equalsIgnoreCase(key)) return index;
            index++;
        }
        return -1;
    }

    @Override
    public Collection<Book> getAllFromAuthorSearch(String queryString) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(queryString.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public Collection<Book> getAllFromYearRange(int yearStart, int yearEnd) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() >= yearStart && book.getYear() <= yearEnd) {
                result.add(book);
            }
        }
        return result;
    }
}
