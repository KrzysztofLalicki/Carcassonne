package app.menu;


import app.model.Player;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;



public class MainMenu extends StackPane {
    public MainMenu(String text,ViewModelMenu viewModelMenu) {
        Label label = new Label("Carcassonne");
        label.setFont(new Font("Comic Sans MS", 60));
        label.setTextFill(Color.GOLD);
        label.setTranslateY(-250);
        Label gameLabel = new Label("Nowa Gra");
        gameLabel.setTranslateY(-170);
        gameLabel.setTextFill(Color.WHITE);
        gameLabel.setFont(new Font("Comic Sans MS", 42));

        Button addPlayer = new CreateStyledButton("Dodaj gracza");
        addPlayer.setTranslateY(-20);
        addPlayer.setOnAction(e -> {
            viewModelMenu.AddPlayerFromMainMenu();
        });

        Button startGame = new CreateStyledButton("Zacznij gre");
        startGame.setTranslateY(-70);
        startGame.setOnAction(e -> {
            viewModelMenu.StarGame();
        });

        Button removePlayer = new CreateStyledButton("Usun gracza");
        removePlayer.setTranslateY(30);
        removePlayer.setOnAction(e -> {
            viewModelMenu.RemovePlayerFromMainMenu();
        });

        Button exit = new CreateStyledButton("Cofnij");
        exit.setTranslateY(80);
        exit.setOnAction(e -> {
            viewModelMenu.BackToOpenMenu();
        });


        this.getChildren().addAll(gameLabel, addPlayer, startGame, removePlayer, label, exit);
        if (!text.isEmpty()) {
            Label warningLabel = new Label(text);
            warningLabel.setFont(Font.font("Comic Sans MS", 20));
            warningLabel.setTextFill(Color.RED);
            warningLabel.setTranslateY(-120);
            FadeTransition fade = new FadeTransition(Duration.seconds(3), warningLabel);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished(e -> warningLabel.setVisible(false));
            fade.play();
            this.getChildren().add(warningLabel);
        }
        if (!viewModelMenu.getPlayers().isEmpty()) {
            Label playerLabel = new Label("Gracze:");
            playerLabel.setTextFill(Color.WHITE);
            playerLabel.setFont(new Font("Comic Sans MS", 20));
            playerLabel.setTranslateY(-50);
            playerLabel.setTranslateX(-230);
            this.getChildren().add(playerLabel);
            int level = -10;
            for (Player player : viewModelMenu.getPlayers()) {
                Button playernameButton = new Button(player.getName());
                playernameButton.setMaxSize(120, 25);
                playernameButton.setTranslateY(level);
                playernameButton.setTranslateX(-230);
                playernameButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));
                playernameButton.setStyle("-fx-background-color: white; -fx-text-fill: purple;");
                Rectangle prostokat = new Rectangle(17, 34);
                prostokat.setFill(player.getColor());
                prostokat.setStroke(Color.BLACK);
                prostokat.setStrokeWidth(2);
                prostokat.setTranslateY(level);
                prostokat.setTranslateX(-165);
                level += 40;
                this.getChildren().add(playernameButton);
                this.getChildren().add(prostokat);
            }


        }
        this.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    }



}