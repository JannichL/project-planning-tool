module dtu.project.app {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;

    opens dtu.project.app.GUI to javafx.fxml;
    exports dtu.project.app.GUI;
    exports dtu.project.app.application;
}