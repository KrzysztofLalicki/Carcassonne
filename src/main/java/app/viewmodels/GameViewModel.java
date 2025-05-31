package app.viewmodels;

import app.model.Game;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameViewModel {
    public static final String CONTROLS_TEXT = "CONTROLS: Arrow keys to navigate, R to rotate, Space to place a tile.";
    public static final String GAME_END_TEXT = "The game has ended. Press ESC to exit.";

    private final Game game;

    private final SideBarViewModel sideBarViewModel;
    private final BoardViewModel boardViewModel;

    private final StringProperty bottomText = new SimpleStringProperty(CONTROLS_TEXT);

    public GameViewModel(Game game) {
        this.game = game;
        sideBarViewModel = new SideBarViewModel(game);
        boardViewModel = new BoardViewModel(game);
        getHasEndedProperty().addListener((_, _, newVal) -> {
            if(newVal)
                bottomText.set(GAME_END_TEXT);
        });
    }

    public SideBarViewModel getSideBarViewModel() {return sideBarViewModel;}
    public BoardViewModel getBoardViewModel() {return boardViewModel;}

    public StringProperty getBottomTextProperty() { return bottomText; }

    public BooleanProperty getHasEndedProperty() {
        return game.getHasEndedProperty();
    }

    public void escape() {
        //TODO: getBackToMenu
        Platform.exit();
    }
}
