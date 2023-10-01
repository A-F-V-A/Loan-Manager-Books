package edu.est.library.infrastructure.javafx.components;

import edu.est.library.application.services.StudentService;
import edu.est.library.domain.models.Book;
import edu.est.library.domain.models.Student;
import edu.est.library.infrastructure.db.StudentDb;
import edu.est.library.infrastructure.javafx.components.Util.IEventTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class ViewStudent implements IEventTableView<Student> {
    private final StudentService service;
    private ObservableList<Student> studentList;
    private Student seletecStudent;
    private Student previousState;
    public ViewStudent (StudentDb contex){
        service = new StudentService(contex);
    }
    public static HBox containerInputs(){
        HBox container = new HBox();

        container.getChildren().add(Components.Input("FirstName"));
        container.getChildren().add(Components.Input("LastName"));
        container.getChildren().add(Components.Input("StudentId"));

        return container;
    }
    public TableView<Student> TableViewStudent(){
        studentList = FXCollections.observableArrayList();
        TableColumn<Student, String> lastName = new TableColumn<>("Last Name");
        TableColumn<Student, String> firstName = new TableColumn<>("First Name");
        TableColumn<Student, String> studentId = new TableColumn<>("Id");


        lastName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));


        studentList.addAll(service.TreeSetToList());


        TableView<Student> tableView = new TableView<>();

        tableView.getColumns().addAll(lastName, firstName, studentId);


        tableView.setItems(studentList);
        AnchorPane.setLeftAnchor(tableView, 0.0);
        AnchorPane.setRightAnchor(tableView, 0.0);
        AnchorPane.setTopAnchor(tableView, 0.0);
        AnchorPane.setBottomAnchor(tableView, 0.0);

        EventTableView(tableView);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return tableView;
    }
    public void Addstudent(VBox element){
        try {
            Student student = new Student();

            HBox container = null;
            for (Node node : element.getChildren()) {
                if(node instanceof HBox) {
                    container = (HBox) node;
                    break;
                }
            }

            if (container == null) return;

            for (Node node : container.getChildren()) {
                if(node instanceof VBox parent){
                    for (Node child : parent.getChildren()){
                        if(child instanceof TextField value) {
                            String nameAttribute = (String) value.getUserData();
                            FillStudent(student,nameAttribute,value.getText());
                        }
                    }
                }
            }


            if(student.isIncomplete()) throw new Exception("Error Book incomplete");
            studentList.add(Create(student));
            Clean(element);
        } catch (Exception e) {
            System.err.println(e);
            Components.AlertLibrary(
                    Alert.AlertType.ERROR,
                    "Invalid Registration",
                    e.getMessage(),
                    "");
        }
    }
    private void FillStudent(Student student, String name,String value) {
        if (name != null && name.equals("FirstName"))
            student.setFirstName(value);
        else if(name != null && name.equals("LastName"))
            student.setLastName(value);
        else if(name != null && name.equals("StudentId"))
            student.setStudentId(Integer.parseInt(value));
    }
    public void Update(VBox element,boolean flag){
        try {
            Student student = new Student();
            if(seletecStudent == null) throw new Exception("I don't select a book");
            HBox container = null;
            for (Node node : element.getChildren()) {
                if(node instanceof HBox) {
                    container = (HBox) node;
                    break;
                }
            }

            if (container == null) return;

            for (Node node : container.getChildren()) {
                if(node instanceof VBox parent){
                    for (Node child : parent.getChildren()){
                        if(child instanceof TextField value) {
                            String nameAttribute = (String) value.getUserData();
                            if(!flag) fillInputs(child,nameAttribute);
                            else FillStudent(student,nameAttribute,value.getText());
                        }
                    }
                }
            }

            if (!flag){
                previousState = new Student(
                        seletecStudent.getFirstName(),
                        seletecStudent.getLastName(),
                        seletecStudent.getStudentId()
                );
            }else{
                Update(student);
                studentList.clear();
                studentList.addAll(service.TreeSetToList());
                previousState = null;
                seletecStudent = null;
                Clean(element);
            }
        } catch (Exception e) {
            System.err.println(e);
            Components.AlertLibrary(
                    Alert.AlertType.ERROR,
                    "can't update",
                    e.getMessage(),
                    "");
        }

    }

    private void fillInputs(Node node, String name){
        if (name != null && name.equals("FirstName") && node instanceof TextField value)
            value.setText(seletecStudent.getFirstName());
        else if(name != null && name.equals("LastName") && node instanceof TextField value)
            value.setText(seletecStudent.getLastName());
        else if(name != null && name.equals("StudentId") && node instanceof TextField value)
            value.setText(seletecStudent.getStudentId() + "");
    }
    public void Delete()  {
        try {
            if(seletecStudent == null) throw new Exception("I don't select a student");
            Student deleted = service.Deleted(seletecStudent);
            studentList.remove(deleted);
        } catch (Exception e) {
            System.err.println(e);
            Components.AlertLibrary(
                    Alert.AlertType.ERROR,
                    "can't delete",
                    e.getMessage(),
                    "");
        }
    }

    private Student Create(Student student) throws Exception {
        return service.Cretae(student);
    }
    private void Update(Student student){
        try {
            if(student == null) throw new Exception("I don't select a student");
            Book bookService;
            for (Student item: service.ToList()){
                if(item.equals(previousState)){
                    service.Update(item,student);
                    break;
                }
            }
        }catch (Exception e){
            System.err.println(e);
            Components.AlertLibrary(
                    Alert.AlertType.ERROR,
                    "can't update",
                    e.getMessage(),
                    "");
        }
    }

    @Override
    public void EventTableView(TableView<Student> tableView) {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) ->{
            if (newSelection == null) return;
            seletecStudent = newSelection;
        });
    }
    private void Clean(VBox element){
        HBox container = null;
        for (Node node : element.getChildren()) {
            if(node instanceof HBox) {
                container = (HBox) node;
                break;
            }
        }

        if (container == null) return;

        for (Node node : container.getChildren()) {
            if(node instanceof VBox parent){
                for (Node child : parent.getChildren()){
                    if(child instanceof TextField value) {
                        value.setText("");
                    } else if (child instanceof DatePicker value) {
                        value.setValue(null);
                    }
                }
            }
        }
    }
}
