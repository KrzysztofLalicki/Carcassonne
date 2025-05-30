package app.menu;

import app.model.Player;
import app.view.GameView;
import app.view.SideBarView;
import app.viewmodels.GameViewModel;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import static app.Carcassonne.game;
import static app.Carcassonne.primaryStage;


public class MainMenu extends StackPane {
    public MainMenu(String text) {
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
            if (game.getPlayers().size() < 6) {
                StackPane newPlayerLayout = new NewPlayerMenu("");
                primaryStage.getScene().setRoot(newPlayerLayout);
            } else {
                StackPane menuLayout = new MainMenu("Maksymalna liczba graczy");
                primaryStage.getScene().setRoot(menuLayout);
            }
        });

        Button startGame = new CreateStyledButton("Zacznij gre");
        startGame.setTranslateY(-70);
        startGame.setOnAction(e -> {
            if (game.getPlayers().size() < 2) {
                StackPane menuLayout = new MainMenu("Za mała ilość graczy");
                primaryStage.getScene().setRoot(menuLayout);
            } else {  // Zaczynamy rozgrywke
                primaryStage.setResizable(false);
//                GameController gameController = new GameController(game);
//                GameView gameView = gameController.getGameView();

                GameViewModel gameViewModel = new GameViewModel(game);
                GameView gameView = new GameView(new SideBarView(game.getPlayers()), gameViewModel);
//                GameController gameController = new GameController(game, gameView);


                Scene scene = new Scene(gameView);
//                scene.setOnKeyPressed(evt -> gameController.handleKeyPress(evt));
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });

        Button removePlayer = new CreateStyledButton("Usun gracza");
        removePlayer.setTranslateY(30);
        removePlayer.setOnAction(e -> {
            if (game.getPlayers().isEmpty()) {
                StackPane menuLayout = new MainMenu("Gościu co ty chcesz usuwać!");
                primaryStage.getScene().setRoot(menuLayout);
            } else {
                StackPane removePlayerMenu = new RemovePlayerMenu("");
                primaryStage.getScene().setRoot(removePlayerMenu);
            }
        });

        Button exit = new CreateStyledButton("Cofnij");
        exit.setTranslateY(80);
        exit.setOnAction(e -> {
            VBox vbox = new OpenMenu();
            primaryStage.getScene().setRoot(vbox);
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
        if (!game.getPlayers().isEmpty()) {
            Label playerLabel = new Label("Gracze:");
            playerLabel.setTextFill(Color.WHITE);
            playerLabel.setFont(new Font("Comic Sans MS", 20));
            playerLabel.setTranslateY(-50);
            playerLabel.setTranslateX(-230);
            this.getChildren().add(playerLabel);
            int level = -10;
            for (Player player : game.getPlayers()) {
                Button playernameButton = new Button(player.getName());
                playernameButton.setMaxSize(120, 25);
                playernameButton.setTranslateY(level);
                playernameButton.setTranslateX(-230);
                playernameButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));
                playernameButton.setStyle("-fx-background-color: white; -fx-text-fill: purple;");
                level += 40;
                this.getChildren().add(playernameButton);
            }


        }
        this.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    }



}