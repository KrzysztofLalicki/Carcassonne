package app;

import app.controller.GameController;
import app.model.Game;
import app.model.Player;
import app.view.GameView;
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

        game.addPlayer(new Player("Kuba"));
        game.addPlayer(new Player("Oskar"));
        game.addPlayer(new Player("Krzysztof"));

        GameController gameController = new GameController(game);
        GameView gameView = gameController.getGameView();
        Scene scene = new Scene(gameView);
        scene.setOnKeyPressed(gameController::handleKeyPress);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}