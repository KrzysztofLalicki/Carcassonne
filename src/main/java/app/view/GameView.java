package app.view;

import app.viewmodels.GameViewModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;


public class GameView extends BorderPane {
    public GameView(GameViewModel gameViewModel) {
        SideBarView sideBarView = new SideBarView(gameViewModel.getSideBarViewModel());
        sideBarView.setPadding(new Insets(10));
        setLeft(sideBarView);

        setRight(new BoardView(gameViewModel.getBoardViewModel()));

        Label bottomLabel = new Label();
        bottomLabel.setFont(Font.font("Monospaced"));
        bottomLabel.textProperty().bind(gameViewModel.getBottomTextProperty());
        setBottom(bottomLabel);
        setAlignment(bottomLabel, Pos.CENTER);

        gameViewModel.getHasEndedProperty().addListener((_, _, newValue) -> {
            if(newValue) {
                setOnKeyPressed(event -> {
                    if(event.getCode() == KeyCode.ESCAPE)
                        gameViewModel.escape();
                });
            }
        });
    }
}