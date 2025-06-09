package app.view;

import app.viewmodels.PlayerViewModel;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static app.view.TileView.TILE_SIZE;

public class PlayerView extends VBox {
    public PlayerView(PlayerViewModel playerViewModel) {
        setSpacing(5);

        Circle circle = new Circle(6);
        circle.setFill(playerViewModel.getColor());

        Label nameLabel = new Label();
        nameLabel.setFont(Font.font("Monospaced", 14));
        nameLabel.setText(playerViewModel.getName());
        nameLabel.textFillProperty().bind(
                Bindings.when(playerViewModel.getSelectedProperty())
                        .then(Color.RED)
                        .otherwise(Color.WHITE)
        );
        nameLabel.fontProperty().bind(
                Bindings.when(playerViewModel.getSelectedProperty())
                        .then(Font.font(nameLabel.getFont().getFamily(), FontWeight.BOLD, nameLabel.getFont().getSize()))
                        .otherwise(Font.font(nameLabel.getFont().getFamily(), FontWeight.NORMAL, nameLabel.getFont().getSize()))
        );

        Label scoreLabel = new Label();
        scoreLabel.setFont(Font.font("Monospaced", 12));
        scoreLabel.setTextFill(Color.LIGHTGRAY);
        scoreLabel.textProperty().bind(
                Bindings.concat("[Score: ", playerViewModel.getScoreProperty().asString(), "]")
        );

        Label followersLabel = new Label();
        followersLabel.setFont(Font.font("Monospaced", 12));
        followersLabel.setTextFill(Color.LIGHTGRAY);
        followersLabel.textProperty().bind(
                Bindings.concat("[Unused followers: ", playerViewModel.getFollowersProperty().asString(), "]")
        );

        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().addAll(circle, nameLabel);
        getChildren().addAll(hBox, scoreLabel, followersLabel);
    }
}