package com.brunomnsilva.domain;

import com.brunomnsilva.dao.DaoException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author brunomnsilva
 */
public class BookDaoTextFiles implements BookDao {

    private static final String DB_FOLDER = "storage";
    private static final String FILE_EXTENSION = ".book";

    public BookDaoTextFiles() {
        /* Check for storage folder presence, otherwise create */
        File directory = new File(DB_FOLDER);
        if(!directory.exists()) {
            directory.mkdir();
        }
    }

    @Override
    public Collection<Book> getAllFromAuthorSearch(String queryString) {
        List<Book> list = new ArrayList<>( getAll() );
        Iterator<Book> iterator = list.iterator();
        while(iterator.hasNext()) {
            Book book = iterator.next();
            if (!book.getAuthor().toLowerCase().contains(queryString.toLowerCase())) {
                iterator.remove();
            }
        }
        return list;
    }

    @Override
    public Collection<Book> getAllFromYearRange(int yearStart, int yearEnd) {
        List<Book> list = new ArrayList<>( getAll() );
        Iterator<Book> iterator = list.iterator();
        while(iterator.hasNext()) {
            Book book = iterator.next();
            if (!(book.getYear() >= yearStart && book.getYear() <= yearEnd)) {
                iterator.remove();
            }
        }
        return list;
    }

    @Override
    public Book get(String key) {
        File file = findFile(key);
        return (file != null ? readContents(file) : null);
    }

    @Override
    public Collection<Book> getAll() {
        File directory = new File(DB_FOLDER);
        File[] files = directory.listFiles(new BookFileExtensionFilter());

        List<Book> all = new ArrayList<>();

        for(File f : files) {
            Book book = readContents(f);
            all.add(book);
        }

        return all;
    }

    @Override
    public void save(Book instance) throws DaoException {
        File file = findFile(instance.getIsbn());
        if(file != null) throw new DaoException("A book already exists with ISBN " + instance.getIsbn());

        writeContents(instance, new File(DB_FOLDER + File.separator + instance.getIsbn() + FILE_EXTENSION));
    }

    @Override
    public void update(Book instance) throws DaoException {
        File file = findFile(instance.getIsbn());
        if(file == null) throw new DaoException("A book already exists with ISBN " + instance.getIsbn());

        writeContents(instance, file);
    }

    @Override
    public Book delete(String key) {
        File file = findFile(key);
        if(file == null) return null;

        Book book = readContents(file);

        file.delete();

        return book;
    }

    @Override
    public int count() {
        File directory = new File(DB_FOLDER);
        File[] files = directory.listFiles(new BookFileExtensionFilter());
        return files.length;
    }

    private File findFile(String key) {
        File directory = new File(DB_FOLDER);
        File[] files = directory.listFiles(new BookFileExtensionFilter());
        for(File f : files) {
            if(f.getName().startsWith(key)) {
                return f;
            }
        }
        return null;
    }

    private Book readContents(File f) {
        Book read = null;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String contents = br.readLine();
            //String[] fields = contents.split(";");
            //read = new Book(fields[0],fields[1],fields[2],Integer.valueOf(fields[3]));
            read = Book.createFromString(contents);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return read;
    }

    private void writeContents(Book b, File f)  {
        try {
            FileWriter fw = new FileWriter(f, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(b.getIsbn()+";"+b.getAuthor()+";"+b.getTitle()+";"+b.getYear());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class BookFileExtensionFilter implements FileFilter {

        @Override
        public boolean accept(File pathname) {
            if( pathname.getName().toLowerCase().endsWith(FILE_EXTENSION) ) {
                return true;
            }
            return false;
        }

    }
}
