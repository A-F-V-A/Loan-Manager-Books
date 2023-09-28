package edu.est.library.domain.models;

import java.util.Date;
import java.util.Objects;

public class Book {
    private String Title;
    private String Author;
    private String Isbn;
    private Date PublicationDate;
    private int Quantity;

    public  Book(){}

    public Book(String title, String author, Date publicationDate, String isbn) {
        Title = title;
        Author = author;
        PublicationDate = publicationDate;
        Isbn = isbn;
    }

    public Book(String title, String author, String isbn, Date publicationDate, int quantity) {
        this(title,author,publicationDate,isbn);
        Quantity = quantity;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Date getPublicationDate() {
        return PublicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        PublicationDate = publicationDate;
    }

    public String getIsbn() {
        return Isbn;
    }

    public void setIsbn(String isbn) {
        Isbn = isbn;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public boolean isIncomplete() {
        return  Title == null || Title.isEmpty() ||
                Author == null || Author.isEmpty() ||
                Isbn == null || Isbn.isEmpty() ||
                PublicationDate == null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Book otherBook = (Book) obj;

        if (!Title.equals(otherBook.Title)) return false;
        return Isbn.equals(otherBook.Isbn);
    }

    @Override
    public int hashCode() {
        int hash = 31;
        hash = hash * Title.hashCode() + Isbn.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return "Book: {" +
                "Title:'" + Title + '\'' +
                ", Author:'" + Author + '\'' +
                ", PublicationDate:" + PublicationDate +
                '}';
    }
}
