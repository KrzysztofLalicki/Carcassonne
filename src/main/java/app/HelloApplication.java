package app;

import app.controller.BoardController;
import app.view.BoardView;
import app.view.TileView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import static java.lang.Thread.sleep;


public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Table table = new Table();
        BoardView boardView = new BoardView(table);
        BoardController boardController = new BoardController(boardView);

        Scene scene = new Scene(boardView);
        scene.setOnKeyPressed(boardController::handleKeyPress);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}