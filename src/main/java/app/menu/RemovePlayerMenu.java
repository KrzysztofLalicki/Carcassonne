package app.menu;

import app.model.Player;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;



public class RemovePlayerMenu extends StackPane {
    public RemovePlayerMenu(String text, ViewModelMenu viewModelMenu) {
        Label label = new Label("Carcassonne");
        label.setFont(new Font("Comic Sans MS", 60));
        label.setTextFill(Color.GOLD);
        label.setTranslateY(-250);
        Label gameLabel = new Label("Nowa Gra");
        gameLabel.setTranslateY(-170);
        gameLabel.setTextFill(Color.WHITE);
        gameLabel.setFont(new Font("Comic Sans MS",42));

        Button addPlayer = new CreateStyledButton("Dodaj gracza");
        addPlayer.setTranslateY(-20);

        addPlayer.setOnAction(e -> {
            viewModelMenu.AddPlayerFromMainMenu();
        });

        Button endRemoving = new CreateStyledButton("Zakończ");
        endRemoving.setTranslateY(30);
        endRemoving.setOnAction(e -> {
            viewModelMenu.BackToMainMenu();
        });


        this.getChildren().addAll(addPlayer, endRemoving, label, gameLabel);
        if(!text.isEmpty())
        {
            Label WarningLabel = new Label(text);
            WarningLabel.setTranslateY(-120);
            WarningLabel.setTextFill(Color.RED);
            WarningLabel.setFont(new Font("Comic Sans MS",20));
            FadeTransition fade = new FadeTransition(Duration.seconds(3), WarningLabel);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished(e -> WarningLabel.setVisible(false));
            fade.play();
            this.getChildren().add(WarningLabel);
        }
        if(!viewModelMenu.getPlayers().isEmpty())
        {
            Label playerLabel = new Label("Gracze:");
            playerLabel.setTextFill(Color.WHITE);
            playerLabel.setFont(new Font("Comic Sans MS", 20));
            playerLabel.setTranslateY(-50);
            playerLabel.setTranslateX(-230);
            this.getChildren().add(playerLabel);
            int level = -10;
            for(Player player : viewModelMenu.getPlayers())
            {
                Button playernameButton = new Button(player.getName());
                playernameButton.setMaxSize(120,25);
                playernameButton.setTranslateY(level);
                playernameButton.setTranslateX(-230);
                playernameButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));
                playernameButton.setStyle("-fx-background-color: yellow; -fx-text-fill: purple;");
                Rectangle prostokat = new Rectangle(17, 34);
                prostokat.setFill(player.getColor());
                prostokat.setStroke(Color.BLACK);
                prostokat.setStrokeWidth(2);
                prostokat.setTranslateY(level);
                prostokat.setTranslateX(-165);
                level += 40;
                playernameButton.setOnAction(e->{
                    viewModelMenu.RemovePlayer(player);
                });
                this.getChildren().add(playernameButton);
                this.getChildren().add(prostokat);
            }
        }
        this.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
