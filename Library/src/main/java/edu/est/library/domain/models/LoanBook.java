package edu.est.library.domain.models;

import edu.est.library.domain.dto.LoanBookDto;

import java.util.*;

public class LoanBook {
    private UUID id;
    private Student LoanStudent;
    private Librarian LoanLibrarian;
    private Date LoanDate;
    private HashMap<LoanBook, TreeSet<BookLoanDetail>> BookLoanDetails;

    public LoanBook(Student student, Date loanDate,Librarian librarian){
        LoanStudent = student;
        LoanDate = loanDate;
        LoanLibrarian = librarian;
        BookLoanDetails = new HashMap<LoanBook, TreeSet<BookLoanDetail>>();
        generateID(
                LoanStudent.toString(),
                LoanLibrarian.toString(),
                LoanDate.toString()
        );
    }

    private void generateID(String... value){
        StringBuilder keys = new StringBuilder();
        for (String val: value) keys.append(val);
        id = UUID.nameUUIDFromBytes(keys.toString().getBytes());
    }

    public LoanBookDto viewData(){
        LoanBookDto view = new LoanBookDto();

        view.id = id.toString();
        view.studentName = LoanStudent.getFirstName() + " " + LoanStudent.getLastName();
        view.librarianName = LoanLibrarian.getFirstName() + " " + LoanLibrarian.getLastName();
        view.date = LoanDate;

        return view;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanBook loanBook)) return false;
        return Objects.equals(id, loanBook.id) && Objects.equals(LoanStudent, loanBook.LoanStudent) && Objects.equals(LoanLibrarian, loanBook.LoanLibrarian) && Objects.equals(LoanDate, loanBook.LoanDate) && Objects.equals(BookLoanDetails, loanBook.BookLoanDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, LoanStudent, LoanLibrarian, LoanDate, BookLoanDetails);
    }
}
