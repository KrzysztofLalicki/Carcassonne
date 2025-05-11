package app.menu;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PostGameMenu extends StackPane {
    public PostGameMenu() {
        Label postGameLabel = new Label("Tu kiedyś będą wyniki.");
        postGameLabel.setFont(new Font("Comic Sans MS", 50));
        postGameLabel.setStyle("-fx-text-fill: white;");
        postGameLabel.setTranslateY(0);
        postGameLabel.setTranslateX(0);
        this.getChildren().add(postGameLabel);
        this.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
