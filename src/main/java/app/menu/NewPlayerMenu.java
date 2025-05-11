package app.menu;

import app.model.Player;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import static app.Carcassonne.game;
import static app.Carcassonne.primaryStage;

public class NewPlayerMenu extends StackPane {
    public NewPlayerMenu(String text)
    {
        Label label = new Label("Carcassonne");
        label.setFont(new Font("Comic Sans MS", 60));
        label.setTextFill(Color.GOLD);
        label.setTranslateY(-250);
        Label gameLabel = new Label("Nowa Gra");
        gameLabel.setTranslateY(-170);
        gameLabel.setTextFill(Color.WHITE);
        gameLabel.setFont(new Font("Comic Sans MS",42));
        this.getChildren().add(label);
        this.getChildren().add(gameLabel);

        TextField nameField = new TextField();
        nameField.setMaxHeight(40);
        nameField.setMaxWidth(150);
        nameField.setTranslateY(-70);
        nameField.setPromptText("Podaj nazwe");
        nameField.setStyle("-fx-text-fill: purple;");

        nameField.setAlignment(Pos.CENTER);
        nameField.setFont(new Font("Comic Sans MS",18));

        Button addPlayer = new CreateStyledButton("Dodaj gracza");
        addPlayer.setTranslateY(-20);
        addPlayer.setOnAction(e -> {
            String stringPlayerName = nameField.getText();
            if(stringPlayerName.isEmpty()) {
                StackPane newPlayerthis = new NewPlayerMenu("Nieprwidłowa nazwa gracza");
                primaryStage.getScene().setRoot(newPlayerthis);
            }
            else if(stringPlayerName.length()>10)
            {
                StackPane newPlayerthis = new NewPlayerMenu("Zadługa nazwa");
                primaryStage.getScene().setRoot(newPlayerthis);
            }
            else if(PlayersContains(stringPlayerName)){
                StackPane newPlayerthis = new NewPlayerMenu("Nazwa zajęta");
                primaryStage.getScene().setRoot(newPlayerthis);
            }
            else{
                game.getPlayers().add(new Player(stringPlayerName));
                StackPane menu = new MainMenu("");
                primaryStage.getScene().setRoot(menu);
            }
        });

        if(!game.getPlayers().isEmpty())
        {
            Label playerLabel = new Label("Gracze:");
            playerLabel.setTextFill(Color.WHITE);
            playerLabel.setFont(new Font("Comic Sans MS", 20));
            playerLabel.setTranslateY(-50);
            playerLabel.setTranslateX(-230);
            this.getChildren().add(playerLabel);
            int level = -10;
            for(Player player : game.getPlayers())
            {
                Button playernameButton = new Button(player.getName());
                playernameButton.setMaxSize(120,25);
                playernameButton.setTranslateY(level);
                playernameButton.setTranslateX(-230);
                playernameButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));
                playernameButton.setStyle("-fx-background-color: white; -fx-text-fill: purple;");
                level += 40;
                this.getChildren().add(playernameButton);
            }


        }

        Button returnToMenu = new CreateStyledButton("Anuluj");
        returnToMenu.setTranslateY(30);
        returnToMenu.setOnAction(e -> {
            StackPane menuthis = new MainMenu("");
            primaryStage.getScene().setRoot(menuthis);
        });
        this.getChildren().add(returnToMenu);
        if(!text.isEmpty()){
            Label incorrectPlayerName = new Label(text);
            FadeTransition fade = new FadeTransition(Duration.seconds(3), incorrectPlayerName);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished(e -> incorrectPlayerName.setVisible(false));
            fade.play();
            incorrectPlayerName.setTextFill(Color.RED);
            incorrectPlayerName.setFont(new Font("Comic Sans MS",20));
            incorrectPlayerName.setTranslateY(-120);
            this.getChildren().addAll(incorrectPlayerName);
        }
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(nameField);
        this.getChildren().add(addPlayer);
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private boolean PlayersContains(String playerName)
    {
        for(Player player : game.getPlayers())
        {
            if(player.getName().equals(playerName))
                return true;
        }
        return false;
    }
}
