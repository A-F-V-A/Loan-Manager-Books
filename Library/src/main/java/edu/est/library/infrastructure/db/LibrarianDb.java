package edu.est.library.infrastructure.db;

import edu.est.library.domain.interfaces.repository.ICrud;
import edu.est.library.domain.models.Librarian;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LibrarianDb implements ICrud<Librarian> {

    private final HashSet<Librarian> librarians;

    public LibrarianDb(HashSet<Librarian> librarians){ this.librarians = librarians;}

    @Override
    public Librarian Cretae(Librarian newEntity) throws Exception {
        int size = librarians.size();
        librarians.add(newEntity);
        if(size == librarians.size()) throw new Exception("The librarian already exists");
        return newEntity;
    }

    @Override
    public Librarian Deleted(Librarian librarian) throws Exception {
        boolean state = librarians.remove(librarian);
        if (state)
            return librarian;
        throw new Exception("Error deleted db");
    }

    @Override
    public HashSet<Librarian> ToList() {
        return librarians;
    }

    @Override
    public List<Librarian> HashSetToList() {
        return new ArrayList<Librarian>(librarians);
    }

    @Override
    public Librarian Update(Librarian librarian, Librarian entityNew) throws Exception {
        Deleted(librarian);
        return Cretae(entityNew);
    }
}
