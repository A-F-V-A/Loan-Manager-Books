package edu.est.library.domain.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class Library  {
    private HashSet<Book> Books;
    private TreeSet<Student> Students;
    private HashSet<Librarian> Librarians;
    private HashMap<Integer, LoanBook> LoanBooks;

    public Library(){
        Books = new HashSet<Book>();
    }
    public HashSet<Book> getBooks() {
        return Books;
    }
    public void addBook(Book newBook){
        Books.add(newBook);
    }
}
