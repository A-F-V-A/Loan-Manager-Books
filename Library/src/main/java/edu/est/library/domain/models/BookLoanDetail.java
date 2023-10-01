package edu.est.library.domain.models;

import edu.est.library.domain.dto.BookLoanDetailDto;

import java.util.Date;
import java.util.UUID;

public class BookLoanDetail implements  Comparable<BookLoanDetail> {
    private final UUID id;
    private Book DetailBook;
    private Date Loandate;
    private String DetailComment;

    public BookLoanDetail(Book book, Date date, String comment,UUID id){
        DetailBook = book;
        Loandate = date;
        DetailComment = comment;
        this.id = id;
    }

    public String getId() {
        return id.toString();
    }

    public Book getDetailBook() {
        return DetailBook;
    }

    public Date getLoandate() {
        return Loandate;
    }

    public String getDetailComment() {
        return DetailComment;
    }

    public void setDetailBook(Book detailBook) {
        DetailBook = detailBook;
    }

    public void setLoandate(Date loandate) {
        Loandate = loandate;
    }

    public void setDetailComment(String detailComment) {
        DetailComment = detailComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookLoanDetail that)) return false;

        if(!that.DetailBook.equals(DetailBook)) return false;
        if(that.Loandate != Loandate) return false;

        return that.DetailComment.equals(DetailComment);
    }

    @Override
    public int compareTo(BookLoanDetail otherDetail) {

        int dateComparison = Loandate.compareTo(otherDetail.Loandate);
        if (dateComparison != 0) return dateComparison;

        return DetailBook.getTitle().compareTo(otherDetail.DetailBook.getTitle());
    }
    public BookLoanDetailDto viewData(){
        BookLoanDetailDto view = new BookLoanDetailDto();
        view.isbn = DetailBook.getIsbn();
        view.bookTitle = DetailBook.getTitle();
        view.comment = DetailComment;
        view.date = Loandate;
        view.id = getId();
        return view;
    }

}
