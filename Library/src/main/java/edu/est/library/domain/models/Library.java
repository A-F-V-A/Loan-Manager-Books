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
        Students = new TreeSet<Student>();
        Librarians = new HashSet<Librarian>();
    }
    public HashSet<Book> getBooks() {
        return Books;
    }
    public TreeSet<Student> getStudents() {
        return Students;
    }
    public HashSet<Librarian> getLibrarians() {
        return Librarians;
    }
    public HashMap<Integer, LoanBook> getLoanBooks() {
        return LoanBooks;
    }
}
