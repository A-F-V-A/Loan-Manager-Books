package edu.est.library.application.services;

import edu.est.library.application.usecases.IBookService;
import edu.est.library.application.usecases.ILibrarianService;
import edu.est.library.application.usecases.ILibraryService;
import edu.est.library.domain.interfaces.repository.ICrud;
import edu.est.library.domain.models.Book;
import edu.est.library.domain.models.Librarian;
import edu.est.library.domain.models.Library;

import java.util.HashSet;
import java.util.List;

public class LibraryService implements ILibrarianService<Librarian> {

    private final ICrud<Librarian> service;

    public LibraryService (ICrud<Librarian> service){ this.service = service; }

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
