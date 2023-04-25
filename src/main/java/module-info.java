module dtu.project.app {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens dtu.project.app.GUI to javafx.fxml;
    exports dtu.project.app.GUI;
}