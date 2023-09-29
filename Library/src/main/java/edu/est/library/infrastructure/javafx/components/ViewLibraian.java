package edu.est.library.infrastructure.javafx.components;

import edu.est.library.application.services.LibrarianService;
import edu.est.library.domain.models.Librarian;
import edu.est.library.domain.models.Student;
import edu.est.library.infrastructure.db.LibrarianDb;
import javafx.collections.ObservableList;

public class ViewLibraian {
    private final LibrarianService service;
    private ObservableList<Librarian> librariansList;
    private Student seletecLibrarian;
    private Student previousState;
    public ViewLibraian (LibrarianDb contex){
        service = new LibrarianService(contex);
    }
}
