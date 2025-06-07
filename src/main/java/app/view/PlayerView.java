package app.view;

import app.viewmodels.PlayerViewModel;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PlayerView extends HBox {
    public PlayerView(PlayerViewModel playerViewModel) {
        setSpacing(10);
        setAlignment(Pos.CENTER_LEFT);

        Label nameLabel = new Label();
        nameLabel.setFont(Font.font("Monospaced"));
        nameLabel.setText(playerViewModel.getName());
        nameLabel.textFillProperty().bind(
                Bindings.when(playerViewModel.getSelectedProperty())
                        .then(Color.YELLOW)
                        .otherwise(Color.WHITE)
        );

        Label scoreLabel = new Label();
        scoreLabel.setFont(Font.font("Monospaced"));
        scoreLabel.setTextFill(Color.LIGHTGRAY);
        scoreLabel.textProperty().bind(
                Bindings.concat("[Score: ", playerViewModel.getScoreProperty().asString(), "]")
        );

        getChildren().addAll(nameLabel, scoreLabel);
    }
}