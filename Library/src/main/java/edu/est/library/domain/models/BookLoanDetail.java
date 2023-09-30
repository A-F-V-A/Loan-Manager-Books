package edu.est.library.domain.models;

import edu.est.library.domain.dto.BookLoanDetailDto;
import edu.est.library.domain.dto.LoanBookDto;

import java.security.PublicKey;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.RecursiveTask;

public class BookLoanDetail implements  Comparable<BookLoanDetail> {
    private Book DetailBook;
    private Date Loandate;
    private String DetailComment;
    public BookLoanDetail(){}
    public BookLoanDetail(Book book, Date date, String comment){
        DetailBook = book;
        Loandate = date;
        DetailComment = comment;
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
    public int compareTo(BookLoanDetail o) {
        return 0;
    }
    public BookLoanDetailDto viewData(){
        BookLoanDetailDto view = new BookLoanDetailDto();
        view.isbn = DetailBook.getIsbn();
        view.bookTitle = DetailBook.getTitle();
        view.comment = DetailComment;
        view.date = Loandate;
        return view;
    }

}
