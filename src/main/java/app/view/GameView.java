package app.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;


public class GameView extends BorderPane {
    public static final String CONTROLS_TEXT = "CONTROLS: Arrow keys to navigate, R to rotate, Space to place a tile.";
    public static final String GAME_END_TEXT = "The game has ended. Press ESC to exit.";

    private final Label bottomLabel;

    public GameView(SideBarView sideBarView, BoardView gameView) {
        sideBarView.setPadding(new Insets(10));
        setLeft(sideBarView);
        setRight(gameView);

        bottomLabel = new Label();
        bottomLabel.setFont(Font.font("Monospaced"));
        setBottom(bottomLabel);
        setAlignment(bottomLabel, Pos.CENTER);
    }

    public void setBottomText(String bottomText) {
        bottomLabel.setText(bottomText);
    }
}