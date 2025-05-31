package app;

import app.model.Game;
import javafx.application.Application;
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