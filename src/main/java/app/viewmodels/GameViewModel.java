package app.viewmodels;

import app.model.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyEvent;
import java.util.function.Consumer;

import static javafx.scene.input.KeyCode.ESCAPE;

public class GameViewModel implements GameActionListener, GameStateChangeListener {
    public static final String PLACE_TILE_CONTROLS_TEXT = "Place a tile. CONTROLS: Arrow keys to navigate, R to rotate, Space to place a tile.";
    public static final String AI_PLACE_TILE = "AI is placing a tile. Wait for your move.";
    public static final String PLACE_FOLLOWER_CONTROLS_TEXT = "Place a follower. CONTROLS: Arrow keys to navigate, S to skip, Space to place a follower.";
    public static final String AI_PLACE_FOLLOWER = "AI is placing a follower. Wait for your move.";
    public static final String GAME_END_TEXT = "The game has ended. Press ESC to exit.";

    private final Game game;

    private final SideBarViewModel sideBarViewModel;
    private final BoardViewModel boardViewModel;

    private final StringProperty bottomText = new SimpleStringProperty(PLACE_TILE_CONTROLS_TEXT);

    public GameViewModel(Game game) {
        this.game = game;
        game.addGameActionListener(this);
        game.addGameStateChangeListener(this);
        boardViewModel = new BoardViewModel(game);
        sideBarViewModel = new SideBarViewModel(game, boardViewModel.getBoardSelectorViewModel());
    }

    public SideBarViewModel getSideBarViewModel() {return sideBarViewModel;}
    public BoardViewModel getBoardViewModel() {return boardViewModel;}

    public StringProperty getBottomTextProperty() { return bottomText; }

    public void escape() {
        //TODO: getBackToMenu
        Platform.exit();
    }

    private final Consumer<KeyEvent> handleKeyEvent = new Consumer<KeyEvent>() {
        @Override
        public void accept(KeyEvent event) {
            if (event.getCode() == ESCAPE)
                escape();
        }
    };

    @Override
    public void placeTile(Tile tile) {
        if(game.getCurrentPlayer() instanceof HumanPlayer)
            bottomText.set(PLACE_TILE_CONTROLS_TEXT);
        else
            bottomText.set(AI_PLACE_TILE);

        System.out.printf("\t%s placing tile\n", game.getCurrentPlayer().getName() );
        if(game.getCurrentPlayer() instanceof HumanPlayer)
            boardViewModel.placeTile(tile);
        else if(game.getCurrentPlayer() instanceof AiPlayer)
            boardViewModel.placeAiTile(tile, ((AiPlayer) game.getCurrentPlayer()).getTilePlacement(tile));
    }

    @Override
    public void placeFollower(Tile tile, Follower follower) {
        if(game.getCurrentPlayer() instanceof HumanPlayer)
            bottomText.set(PLACE_FOLLOWER_CONTROLS_TEXT);
        else
            bottomText.set(AI_PLACE_FOLLOWER);

        System.out.printf("\t%s placing follower\n", game.getCurrentPlayer().getName() );
        if(game.getCurrentPlayer() instanceof HumanPlayer)
            boardViewModel.placeFollower(tile, follower);
        else if(game.getCurrentPlayer() instanceof AiPlayer)
            boardViewModel.placeAiFollower(tile, follower, ((AiPlayer) game.getCurrentPlayer()).getFollowerPlacement(tile, follower));
    }

    @Override
    public void onGameEnd() {
        bottomText.set(GAME_END_TEXT);
        KeyboardManager.getInstance().register(handleKeyEvent);
    }
}
