package edu.est.library.application.services;

import edu.est.library.application.usecases.IStudentService;
import edu.est.library.domain.interfaces.repository.ICrudStudent;
import edu.est.library.domain.models.Student;

import java.util.List;
import java.util.TreeSet;

public class StudentService implements IStudentService<Student> {

    private final ICrudStudent<Student> service;

    public StudentService(ICrudStudent<Student> service){ this.service = service;}

    @Override
    public Student Cretae(Student newEntity) throws Exception {
        if(newEntity == null) throw new Exception("Book null");
        return service.Cretae(newEntity);
    }

    @Override
    public Student Deleted(Student student) throws Exception {
        if(student == null) throw new Exception("Book null");
        return service.Deleted(student);
    }

    @Override
    public Student Update(Student student, Student entityNew) throws Exception {
        if(student == null) throw new Exception("Book null");
        if(entityNew == null) throw new Exception("Update book null");
        return service.Update(student,entityNew);
    }

    @Override
    public TreeSet<Student> ToList()  {
        return service.ToList();
    }

    @Override
    public List<Student> TreeSetToList() {
        return service.TreeSetToList();
    }
}
