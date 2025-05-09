module app.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.compiler;

    opens app to javafx.fxml;
    exports app;
    exports app.model;
    opens app.model to javafx.fxml;
}