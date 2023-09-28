package edu.est.library.application.services;

import edu.est.library.application.usecases.ILoanBookService;
import edu.est.library.domain.models.LoanBook;

import java.util.HashSet;
import java.util.List;

public class LoanBookService implements ILoanBookService<LoanBook> {

    @Override
    public LoanBook Cretae(LoanBook newEntity) throws Exception {
        return null;
    }

    @Override
    public LoanBook Deleted(LoanBook loanBook) throws Exception {
        return null;
    }

    @Override
    public HashSet<LoanBook> ToList() {
        return null;
    }

    @Override
    public List<LoanBook> HashSetToList() {
        return null;
    }

    @Override
    public LoanBook Update(LoanBook loanBook, LoanBook entityNew) throws Exception {
        return null;
    }
}
