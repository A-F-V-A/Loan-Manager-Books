package edu.est.library.domain.models;

import java.security.PublicKey;
import java.util.Objects;

public class BookLoanDetail {
    private Book DetailBook;
    private String DetailComment;
    public BookLoanDetail(Book book){
        DetailBook = book;
    }
    public  BookLoanDetail(Book book, String comment){
        this(book);
        DetailComment = comment;
    }
    public String getDetailComment() {
        return DetailComment;
    }
    public void setDetailComment(String detailComment) {
        DetailComment = detailComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookLoanDetail that)) return false;
        return Objects.equals(DetailBook, that.DetailBook) && Objects.equals(DetailComment, that.DetailComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(DetailBook, DetailComment);
    }
}
