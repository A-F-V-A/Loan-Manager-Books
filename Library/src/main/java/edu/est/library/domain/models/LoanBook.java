package edu.est.library.domain.models;

import java.util.Date;
import java.util.HashMap;

public class LoanBook {
    private Student LoanStudent;
    private Date LoanDate;
    private HashMap<Integer, BookLoanDetail> BookLoanDetails;

    public LoanBook(Student student, Date loanDate){
        LoanStudent = student;
        LoanDate = loanDate;
        BookLoanDetails = new HashMap<Integer, BookLoanDetail>();
    }

    public void AddBookLoanDetail(int id,BookLoanDetail detail){
        BookLoanDetails.put(id,detail);
    }

}
