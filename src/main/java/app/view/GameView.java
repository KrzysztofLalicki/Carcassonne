package app.view;

import app.model.Table;
import app.viewmodels.GameViewModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;


public class GameView extends BorderPane {
    public static final String CONTROLS_TEXT = "CONTROLS: Arrow keys to navigate, R to rotate, Space to place a tile.";
    public static final String GAME_END_TEXT = "The game has ended. Press ESC to exit.";

    private final Label bottomLabel;

    private BoardView boardView;
    private SideBarView sideBarView;

    //TODO: remove table from here!!!
    public GameView(SideBarView sideBarView, GameViewModel gameViewModel, Table table) {
        boardView = new BoardView(table, gameViewModel.getBoardViewModel());

        sideBarView.setPadding(new Insets(10));
        setLeft(sideBarView);
        setRight(boardView);

        bottomLabel = new Label();
        bottomLabel.setFont(Font.font("Monospaced"));
        setBottom(bottomLabel);
        setAlignment(bottomLabel, Pos.CENTER);
    }

    public void setBottomText(String bottomText) {
        bottomLabel.setText(bottomText);
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public SideBarView getSideBarView() {
        return sideBarView;
    }
}