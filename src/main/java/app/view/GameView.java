package app.view;

import app.viewmodels.GameViewModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;


public class GameView extends BorderPane {
    public GameView(GameViewModel gameViewModel) {
        SideBarView sideBarView = new SideBarView(gameViewModel.getSideBarViewModel());
        sideBarView.setPadding(new Insets(10));
        setLeft(sideBarView);

        setCenter(new BoardView(gameViewModel.getBoardViewModel()));

        Label bottomLabel = new Label();
        bottomLabel.setFont(Font.font("Monospaced", 16));
        bottomLabel.textProperty().bind(gameViewModel.getBottomTextProperty());
        setBottom(bottomLabel);
        setAlignment(bottomLabel, Pos.CENTER);
    }
}