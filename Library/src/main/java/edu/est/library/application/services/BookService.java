package edu.est.library.application.services;

import edu.est.library.application.usecases.IBookService;
import edu.est.library.domain.interfaces.repository.ICrud;
import edu.est.library.domain.interfaces.repository.ICrudBook;
import edu.est.library.domain.models.Book;
import edu.est.library.domain.models.Library;

import javax.swing.text.html.parser.Entity;
import java.util.HashSet;
import java.util.List;

public class BookService implements IBookService<Book> {

    private final ICrudBook<Book> service;

    public BookService(ICrudBook<Book> service){
        this.service = service;
    }

    @Override
    public Book Cretae(Book newEntity) throws Exception {
        if(newEntity == null) throw new Exception("Book null");
        return service.Cretae(newEntity);
    }

    @Override
    public Book Deleted(Book book) throws Exception {
        if(book == null) throw new Exception("Book null");
        return service.Deleted(book);
    }

    @Override
    public HashSet<Book> ToList() {
        return service.ToList();
    }

    @Override
    public List<Book> HashSetToList() {
        return service.HashSetToList();
    }

    @Override
    public Book Update(Book book, Book entityNew) throws Exception {
        if(book == null) throw new Exception("Book null");
        if(entityNew == null) throw new Exception("Update book null");
        return service.Update(book,entityNew);
    }

    @Override
    public int lendBook(Book book) throws Exception {
        return service.lendBook(book);
    }
}
