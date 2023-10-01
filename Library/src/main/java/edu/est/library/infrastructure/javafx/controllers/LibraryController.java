package edu.est.library.infrastructure.javafx.controllers;

import edu.est.library.domain.models.Book;
import edu.est.library.infrastructure.db.LibraryDB;
import edu.est.library.infrastructure.javafx.components.ViewBook;
import edu.est.library.infrastructure.javafx.components.ViewLibraian;
import edu.est.library.infrastructure.javafx.components.ViewLoanBook;
import edu.est.library.infrastructure.javafx.components.ViewStudent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {

    private final LibraryDB library = new LibraryDB();
    private ViewBook viewBook;
    private ViewStudent viewStudent;
    private  ViewLibraian viewLibraian;

    private ViewLoanBook viewLoanBook;

    private String CurrentView;
    @FXML
    public Button B_add;
    @FXML
    public Button B_view_detail;
    @FXML
    public Button B_deleted;
    @FXML
    public Label L_title_view;
    @FXML
    public VBox container_inputs;
    @FXML
    public AnchorPane container_table;
    @FXML
    public VBox menu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewBook = new ViewBook(library.getBooks());
        viewStudent = new ViewStudent(library.getStudent());
        viewLibraian = new ViewLibraian(library.getLibrarian());
        viewLoanBook = new ViewLoanBook(library.getLoanBookDb(),viewBook,viewStudent,viewLibraian,container_table,CurrentView);
    }

    /** Menu **/
    @FXML
    public void AddBookView(ActionEvent event){
        CleanView();
        container_inputs.getChildren().add(ViewBook.containerInputs());
        container_table.getChildren().add(viewBook.TableViewBooks());
        L_title_view.setText("Add Book");
        CurrentView = "AddBookView";
    }
    @FXML
    public void AddClientView(ActionEvent event){
        CleanView();
        container_inputs.getChildren().add(ViewStudent.containerInputs());
        container_table.getChildren().add(viewStudent.TableViewStudent());
        L_title_view.setText("Add Student");
        CurrentView = "AddClientView";
    }

    @FXML
    public void AddEmployee(ActionEvent event){
        CleanView();
        container_inputs.getChildren().add(ViewLibraian.containerInputs());
        container_table.getChildren().add(viewLibraian.TableViewLibrarian());
        L_title_view.setText("Add Employee");
        CurrentView = "AddEmployee";
    }

    @FXML
    public void LoanManagement(ActionEvent event){
        CleanView();
        container_inputs.getChildren().add(viewLoanBook.containerInputs(container_inputs));
        container_table.getChildren().add(viewLoanBook.TableViewLoanBook());
        L_title_view.setText("Loan Management");
        CurrentView = "LoanManagement";
    }

    /** Crud logic **/
    @FXML
    public void AddRegister(ActionEvent event){
        if(CurrentView.equals("AddClientView")) viewStudent.Addstudent(container_inputs);
        else if(CurrentView.equals("AddBookView")) viewBook.AddBook(container_inputs);
        else if(CurrentView.equals("AddEmployee")) viewLibraian.AddLibrarian(container_inputs);
        else if(CurrentView.equals("LoanManagement"))  viewLoanBook.CreateLoad();
    }

    @FXML
    public void Update(ActionEvent event){
        Button btn = (Button) event.getSource();
        boolean flag = btn.getStyleClass().contains("UpdateView");

        if(CurrentView.equals("AddClientView")) viewStudent.Update(container_inputs,flag);
        else if(CurrentView.equals("AddBookView"))  viewBook.Update(container_inputs,flag);
        else if(CurrentView.equals("AddEmployee")) viewLibraian.Update(container_inputs,flag);
        else if(CurrentView.equals("ViewDetail")) viewLoanBook.UpdateLoad();

        if(!flag) btn.getStyleClass().add("UpdateView");
        else btn.getStyleClass().remove("UpdateView");
    }

    @FXML
    public void Delete(ActionEvent event){
        if(CurrentView.equals("AddClientView")) viewStudent.Delete();
        else if(CurrentView.equals("AddBookView")) viewBook.Delete();
        else if(CurrentView.equals("AddEmployee")) viewLibraian.Delete();
        else if(CurrentView.equals("LoanManagement")) viewLoanBook.deletedLoanBook();
        else if(CurrentView.equals("ViewDetail")) viewLoanBook.deletedBookLoanDetail();
    }

    @FXML
    public void ViewDetail(ActionEvent event){
        CurrentView = "ViewDetail";
        container_table.getChildren().add(viewLoanBook.TableViewLoanBookDetail());
    }



    /** Utilitarian **/
    private void CleanView() {
        container_inputs.getChildren().clear();
        container_table.getChildren().clear();
    }

}
