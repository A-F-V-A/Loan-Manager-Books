package edu.est.library.infrastructure.db;

import edu.est.library.domain.interfaces.repository.ICrudStudent;
import edu.est.library.domain.models.Book;
import edu.est.library.domain.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class StudentDb implements ICrudStudent<Student> {

    private final TreeSet<Student> students;
    private Student seletecStudent;
    private Student previousState;

    public StudentDb (TreeSet<Student> students){ this.students = students; }

    @Override
    public Student Cretae(Student newEntity) throws Exception {
        int size =  students.size();
        students.add(newEntity);
        if(size == students.size()) throw new Exception("The student already exists");
        return newEntity;
    }

    @Override
    public Student Deleted(Student student) throws Exception {
        if(!students.remove(student)) throw new Exception("An error occurred deleting");
        return student;
    }

    @Override
    public Student Update(Student student, Student entityNew) throws Exception {
        try {
            Deleted(student);
            return Cretae(entityNew);
        }catch (Exception e){
            throw new Exception("occurred Update: " + e.getMessage());
        }
    }

    @Override
    public TreeSet<Student> ToList(){
        return students;
    }
    @Override
    public List<Student> TreeSetToList() {
        return new ArrayList<Student>(students);
    }
}
