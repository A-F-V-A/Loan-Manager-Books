package edu.est.library.infrastructure.javafx.components;

import edu.est.library.domain.models.Book;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class Components {
    public static VBox Input(String name){
        VBox container = new VBox();
        container.getStyleClass().add("custom-vbox");

        Label title = new Label(name);
        title.getStyleClass().add("custom-label");

        TextField input = new TextField();
        input.getStyleClass().add("custom-text-field");
        input.setUserData(name);

        container.getChildren().addAll(title, input);

        return container;
    }
    public static VBox InputDatePicker(String name, LocalDate date) {
        VBox container = new VBox();
        container.getStyleClass().add("custom-vbox");

        Label title = new Label(name);
        title.getStyleClass().add("custom-label");

        DatePicker datePicker = new DatePicker(date);
        datePicker.getStyleClass().add("custom-date-picker");
        datePicker.setUserData(name);

        container.getChildren().addAll(title, datePicker);

        return container;
    }

    public static VBox containerButton(String name, EventHandler<ActionEvent> btnActionHandler) {
        VBox container = new VBox();
        container.getStyleClass().add("custom-vbox");

        Label title = new Label("No selection");
        title.getStyleClass().add("custom-label-selection");
        title.setUserData("label" + name);

        Button btn = new Button(name);
        btn.getStyleClass().add("btn-select");
        btn.setUserData(name);
        btn.setOnAction(btnActionHandler);

        container.getChildren().addAll(title, btn);

        return container;
    }
    public static void AlertLibrary(Alert.AlertType type, String title, String message, String recommendation){
        Alert alter = new Alert(type);
        alter.setTitle(title);
        alter.setHeaderText(message);
        alter.setContentText(recommendation);
        alter.showAndWait();
    }

}
