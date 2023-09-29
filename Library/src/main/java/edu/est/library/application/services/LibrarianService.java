package edu.est.library.application.services;

import edu.est.library.application.usecases.ILibrarianService;
import edu.est.library.domain.interfaces.repository.ICrud;
import edu.est.library.domain.models.Librarian;

import java.util.HashSet;
import java.util.List;

public class LibrarianService implements ILibrarianService<Librarian> {

    private final ICrud<Librarian> service;

    public LibrarianService( ICrud<Librarian> service){ this.service = service;}

    @Override
    public Librarian Cretae(Librarian newEntity) throws Exception {
        if(newEntity == null) throw new Exception("Librarian null");
        return service.Cretae(newEntity);
    }

    @Override
    public Librarian Deleted(Librarian librarian) throws Exception {
        if(librarian == null) throw new Exception("Librarian null");
        return service.Deleted(librarian);
    }

    @Override
    public HashSet<Librarian> ToList() {
        return service.ToList();
    }

    @Override
    public List<Librarian> HashSetToList() {
        return service.HashSetToList();
    }

    @Override
    public Librarian Update(Librarian librarian, Librarian entityNew) throws Exception {
        if(librarian == null) throw new Exception("Librarian null");
        if(entityNew == null) throw new Exception("Update Librarian null");
        return service.Update(librarian,entityNew);
    }
}
