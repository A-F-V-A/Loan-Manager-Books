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

    public String getId() {
        return id.toString();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Student getLoanStudent() {
        return LoanStudent;
    }

    public void setLoanStudent(Student loanStudent) {
        LoanStudent = loanStudent;
    }

    public Librarian getLoanLibrarian() {
        return LoanLibrarian;
    }

    public void setLoanLibrarian(Librarian loanLibrarian) {
        LoanLibrarian = loanLibrarian;
    }

    public Date getLoanDate() {
        return LoanDate;
    }

    public void setLoanDate(Date loanDate) {
        LoanDate = loanDate;
    }

    public HashMap<LoanBook, TreeSet<BookLoanDetail>> getBookLoanDetails() {
        return BookLoanDetails;
    }

    public void setBookLoanDetails(HashMap<LoanBook, TreeSet<BookLoanDetail>> bookLoanDetails) {
        BookLoanDetails = bookLoanDetails;
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
