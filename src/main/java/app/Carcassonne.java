package app;

import app.controller.GameController;
import app.model.Game;
import app.view.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import app.menu.*;

public class Carcassonne extends Application {
    public static Game game;
    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        VBox vbox = new OpenMenu();
        primaryStage.setScene(new Scene(vbox,800,600));
        primaryStage.show();
    }

}