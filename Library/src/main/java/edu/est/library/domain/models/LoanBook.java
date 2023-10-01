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
        generateID(
                LoanStudent.toString(),
                LoanLibrarian.toString()
        );
        BookLoanDetails = new HashMap<LoanBook, TreeSet<BookLoanDetail>>();
        BookLoanDetails.put(this,new TreeSet<BookLoanDetail>());


    }

    public String getId() {
        return id.toString();
    }

    public UUID getIdUUID() {
        return id;
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

    public TreeSet<BookLoanDetail> Details() {
        return BookLoanDetails.get(this);
    }

    public void addDetails(BookLoanDetail... details) {
        TreeSet<BookLoanDetail> Details = BookLoanDetails.get(this);

        Details.addAll(Arrays.asList(details));

        BookLoanDetails.put(this, Details);
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
        LoanBook otherLoanBook = (LoanBook) o;
        return id.toString().equals(otherLoanBook.getId());
    }

    @Override
    public int hashCode() {
        int hash = 31;
        hash = hash * id.toString().hashCode() + LoanStudent.toString().hashCode() + LoanLibrarian.toString().hashCode();
        return hash;
    }

}
