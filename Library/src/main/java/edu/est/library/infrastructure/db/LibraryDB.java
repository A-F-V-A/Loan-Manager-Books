package edu.est.library.infrastructure.db;
import edu.est.library.domain.models.Book;
import edu.est.library.domain.models.Library;

import java.util.Date;

public class LibraryDB  {
    private Library library;
    private BookDb books;
    public LibraryDB() {
        library = new Library();
        books = new BookDb(library.getBooks());
        dataInit();
    }
    public void dataInit()  {

        try {
            Date date1 = new Date(122, 2, 15); // Fecha: 15 de marzo de 2022
            Date date2 = new Date(121, 8, 30); // Fecha: 30 de septiembre de 2021
            Date date3 = new Date(120, 4, 10); // Fecha: 10 de mayo de 2020

            Book book1 = new Book("El Jardín Secreto", "Frances Hodgson Burnett", date1,"0001");
            Book book2 = new Book("La Sombra del Viento", "Carlos Ruiz Zafón", date2,"0011");
            Book book3 = new Book("1984", "George Orwell", date3,"0010");

            books.Cretae(book1);
            books.Cretae(book2);
            books.Cretae(book3);
        }catch (Exception e){
            System.err.println(e);
        }
    }

    public Library getLibrary() {
        return library;
    }

    public BookDb getBooks() {
        return books;
    }
}