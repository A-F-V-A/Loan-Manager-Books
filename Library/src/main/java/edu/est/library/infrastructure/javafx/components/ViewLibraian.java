package edu.est.library.infrastructure.javafx.components;

import edu.est.library.application.services.LibrarianService;
import edu.est.library.domain.models.Librarian;
import edu.est.library.infrastructure.db.LibrarianDb;
import edu.est.library.infrastructure.javafx.components.Util.IEventTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class ViewLibraian implements IEventTableView<Librarian> {
    private final LibrarianService service;
    private ObservableList<Librarian> librariansList;
    private Librarian seletecLibrarian;
    private Librarian previousState;
    public ViewLibraian (LibrarianDb contex){
        service = new LibrarianService(contex);
    }
    public static HBox containerInputs(){
        HBox container = new HBox();

        container.getChildren().add(Components.Input("firstName"));
        container.getChildren().add(Components.Input("lastName"));
        container.getChildren().add(Components.Input("identification"));
        container.getChildren().add(Components.InputDatePicker("startDate", LocalDate.now()));

        return container;
    }
    public TableView<Librarian> TableViewLibrarian(){
        librariansList = FXCollections.observableArrayList();
        TableColumn<Librarian, String> lastName = new TableColumn<>("Last Name");
        TableColumn<Librarian, String> firstName = new TableColumn<>("First Name");
        TableColumn<Librarian, String> identification = new TableColumn<>("Identification");
        TableColumn<Librarian, LocalDate> startDate = new TableColumn<>("Start Date");

        lastName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        identification.setCellValueFactory(new PropertyValueFactory<>("identification"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        librariansList.addAll(service.HashSetToList());


        TableView<Librarian> tableView = new TableView<>();

        tableView.getColumns().addAll(lastName, firstName, identification,startDate);


        tableView.setItems(librariansList);
        AnchorPane.setLeftAnchor(tableView, 0.0);
        AnchorPane.setRightAnchor(tableView, 0.0);
        AnchorPane.setTopAnchor(tableView, 0.0);
        AnchorPane.setBottomAnchor(tableView, 0.0);

        EventTableView(tableView);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return tableView;
    }

    public void AddLibrarian(VBox element){
        try {
            Librarian librarian = new Librarian();

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
                            FillStudent(librarian,nameAttribute,value.getText());
                        }else if (child instanceof DatePicker value) {
                            librarian.setStartDate(value.getValue());
                        }
                    }
                }
            }

            if(librarian.isIncomplete()) throw new Exception("Error librarian incomplete");
            librariansList.add(Create(librarian));
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
    private void FillStudent(Librarian librarian, String name,String value) {
        if (name != null && name.equals("firstName"))
            librarian.setFirstName(value);
        else if(name != null && name.equals("lastName"))
            librarian.setLastName(value);
        else if(name != null && name.equals("identification"))
            librarian.setIdentification(value);
    }
    public void Update(VBox element,boolean flag){
        try {
            Librarian librarian = new Librarian();
            if(seletecLibrarian == null) throw new Exception("I don't select a Librarian");
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
                            else FillStudent(librarian,nameAttribute,value.getText());
                        }
                    }
                }
            }

            if (!flag){
                previousState = new Librarian(
                        seletecLibrarian.getFirstName(),
                        seletecLibrarian.getLastName(),
                        seletecLibrarian.getIdentification(),
                        seletecLibrarian.getStartDate()
                );
            }else{
                Update(librarian);
                librariansList.clear();
                librariansList.addAll(service.HashSetToList());
                previousState = null;
                seletecLibrarian = null;
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
        if (name != null && name.equals("firstName") && node instanceof TextField value)
            value.setText(seletecLibrarian.getFirstName());
        else if(name != null && name.equals("lastName") && node instanceof TextField value)
            value.setText(seletecLibrarian.getLastName());
        else if(name != null && name.equals("identification") && node instanceof TextField value)
            value.setText(seletecLibrarian.getIdentification());
        else if (name != null && name.equals("startDate") && node instanceof DatePicker value) {
            value.setValue(seletecLibrarian.getStartDate());
        }
    }
    public void Delete()  {
        try {
            if(seletecLibrarian == null) throw new Exception("I don't select a student");
            Librarian deleted = service.Deleted(seletecLibrarian);
            librariansList.remove(deleted);
        } catch (Exception e) {
            System.err.println(e);
            Components.AlertLibrary(
                    Alert.AlertType.ERROR,
                    "can't delete",
                    e.getMessage(),
                    "");
        }
    }

    private Librarian Create(Librarian librarian) throws Exception {
        return service.Cretae(librarian);
    }
    private void Update(Librarian librarian){
        try {
            if(librarian == null) throw new Exception("I don't select a librarian");
            for (Librarian item: service.ToList()){
                if(item.equals(previousState)){
                    service.Update(item,librarian);
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
    public void EventTableView(TableView<Librarian> tableView) {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) ->{
            if (newSelection == null) return;
            seletecLibrarian = newSelection;
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
