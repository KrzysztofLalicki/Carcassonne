package app.controller;

import app.model.*;
import app.view.BoardView;
import app.view.TileView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class BoardController {
    private final Box box;
    private final Table table;
    Tile nextTile;

    private final BoardView boardView;
    private Position selected;


    public BoardController(Game game) {
        box = game.getBox();
        table = game.getTable();

        this.boardView = new BoardView(game.getTable());
        selected = new Position(Table.STARTING_TILE_POSITION, Table.STARTING_TILE_POSITION);
        boardView.setOnTablePosition(selected);
        nextTile = game.getBox().giveTile();
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public void handleKeyPress(KeyEvent event) {
        int x = selected.x;
        int y = selected.y;

        if (event.getCode() == KeyCode.UP) y--;
        else if(event.getCode() == KeyCode.DOWN) y++;
        else if(event.getCode() == KeyCode.LEFT) x--;
        else if(event.getCode() == KeyCode.RIGHT) x++;
        else if(event.getCode() == KeyCode.R)
            nextTile.rotate();
        else if(event.getCode() == KeyCode.SPACE && table.canPlace(nextTile, selected.x, selected.y)) {
            table.putTile(nextTile, selected.x, selected.y);
            nextTile = box.giveTile();
        }

        Position newPos = new Position(x, y);

        boardView.setOnTablePosition(newPos);
        selected = newPos;

        if(table.getTiles()[newPos.x][newPos.y] == null) {
            boardView.setImageOnSelectedTile(nextTile);

            if (table.canPlace(nextTile, newPos.x, newPos.y))
                boardView.setSelectionOutline(TileView.Outline.GREEN);
            else
                boardView.setSelectionOutline(TileView.Outline.RED);
        }
        else
            boardView.setSelectionOutline(TileView.Outline.RED);
    }
}
