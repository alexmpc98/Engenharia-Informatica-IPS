package com.brunomnsilva.domain;

import com.brunomnsilva.dao.Dao;

import java.util.Collection;

/**
 * @author brunomnsilva
 */
public interface BookDao extends Dao<Book, String> {

    /* Aditional operations besides CRUD (inherited): */

    Collection<Book> getAllFromAuthorSearch(String queryString);
    Collection<Book> getAllFromYearRange(int yearStart, int yearEnd);
}
