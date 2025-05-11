package app.menu;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CreateStyledButton extends Button {
    public CreateStyledButton(String text) {
        super(text);
        this.setPrefSize(150, 40);
        this.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,18));
        this.setTextFill(Color.PURPLE);
        this.setStyle("-fx-background-color: white; -fx-text-fill: purple");
    }
}
