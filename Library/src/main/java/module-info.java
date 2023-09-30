module edu.est.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens  edu.est.library.infrastructure.javafx to javafx.fmxl;
    exports edu.est.library.infrastructure.javafx;

    opens edu.est.library.infrastructure.javafx.controllers to javafx.fmxl;
    exports edu.est.library.infrastructure.javafx.controllers;

    opens  edu.est.library.domain.models to javafx.fxml;
    exports edu.est.library.domain.models;

    opens  edu.est.library.domain.dto to javafx.fxml;
    exports edu.est.library.domain.dto;
}