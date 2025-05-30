package app.controller;

import app.model.*;
import app.utils.Position;
import app.view.BoardView;
import app.view.GameView;
import app.view.SideBarView;
import app.view.TileView;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class GameController {
    private final Game game;
    private final Box box;
    private final Table table;
    Tile nextTile;

    private final GameView gameView;
    private final SideBarView sideBarView;
    private final BoardView boardView;
    private Position selected;

    public GameController(Game game, GameView gameView) {
        this.game = game;
        box = game.getBox();
        table = game.getTable();

        this.gameView = gameView;

        this.sideBarView = gameView.getSideBarView();
        this.boardView = gameView.getBoardView();

        //this.gameView = new GameView(sideBarView, boardView);

        gameView.setBottomText(GameView.CONTROLS_TEXT);

        selected = new Position(Table.STARTING_TILE_POSITION, Table.STARTING_TILE_POSITION);
        boardView.setOnTablePosition(selected);
        nextTile = game.getBox().giveTile();

        sideBarView.setNextTile(nextTile);
//        sideBarView.setNextTileOutline(TileView.Outline.RED);
        sideBarView.selectPlayer(game.getCurrentPlayer());
    }

//    public GameView getGameView() {
//        return gameView;
//    }

    private boolean gameEnded = false;
    public void handleKeyPress(KeyEvent event) {
        int x = selected.x();
        int y = selected.y();

        if (event.getCode() == KeyCode.UP) y--;
        else if(event.getCode() == KeyCode.DOWN) y++;
        else if(event.getCode() == KeyCode.LEFT) x--;
        else if(event.getCode() == KeyCode.RIGHT) x++;
        else if(event.getCode() == KeyCode.R)
            nextTile.rotate();
        else if(event.getCode() == KeyCode.SPACE && table.canPlace(nextTile, selected.x(), selected.y())) {
            if(!gameEnded)
                table.placeTile(nextTile, selected.x(), selected.y());

            if(!box.isEmpty()) {
                nextTile = box.giveTile();
                sideBarView.selectPlayer(game.getCurrentPlayer());
            }
            else {
                gameEnded = true;
                nextTile = new Tile(new short[6], null);
                gameView.setBottomText(GameView.GAME_END_TEXT);
            }
            sideBarView.setNextTile(nextTile);
        }
        else if(gameEnded && event.getCode() == KeyCode.ESCAPE) {
            /*StackPane postMenu = new PostGameMenu();
            primaryStage.getScene().setRoot(postMenu);
            primaryStage.setTitle("Game Over");
            primaryStage.show();*/
            //TODO PostGameScene
            Platform.exit();
        }

        Position newPos = new Position(x, y);

        boardView.setOnTablePosition(newPos);
        selected = newPos;

        if(table.getTiles()[newPos.x()][newPos.y()] == null && !gameEnded) {
            boardView.setImageOnSelectedTile(nextTile);
            sideBarView.setNextTile(nextTile);

            if (table.canPlace(nextTile, newPos.x(), newPos.y())) {
                boardView.setSelectionOutline(TileView.Outline.GREEN);
//                sideBarView.setNextTileOutline(TileView.Outline.GREEN);
            }
            else {
                boardView.setSelectionOutline(TileView.Outline.RED);
//                sideBarView.setNextTileOutline(TileView.Outline.RED);
            }
        }
        else {
            boardView.setSelectionOutline(TileView.Outline.RED);
//            sideBarView.setNextTileOutline(TileView.Outline.RED);
        }
    }
}
