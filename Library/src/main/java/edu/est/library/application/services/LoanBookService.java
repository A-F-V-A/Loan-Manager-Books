package edu.est.library.application.services;

import edu.est.library.application.usecases.ILoanBookService;
import edu.est.library.domain.interfaces.repository.ICrudLoanBook;
import edu.est.library.domain.models.LoanBook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PrimitiveIterator;

public class LoanBookService implements ILoanBookService<LoanBook> {

    private final ICrudLoanBook<LoanBook> service;

    public LoanBookService(ICrudLoanBook<LoanBook> service){ this.service = service; }

    @Override
    public LoanBook Cretae(LoanBook newEntity) throws Exception {
        if(newEntity == null) throw new Exception("LoanBook null");
        return service.Cretae(newEntity);
    }

    @Override
    public LoanBook Deleted(LoanBook loanBook) throws Exception {
        if(loanBook == null) throw new Exception("LoanBook null");
        return service.Deleted(loanBook);
    }

    @Override
    public LoanBook Update(LoanBook loanBook) throws Exception {
        if(loanBook == null) throw new Exception("LoanBook null");
        return service.Update(loanBook);
    }

    @Override
    public HashMap<String,LoanBook> ToList() {
        return service.ToList();
    }

    @Override
    public List<LoanBook> HashMapToList() {
        return service.HashMapToList();
    }
}
