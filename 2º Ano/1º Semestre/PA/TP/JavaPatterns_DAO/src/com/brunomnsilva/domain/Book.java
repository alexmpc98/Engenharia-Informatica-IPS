package com.brunomnsilva.domain;

import java.io.Serializable;

/**
 * @author brunomnsilva
 */
public class Book implements Serializable {

    private final String isbn;
    private String author;
    private String title;
    private int year;

    public Book(String isbn, String author, String title, int year) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                '}';
    }

    /* Bellow are 'creation methods' for this class */

    public static Book createFromStrings(String isbn, String author, String title, String year) {
        /* We should perform value validations here, e.g., strings not null or empty */
        int yearValue = 0;
        try {
            yearValue = Integer.valueOf(year);
        } catch (NumberFormatException e) {
            yearValue = 0;
        }
        return new Book(isbn, author, title, yearValue);
    }


    public static Book createFromString(String contents) {
        /* We should perform value validations here, e.g., string not null or empty */
        String[] fields = contents.split(";");
        try {
            return new Book(fields[0],fields[1],fields[2],Integer.valueOf(fields[3]));
        } catch (NumberFormatException e) {
            return new Book(fields[0],fields[1],fields[2],0);
        }
    }
}
