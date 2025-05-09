package app.controller;

import app.model.Position;
import app.view.BoardView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class BoardController {
    private final BoardView board;
//    private Position selected;

    public BoardController(BoardView board) {
        this.board = board;
//        selected = new Position(69, 69);
//        board.setOnTablePosition(selected);
    }

    public void handleKeyPress(KeyEvent event) {
//        int x = selected.x;
//        int y = selected.y;
//
//        if (event.getCode() == KeyCode.UP && y > 0) y--;
//        else if (event.getCode() == KeyCode.DOWN && y < 4) y++;
//        else if (event.getCode() == KeyCode.LEFT && x > 0) x--;
//        else if (event.getCode() == KeyCode.RIGHT && x < 4) x++;
//
//        Position newPos = new Position(x, y);
//        if (!newPos.equals(selected)) {
//            board.setOnTablePosition(newPos);
//            selected = newPos;
//        }
    }
}
