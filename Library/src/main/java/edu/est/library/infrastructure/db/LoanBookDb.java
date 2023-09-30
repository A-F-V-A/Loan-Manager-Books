package edu.est.library.infrastructure.db;

import edu.est.library.domain.interfaces.repository.ICrudLoanBook;
import edu.est.library.domain.models.LoanBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LoanBookDb implements ICrudLoanBook<LoanBook> {

    private final HashMap<String,LoanBook> loanBooks;

    public LoanBookDb(HashMap<String, LoanBook> loanBooks) {
        this.loanBooks = loanBooks;
    }

    @Override
    public LoanBook Cretae(LoanBook newEntity) throws Exception {
        if(loanBooks.containsKey(newEntity.getId())) throw new Exception("A detail has already been recorded");
        loanBooks.put(newEntity.getId(),newEntity);
        return newEntity;
    }

    @Override
    public LoanBook Deleted(LoanBook loanBook) throws Exception {
        if(loanBooks.containsKey(loanBook.getId())) throw new Exception("The detail you are trying to delete does not exist");
        return loanBooks.remove(loanBook.getId());
    }

    @Override
    public LoanBook Update(LoanBook loanBook) throws Exception {
        return Cretae(loanBook);
    }


    @Override
    public HashMap<String, LoanBook> ToList() {
        return new HashMap<String, LoanBook>(loanBooks);
    }

    @Override
    public List<LoanBook> HashMapToList() {
        return new ArrayList<LoanBook>(loanBooks.values());
    }
}
