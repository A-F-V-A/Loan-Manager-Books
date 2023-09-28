package edu.est.library.infrastructure.javafx.components;

import edu.est.library.application.services.StudentService;
import javafx.scene.layout.HBox;

public class ViewStudent {
    private final StudentService service;
    public ViewStudent (StudentService service){
        this.service = service;
    }
    public static HBox containerInputs(){
        HBox container = new HBox();

        container.getChildren().add(Components.Input("FirstName"));
        container.getChildren().add(Components.Input("LastName"));
        container.getChildren().add(Components.Input("StudentId"));

        return container;
    }
}
