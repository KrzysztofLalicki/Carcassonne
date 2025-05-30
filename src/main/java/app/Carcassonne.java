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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ViewModelMenu viewModelMenu = new ViewModelMenu(new Game(), primaryStage);
    }

}