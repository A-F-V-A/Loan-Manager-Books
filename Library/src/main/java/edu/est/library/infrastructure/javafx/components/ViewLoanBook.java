package edu.est.library.infrastructure.javafx.components;

import edu.est.library.application.services.LoanBookService;
import edu.est.library.domain.dto.BookLoanDetailDto;
import edu.est.library.domain.dto.LoanBookDto;
import edu.est.library.domain.models.*;
import edu.est.library.infrastructure.db.LoanBookDb;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class ViewLoanBook {

    private final LoanBookService service;
    private ObservableList<LoanBookDto> ListLoanBookDto;
    private ObservableList<BookLoanDetailDto> ListBookLoanDetailDto;
    private BookLoanDetailDto selectDetail;
    private LoanBookDto seletec;
    private LoanBookDto previousState;
    private final ViewBook viewBook;
    private final ViewStudent viewStudent;
    private final ViewLibraian viewLibraian;
    private AnchorPane container_table;
    private Book selectedBook;
    private Student selectedStudent;
    private Librarian selectedLibrarian;
    private LoanBook loanBookFinal;
    private BookLoanDetail bookLoanDetailFinal;

    private  VBox parent;

    private String CurrentView;
    public ViewLoanBook(LoanBookDb contex,ViewBook viewBook,ViewStudent viewStudent,ViewLibraian viewLibraian,AnchorPane container_table,String CurrentView){
        service = new LoanBookService(contex);
        this.viewBook = viewBook;
        this.viewStudent = viewStudent;
        this.viewLibraian = viewLibraian;
        this.container_table = container_table;
        this.CurrentView = CurrentView;
    }

    public HBox containerInputs(VBox container_inputs){
        parent = container_inputs;
        HBox container = new HBox();

        EventHandler<ActionEvent> customHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                switch (((Button) event.getSource()).getUserData().toString()) {
                    case "Book" -> {
                        container_table.getChildren().clear();
                        TableView<Book> table = viewBook.TableViewBooks();
                        table.getSelectionModel().selectedItemProperty().addListener(this::handleSelectionEventBook);
                        container_table.getChildren().add(table);
                    }
                    case "Student" -> {
                        container_table.getChildren().clear();
                        TableView<Student> table = viewStudent.TableViewStudent();
                        table.getSelectionModel().selectedItemProperty().addListener(this::handleSelectionEventStudent);
                        container_table.getChildren().add(table);
                    }
                    case "Librarian" -> {
                        container_table.getChildren().clear();
                        TableView<Librarian> table = viewLibraian.TableViewLibrarian();
                        table.getSelectionModel().selectedItemProperty().addListener(this::handleSelectionEventLibrarian);
                        container_table.getChildren().add(table);
                    }
                }
            }
            public void handleSelectionEventBook(ObservableValue<? extends Book> observable, Book oldSelection, Book newSelection) {
                if (newSelection == null) return;
                selectedBook = newSelection;
                System.out.println(selectedBook.toString());
                if(selectedBook.getQuantity() != 0){
                    Node LabelText = findComponentWithUserDataFromNode(container_inputs,"labelBook");
                    if(LabelText instanceof Label value) value.setText(selectedBook.getTitle());
                }else{
                    selectedBook = null;
                    Components.AlertLibrary(
                            Alert.AlertType.WARNING,
                            "There is no book",
                            "Sorry, there are no books available.",
                            "");
                }

            }
            public void handleSelectionEventStudent(ObservableValue<? extends Student> observable, Student oldSelection, Student newSelection) {
                if (newSelection == null) return;
                selectedStudent = newSelection;
                System.out.println(selectedStudent.toString());
                Node LabelText = findComponentWithUserDataFromNode(container_inputs,"labelStudent");
                if(LabelText instanceof Label value) value.setText(selectedStudent.getFirstName() + " " + selectedStudent.getLastName());
            }
            public void handleSelectionEventLibrarian(ObservableValue<? extends Librarian> observable, Librarian oldSelection, Librarian newSelection) {
                if (newSelection == null) return;
                selectedLibrarian = newSelection;
                System.out.println(selectedLibrarian.toString());
                Node LabelText = findComponentWithUserDataFromNode(container_inputs,"labelLibrarian");
                if(LabelText instanceof Label value) value.setText(selectedLibrarian.getFirstName() + " " + selectedLibrarian.getLastName());
            }
        };

        container.getChildren().add(Components.containerButton("Book",customHandler));
        container.getChildren().add(Components.containerButton("Student",customHandler));
        container.getChildren().add(Components.containerButton("Librarian",customHandler));
        Button btn = new Button("X");
        btn.getStyleClass().add("cancel-button");
        btn.setUserData("cancel");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedLibrarian = null;
                selectedStudent = null;
                selectedBook = null;
                CurrentView = "LoanManagement";
                container_table.getChildren().clear();
                container_inputs.getChildren().clear();
                container_inputs.getChildren().add(containerInputs(container_inputs));
                container_table.getChildren().add(TableViewLoanBook());
                selectedBook = null;
                selectedStudent = null;
                selectedLibrarian = null;
                loanBookFinal = null;
                bookLoanDetailFinal = null;
            }
        });
        container.getChildren().add(btn);
        container.getChildren().add(Components.InputDatePicker("LoanDate", LocalDate.now()));
        container.getChildren().add(Components.Input("LoanComment"));

        return container;
    }
    public TableView<LoanBookDto> TableViewLoanBook(){
        ListLoanBookDto = FXCollections.observableArrayList();
        TableColumn<LoanBookDto, String> id = new TableColumn<>("Id");
        TableColumn<LoanBookDto, String> studentName = new TableColumn<>("Student");
        TableColumn<LoanBookDto, String> librarianName = new TableColumn<>("Librarian");
        TableColumn<LoanBookDto, Date> date = new TableColumn<>("Date");


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        librarianName.setCellValueFactory(new PropertyValueFactory<>("librarianName"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));



        ListLoanBookDto.addAll(LoanBookToDto());


        TableView<LoanBookDto> tableView = new TableView<>();

        tableView.getColumns().addAll(id, studentName, librarianName,date);


        tableView.setItems(ListLoanBookDto);
        AnchorPane.setLeftAnchor(tableView, 0.0);
        AnchorPane.setRightAnchor(tableView, 0.0);
        AnchorPane.setTopAnchor(tableView, 0.0);
        AnchorPane.setBottomAnchor(tableView, 0.0);

        EventTableView(tableView);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return tableView;
    }
    public TableView<BookLoanDetailDto> TableViewLoanBookDetail(){
        ListBookLoanDetailDto = FXCollections.observableArrayList();
        TableColumn<BookLoanDetailDto, String> isbn = new TableColumn<>("Isbn");
        TableColumn<BookLoanDetailDto, String> bookTitle = new TableColumn<>("Book Title");
        TableColumn<BookLoanDetailDto, Date> date = new TableColumn<>("Date");
        TableColumn<BookLoanDetailDto, String> comment = new TableColumn<>("Comment");

        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        bookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));

        ListBookLoanDetailDto.addAll(BookLoanDetailToDto());

        TableView<BookLoanDetailDto> tableView = new TableView<>();

        tableView.getColumns().addAll(isbn, bookTitle, date,comment);

        tableView.setItems(ListBookLoanDetailDto);
        AnchorPane.setLeftAnchor(tableView, 0.0);
        AnchorPane.setRightAnchor(tableView, 0.0);
        AnchorPane.setTopAnchor(tableView, 0.0);
        AnchorPane.setBottomAnchor(tableView, 0.0);

        EventTableView(tableView,"TableViewLoanBookDetail");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return tableView;
    }
    public void EventTableView(TableView<LoanBookDto> tableView) {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) return;
            seletec = newSelection;
            fillAdd();
        });
    }
    public void CreateLoad(){
        try {
            LocalDate date = null;
            if(selectedBook == null) throw new Exception("Error I did not select book 📖");
            if(selectedStudent == null) throw new Exception("Error I did not select Student 👨‍🎓👩‍🎓");
            if(selectedLibrarian == null) throw new Exception("Error I did not select Librarian 👷‍♂️👷‍♀️");

            Node node = findComponentWithUserDataFromNode(parent,"LoanDate");
            if(node instanceof DatePicker value){
                date =  value.getValue();
                if(date == null) throw new Exception("Date selection 📅");
            }else throw new Exception("Date selection 📅");

            node = findComponentWithUserDataFromNode(parent,"LoanComment");
            String valueComment = "";
            if(node instanceof TextField val) valueComment = val.getText().trim();

            LoanBook loanBook = new LoanBook(selectedStudent,java.sql.Date.valueOf(date),selectedLibrarian);
            boolean flag = false;
            if(loanBook.equals(loanBookFinal)) loanBook = loanBookFinal;

            if(loanBookFinal == null ) flag = true;

            BookLoanDetail bookLoanDetail = new BookLoanDetail(selectedBook,java.sql.Date.valueOf(date),valueComment,loanBook.getIdUUID());
            loanBook.addDetails(bookLoanDetail);
            viewBook.lendBook(selectedBook);

            if(flag) {
                LoanBook newLoanBook = service.Cretae(loanBook);
                ListLoanBookDto.add(newLoanBook.viewData());
            }

            node = findComponentWithUserDataFromNode(parent,"cancel");
            if(node instanceof Button btn) btn.fire();

        }catch (Exception e){
            System.err.println(e);
            Components.AlertLibrary(
                    Alert.AlertType.ERROR,
                    "Invalid Registration",
                    e.getMessage(),
                    "");
        }
    }
    public void UpdateLoad(){
        try {
            LocalDate date = null;
            if(selectedBook == null) throw new Exception("Error I did not select book 📖");
            if(selectedStudent == null) throw new Exception("Error I did not select Student 👨‍🎓👩‍🎓");
            if(selectedLibrarian == null) throw new Exception("Error I did not select Librarian 👷‍♂️👷‍♀️");
            if(loanBookFinal == null) throw new Exception("There is no detail 🔍️");
            if(bookLoanDetailFinal == null) throw new Exception("Error choosing row");

            Node node = findComponentWithUserDataFromNode(parent,"LoanDate");
            if(node instanceof DatePicker value){
                date =  value.getValue();
                if(date == null) throw new Exception("Date selection 📅");
            }else throw new Exception("Date selection 📅");

            node = findComponentWithUserDataFromNode(parent,"LoanComment");
            String valueComment = "";
            if(node instanceof TextField val) valueComment = val.getText().trim();

            loanBookFinal = service.findId(loanBookFinal.getId());
            loanBookFinal.setLoanLibrarian(selectedLibrarian);
            loanBookFinal.setLoanStudent(selectedStudent);

            Iterator<BookLoanDetail> iterator = loanBookFinal.Details().iterator();
            Book book = new Book();
            book.setTitle(selectDetail.bookTitle);
            book.setIsbn(selectDetail.isbn);
            BookLoanDetail detailFinal = null;

            while (iterator.hasNext()) {
                BookLoanDetail detail = iterator.next();
                if(detail.getDetailBook().equals(book)){
                    detailFinal = detail;
                    break;
                }
            }

            detailFinal.setDetailBook(selectedBook);

            ListBookLoanDetailDto.clear();
            var list = loanBookFinal.Details();
            for(BookLoanDetail detail:list) ListBookLoanDetailDto.add(detail.viewData());

        }catch (Exception e){
            System.err.println(e);
        }
    }

    public void deletedLoanBook(){
        try {
            if(loanBookFinal == null) throw new Exception("Error Deleted");
            var delated = service.Deleted(loanBookFinal);
            container_table.getChildren().clear();
            container_table.getChildren().add(TableViewLoanBook());
            selectedBook = null;
            selectedStudent = null;
            selectedLibrarian = null;
            loanBookFinal = null;
            bookLoanDetailFinal = null;

        }catch (Exception e){
            System.err.println(e);
            Components.AlertLibrary(
                    Alert.AlertType.ERROR,
                    "Invalid deleted",
                    e.getMessage(),
                    "");
        }
    }

    public void deletedBookLoanDetail(){
        try {
            if(selectDetail == null) throw new Exception("Error deletedBookLoanDetail");

            LoanBook loanBook = service.findId(selectDetail.id);
            if(loanBook == null) throw new Exception("Loan not found");


            Iterator<BookLoanDetail> iterator = loanBook.Details().iterator();
            Book book = new Book();
            book.setTitle(selectDetail.bookTitle);
            book.setIsbn(selectDetail.isbn);
            BookLoanDetail detailFinal = null;

            while (iterator.hasNext()) {
                BookLoanDetail detail = iterator.next();
                if(detail.getDetailBook().equals(book)){
                    iterator.remove();
                    break;
                }
            }

            container_table.getChildren().clear();
            container_table.getChildren().add(TableViewLoanBookDetail());
            selectedBook = null;
            selectedStudent = null;
            selectedLibrarian = null;
            loanBookFinal = null;
            bookLoanDetailFinal = null;

        }catch (Exception e){
            System.err.println(e);
            Components.AlertLibrary(
                    Alert.AlertType.ERROR,
                    "Invalid deleted",
                    e.getMessage(),
                    "");
        }
    }
    private List<LoanBookDto> LoanBookToDto(){
        List<LoanBookDto> view = new ArrayList<LoanBookDto>();
        Iterator<Map.Entry<String, LoanBook>> iterator = service.ToList().entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<String, LoanBook> entry = iterator.next();
            view.add(entry.getValue().viewData());
        }

        return view;
    }
    private List<BookLoanDetailDto> BookLoanDetailToDto(){
        List<BookLoanDetailDto> view = new ArrayList<BookLoanDetailDto>();
        LoanBook loanBook = null;
        if(seletec != null) loanBook = service.findId(seletec.getId());
        if(loanBook == null){
            Components.AlertLibrary(
                    Alert.AlertType.WARNING,
                    "Loan not selected",
                    "Select a loan",
                    "Click on the table");
        }else{
            container_table.getChildren().clear();
            TreeSet<BookLoanDetail> details = loanBook.Details();
            Iterator<BookLoanDetail> iterator = details.iterator();

            while (iterator.hasNext()) {
                BookLoanDetail detail = iterator.next();
                view.add(detail.viewData());
            }
            return view;
        }
        return view;
    }
    public void EventTableView(TableView<BookLoanDetailDto> tableView, String identifier) {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) return;
            selectDetail = newSelection;
            fillUpdate();
        });
    }
    private void fillUpdate(){
        try{
            if(selectDetail == null) throw new Exception("Error");

            LoanBook loanBook = service.findId(selectDetail.id);
            if(loanBook == null) throw new Exception("Loan not found");

            loanBookFinal = loanBook;


            Iterator<BookLoanDetail> iterator = loanBook.Details().iterator();
            Book book = new Book();
            book.setTitle(selectDetail.bookTitle);
            book.setIsbn(selectDetail.isbn);
            BookLoanDetail detailFinal = null;

            while (iterator.hasNext()) {
                BookLoanDetail detail = iterator.next();
                if(detail.getDetailBook().equals(book)){
                    selectedBook = detail.getDetailBook();
                    detailFinal = detail;
                    break;
                }
            }

            bookLoanDetailFinal = detailFinal;

            Node node = findComponentWithUserDataFromNode(parent,"labelBook");
            if(node instanceof Label value) value.setText(selectDetail.bookTitle);


            node = findComponentWithUserDataFromNode(parent,"labelStudent");
            if(node instanceof Label value){
                value.setText(loanBook.getLoanStudent().getFirstName() + " " + loanBook.getLoanStudent().getLastName());
                selectedStudent = loanBook.getLoanStudent();
            }

            node = findComponentWithUserDataFromNode(parent,"labelLibrarian");
            if(node instanceof Label value){
                value.setText(loanBook.getLoanLibrarian().getFirstName() + " " + loanBook.getLoanLibrarian().getLastName());
                selectedLibrarian = loanBook.getLoanLibrarian();
            }

            node = findComponentWithUserDataFromNode(parent,"LoanDate");
            if(node instanceof DatePicker value) value.setValue(selectDetail.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            node = findComponentWithUserDataFromNode(parent,"LoanComment");
            if(node instanceof TextField value) value.setText(selectDetail.comment);

        }catch (Exception e){
            System.err.println(e);
        }
    }
    private void fillAdd(){
        try{
            if(seletec == null) throw new Exception("Error");

            LoanBook loanBook = service.findId(seletec.id);
            if(loanBook == null) throw new Exception("Loan not found");

            loanBookFinal = loanBook;

            Node node = findComponentWithUserDataFromNode(parent,"labelStudent");
            if(node instanceof Label value){
                value.setText(loanBook.getLoanStudent().getFirstName() + " " + loanBook.getLoanStudent().getLastName());
                selectedStudent = loanBook.getLoanStudent();
            }

            node = findComponentWithUserDataFromNode(parent,"labelLibrarian");
            if(node instanceof Label value){
                value.setText(loanBook.getLoanLibrarian().getFirstName() + " " + loanBook.getLoanLibrarian().getLastName());
                selectedLibrarian = loanBook.getLoanLibrarian();
            }

        }catch (Exception e){
            System.err.println(e);
        }
    }
    public Node findComponentWithUserDataFromNode(Node startNode, String userData) {
        if (startNode.getUserData() != null && startNode.getUserData().equals(userData)) {
            return startNode;
        }

        if (startNode instanceof Parent) {
            Parent parent = (Parent) startNode;
            for (Node node : parent.getChildrenUnmodifiable()) {
                Node foundNode = findComponentWithUserDataFromNode(node, userData);
                if (foundNode != null) {
                    return foundNode;
                }
            }
        }

        return null;
    }

}
