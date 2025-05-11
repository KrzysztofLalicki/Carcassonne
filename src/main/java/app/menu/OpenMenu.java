package app.menu;

import app.Carcassonne;
import app.model.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static app.Carcassonne.primaryStage;
import static app.Carcassonne.game;

public class OpenMenu extends VBox {
    public OpenMenu() {
        game = new Game();
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        Label label = new Label("Carcassonne");
        label.setFont(new Font("Comic Sans MS", 60));
        label.setTextFill(Color.GOLD);
        Button newGameButton = new CreateStyledButton("Nowa gra");
        newGameButton.setOnAction(e -> {
            StackPane menuLayout = new MainMenu("");
            primaryStage.getScene().setRoot(menuLayout);
        });

        Button exit = new CreateStyledButton("Opuść gre");
        exit.setOnAction(e -> {
            System.exit(0);
        });
        this.getChildren().addAll(label, newGameButton);
        this.setSpacing(10);
        this.getChildren().add(exit);
        primaryStage.setTitle("Carcassonne");
    }
}
