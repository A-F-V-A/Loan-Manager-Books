package edu.est.library.application.services;

import edu.est.library.application.usecases.ILibrarianService;
import edu.est.library.domain.models.Librarian;

import java.util.HashSet;
import java.util.List;

public class LibrarianService implements ILibrarianService<Librarian> {

    @Override
    public Librarian Cretae(Librarian newEntity) throws Exception {
        return null;
    }

    @Override
    public Librarian Deleted(Librarian librarian) throws Exception {
        return null;
    }

    @Override
    public HashSet<Librarian> ToList() {
        return null;
    }

    @Override
    public List<Librarian> HashSetToList() {
        return null;
    }

    @Override
    public Librarian Update(Librarian librarian, Librarian entityNew) throws Exception {
        return null;
    }
}
