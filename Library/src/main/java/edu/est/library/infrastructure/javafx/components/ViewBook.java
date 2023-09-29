package edu.est.library.infrastructure.javafx.components;

import edu.est.library.application.services.BookService;
import edu.est.library.domain.models.Book;
import edu.est.library.infrastructure.db.BookDb;
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

public class ViewBook implements IEventTableView<Book> {

    private final BookService service;
    private ObservableList<Book> bookList;
    private Book seletecBook;
    private Book previousState;
    public ViewBook(BookDb context){
        service = new BookService(context);

    }
    public static HBox containerInputs(){
        HBox container = new HBox();

        container.getChildren().add(Components.Input("Title"));
        container.getChildren().add(Components.Input("Author"));
        container.getChildren().add(Components.Input("Isbn"));
        container.getChildren().add(Components.InputDatePicker("Date", LocalDate.now()));
        container.getChildren().add(Components.Input("Quantity"));

        return container;
    }
    public TableView<Book> TableViewBooks(){
        bookList = FXCollections.observableArrayList();
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
        TableColumn<Book, Date> publicationDateColumn = new TableColumn<>("Publication Date");
        TableColumn<Book, Integer> quantityColumn = new TableColumn<>("Quantity");

        // Asignar qué atributo de Book corresponde a cada columna
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("Author"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("Isbn"));
        publicationDateColumn.setCellValueFactory(new PropertyValueFactory<>("PublicationDate"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        bookList.addAll(service.HashSetToList());

        // Crear la TableView y asignar las columnas
        TableView<Book> tableView = new TableView<>();
        tableView.getColumns().addAll(titleColumn, authorColumn, isbnColumn, publicationDateColumn, quantityColumn);

        // Asignar la lista de libros a la TableView
        tableView.setItems(bookList);
        AnchorPane.setLeftAnchor(tableView, 0.0);
        AnchorPane.setRightAnchor(tableView, 0.0);
        AnchorPane.setTopAnchor(tableView, 0.0);
        AnchorPane.setBottomAnchor(tableView, 0.0);

        EventTableView(tableView);
        return tableView;
    }
    public void AddBook(VBox element){
        try {
            Book book = new Book();

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
                            FillBook(book,nameAttribute,value.getText());
                        } else if (child instanceof DatePicker value) {
                            String nameAttribute = (String) value.getUserData();
                            FillBook(book,nameAttribute,value.getValue().toString());
                        }
                    }
                }
            }


            if(book.isIncomplete()) throw new Exception("Error Book incomplete");
            bookList.add(Create(book));
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
    public void Update(VBox element,boolean flag){
        try {
            Book book = new Book();
            if(seletecBook == null) throw new Exception("I don't select a book");
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
                            else FillBook(book,nameAttribute,value.getText());
                        } else if (child instanceof DatePicker value) {
                            String nameAttribute = (String) value.getUserData();
                            if(!flag) fillInputs(child,nameAttribute);
                            else FillBook(book,nameAttribute,value.getValue().toString());
                        }
                    }
                }
            }

            if (!flag){
                previousState = new Book(
                        seletecBook.getTitle(),
                        seletecBook.getAuthor(),
                        seletecBook.getIsbn(),
                        seletecBook.getPublicationDate(),
                        seletecBook.getQuantity()
                );
            }else{
                Update(book);
                bookList.clear();
                bookList.addAll(service.HashSetToList());
                previousState = null;
                seletecBook = null;
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
    public void Delete()  {
        try {
            if(seletecBook == null) throw new Exception("I don't select a book");
            Book deleted = service.Deleted(seletecBook);
            bookList.remove(deleted);
        } catch (Exception e) {
            System.err.println(e);
            Components.AlertLibrary(
                    Alert.AlertType.ERROR,
                    "can't delete",
                    e.getMessage(),
                    "");
        }
    }
    private void fillInputs(Node node, String name){
        if (name != null && name.equals("Title") && node instanceof TextField value)
            value.setText(seletecBook.getTitle());
        else if(name != null && name.equals("Author") && node instanceof TextField value)
            value.setText(seletecBook.getAuthor());
        else if(name != null && name.equals("Isbn") && node instanceof TextField value)
            value.setText(seletecBook.getIsbn());
        else if(name != null && name.equals("Date") && node instanceof DatePicker value){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Instant instant = seletecBook.getPublicationDate().toInstant();
            ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
            LocalDate localDate = zonedDateTime.toLocalDate();
            value.setValue(localDate);
        }
        else if(name != null && name.equals("Quantity") && node instanceof TextField value)
            value.setText(seletecBook.getQuantity() + "");
    }
    private void FillBook(Book book,String name,String value){
        if (name != null && name.equals("Title"))
            book.setTitle(value);
        else if(name != null && name.equals("Author"))
            book.setAuthor(value);
        else if(name != null && name.equals("Isbn"))
            book.setIsbn(value);
        else if(name != null && name.equals("Date")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                book.setPublicationDate(dateFormat.parse(value));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        else if(name != null && name.equals("Quantity"))
            book.setQuantity(Integer.parseInt(value));
    }
    private Book Create(Book book) throws Exception {
        int size = service.ToList().size();
        var newBook = service.Cretae(book);
        if(size == service.ToList().size()) throw new Exception("There is already a book");
        return newBook;
    }
    private void Update(Book book){
        try {
            if(book == null) throw new Exception("I don't select a book");
            Book bookService;
            for (Book item: service.ToList()){
                if(item.equals(previousState)){
                    service.Update(item,book);
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
    public void EventTableView(TableView<Book> tableView) {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) ->{
            if (newSelection == null) return;
            seletecBook = newSelection;
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
