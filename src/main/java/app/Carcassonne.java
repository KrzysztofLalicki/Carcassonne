package app;

import app.controller.BoardController;
import app.model.Game;
import app.view.BoardView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Carcassonne extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();
        BoardController boardController = new BoardController(game);
        BoardView boardView = boardController.getBoardView();
        Scene scene = new Scene(boardView);
        scene.setOnKeyPressed(boardController::handleKeyPress);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}