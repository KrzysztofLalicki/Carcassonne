package app;

import app.controller.GameController;
import app.model.Game;
import app.model.Player;
import app.view.GameView;
import app.view.SideBarView;
import app.viewmodels.GameViewModel;
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


        game.addPlayer(new Player("dupa"));
        game.addPlayer(new Player("cipa"));

        GameViewModel gameViewModel = new GameViewModel(game);
        GameView gameView = new GameView(new SideBarView(game.getPlayers()), gameViewModel, game.getTable());
        GameController gameController = new GameController(game, gameView);


        Scene scene = new Scene(gameView);
        scene.setOnKeyPressed(evt -> gameController.handleKeyPress(evt));

        primaryStage.setScene(scene);

//        primaryStage.setScene(new Scene(vbox,800,600));
        primaryStage.show();
    }

}