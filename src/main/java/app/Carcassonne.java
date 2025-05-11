package app;

import app.controller.GameController;
import app.model.Game;
import app.view.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Carcassonne extends Application {
    private Game game;
    private Stage primaryStage;

    public Carcassonne(Game game, Stage primaryStage) {
        this.game = game;
        this.primaryStage = primaryStage;
        primaryStage.setResizable(false);
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameController gameController = new GameController(game);
        GameView gameView = gameController.getGameView();
        Scene scene = new Scene(gameView);
        scene.setOnKeyPressed(gameController::handleKeyPress);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}