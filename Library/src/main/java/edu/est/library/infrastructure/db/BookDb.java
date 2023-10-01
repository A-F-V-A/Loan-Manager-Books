package edu.est.library.infrastructure.db;

import edu.est.library.domain.interfaces.repository.ICrud;
import edu.est.library.domain.interfaces.repository.ICrudBook;
import edu.est.library.domain.models.Book;
import javafx.scene.chart.ValueAxis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class BookDb implements ICrudBook<Book> {

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


    @Override
    public int lendBook(Book book) throws Exception {

        Iterator<Book> iterador = books.iterator();
        int value = -1;

        while (iterador.hasNext()) {
            Book modifyBook = iterador.next();

            if (modifyBook.equals(book)) {
                System.out.println("Found book: " + modifyBook.toString());
                value = modifyBook.Quantity(1);
                break;
            }
        }
        if(value == -1) throw new Exception("It is not possible to lend the book");
        return value;
    }
}
