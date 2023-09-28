package edu.est.library.infrastructure.javafx.controllers;

import edu.est.library.domain.models.Book;
import edu.est.library.infrastructure.db.LibraryDB;
import edu.est.library.infrastructure.javafx.components.ViewBook;
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
    private  ViewBook viewBook;
    @FXML
    public Button B_add;
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
    }

    @FXML
    public void AddBookView(ActionEvent event){
        RemoveClass();
        container_inputs.getStyleClass().add("AddBookView");
        container_inputs.getChildren().add(ViewBook.containerInputs());


        container_table.getChildren().add(viewBook.TableViewBooks());
        System.out.println(container_inputs.getStyleClass().contains("AddBookView"));
    }
    @FXML
    public void AddClientView(ActionEvent event){
        RemoveClass();
        container_inputs.getStyleClass().add("AddClientView");
        container_inputs.getChildren().add(ViewStudent.containerInputs());

        System.out.println(container_inputs.getStyleClass().contains("AddClientView"));
    }
    @FXML
    public void AddRegister(ActionEvent event){
        if(container_inputs.getStyleClass().contains("AddClientView")){
            System.out.println("AddClientView");
        }else if(container_inputs.getStyleClass().contains("AddBookView")){
            viewBook.AddBook(container_inputs);
        }
    }

    private void RemoveClass() {
        container_inputs.getChildren().clear();
        container_table.getChildren().clear();
        container_inputs.getStyleClass().remove("ViewAddBook");
        container_inputs.getStyleClass().remove("AddClientView");
    }

    /*
    * Crude stocks
    *
    */
    @FXML
    public void Delete(ActionEvent event){
        if(container_inputs.getStyleClass().contains("AddClientView")){
            System.out.println("AddClientView");
        }else if(container_inputs.getStyleClass().contains("AddBookView")){
            viewBook.Delete();
        }
    }

    @FXML
    public void Update(ActionEvent event){
        Button btn = (Button) event.getSource();
        boolean flag = btn.getStyleClass().contains("UpdateView");




        if(container_inputs.getStyleClass().contains("AddClientView")){
            System.out.println("AddClientView");
        }else if(container_inputs.getStyleClass().contains("AddBookView")){
            viewBook.Update(container_inputs,flag);
        }

        if(!flag) {
            btn.getStyleClass().add("UpdateView");
        }
        else{
            btn.getStyleClass().remove("UpdateView");
        }
    }

}
