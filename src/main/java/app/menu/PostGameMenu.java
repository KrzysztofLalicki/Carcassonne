package app.menu;

import app.model.Game;
import app.model.Player;
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

import java.util.Comparator;

public class PostGameMenu extends StackPane {
    public PostGameMenu(ViewModelMenu viewModelMenu) {
        Label label = new Label("Carcassonne");
        label.setFont(new Font("Comic Sans MS", 60));
        label.setTextFill(Color.GOLD);
        label.setTranslateY(-200);
        Label wynik = new Label("Wyniki");
        wynik.setFont(new Font("Comic Sans MS", 30));
        wynik.setTextFill(Color.WHITE);
        wynik.setTranslateY(-120);
        this.getChildren().addAll(label, wynik);
        int level = -60;
        viewModelMenu.getPlayers().sort(Comparator.comparingInt(Player::getPoints).reversed());
        for (Player player : viewModelMenu.getPlayers()) {
            Button playernameButton = new Button(player.getName());
            playernameButton.setMaxSize(200, 25);
            playernameButton.setTranslateY(level);
            playernameButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));
            playernameButton.setStyle("-fx-background-color: white; -fx-text-fill: purple;");
            Rectangle prostokat = new Rectangle(34, 34);
            prostokat.setFill(player.getColor());
            prostokat.setStroke(Color.BLACK);
            prostokat.setStrokeWidth(2);
            prostokat.setTranslateY(level);
            prostokat.setTranslateX(-100);
            Label number = new Label(String.valueOf( player.getPoints()));
            number.setFont(new Font("Comic Sans MS", 30));
            number.setTextFill(Color.GOLD);
            number.setTranslateY(level);
            number.setTranslateX(80);
            level += 40;
            this.getChildren().add(playernameButton);
            this.getChildren().add(prostokat);
            this.getChildren().add(number);
        }
        Button end = new CreateStyledButton("Wyjdź");
        end.setTranslateY(level + 30);
        end.setOnAction(e -> {
            viewModelMenu.PostGameMenugotoMain();
        });
        this.getChildren().add(end);
        this.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
