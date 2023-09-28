package edu.est.library.infrastructure.db;

import edu.est.library.domain.interfaces.repository.ICrud;
import edu.est.library.domain.models.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BookDb implements ICrud<Book> {

    private final HashSet<Book> books;

    public BookDb(HashSet<Book> books){
        this.books = books;
    }

    @Override
    public Book Cretae(Book newEntity) throws Exception {
        books.add(newEntity);
        return newEntity;
    }

    @Override
    public Book Deleted(Book book) throws Exception {
        boolean state = books.remove(book);
        if (state)
            return book;
        throw new Exception("Error deleted db");
    }

    @Override
    public HashSet<Book> ToList() {
        return books;
    }

    @Override
    public List<Book> HashSetToList() {
        return new ArrayList<Book>(books);
    }

    @Override
    public Book Update(Book book, Book bookNew) throws Exception {
        Deleted(book);
        return Cretae(bookNew);
    }
}
