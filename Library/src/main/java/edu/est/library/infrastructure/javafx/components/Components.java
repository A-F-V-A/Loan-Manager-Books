package edu.est.library.infrastructure.javafx.components;

import edu.est.library.domain.models.Book;
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
    public static void AlertLibrary(Alert.AlertType type, String title, String message, String recommendation){
        Alert alter = new Alert(type);
        alter.setTitle(title);
        alter.setHeaderText(message);
        alter.setContentText(recommendation);
        alter.showAndWait();
    }

}
