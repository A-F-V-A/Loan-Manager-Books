package edu.est.library.domain.models;

import java.util.Date;
import java.util.HashMap;
import java.util.TreeSet;

public class LoanBook {
    private Student LoanStudent;
    private Date LoanDate;
    private HashMap<LoanBook, TreeSet<BookLoanDetail>> BookLoanDetails;

    public LoanBook(Student student, Date loanDate){
        LoanStudent = student;
        LoanDate = loanDate;
        BookLoanDetails = new HashMap<LoanBook, TreeSet<BookLoanDetail>>();
    }


}
