package app.viewmodels;

import app.model.Follower;
import app.model.Game;
import app.model.Table;
import app.model.Tile;
import app.utils.Position;
import app.view.BoardSelector;
import app.view.TileView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;


import java.util.ArrayList;
import java.util.List;

import static app.model.Table.STARTING_TILE_POSITION;
import static app.view.AbstractBoardView.DISPLAYED_GRID_SIZE;


public class BoardViewModel {
    public Table table;
    public TableViewModel tableViewModel;
    public BoardSelectorViewModel boardSelectorViewModel;

    public ObjectProperty<Position> onTablePositionProperty = new SimpleObjectProperty<>(new Position(STARTING_TILE_POSITION, STARTING_TILE_POSITION));;
    public ObjectProperty<Position> onViewPositionProperty = new SimpleObjectProperty<>(new Position(DISPLAYED_GRID_SIZE / 2, DISPLAYED_GRID_SIZE / 2));

    public BoardViewModel(Game game) {
        this.table = game.getTable();
        tableViewModel = new TableViewModel(this);
        boardSelectorViewModel = new BoardSelectorViewModel(this);

    }

    public void placeTile(Tile tile) {
        boardSelectorViewModel.placeTile(tile);
    }

    public void placeFollower(Tile tile, Follower follower) {
        tableViewModel.placeFollower(tile, follower);
    }




//    private void setOnTablePosition(Position newOnTablePosition) {
//        if(newOnTablePosition.x() < 0 || newOnTablePosition.x() >= Table.TABLE_DIMENSIONS || newOnTablePosition.y() < 0 || newOnTablePosition.y() >= Table.TABLE_DIMENSIONS)
//            return;
//        Position newOnViewPosition = new Position(onViewPosition.get().x() + newOnTablePosition.x() - onTablePosition.get().x(), onViewPosition.get().y() + newOnTablePosition.y() - onTablePosition.get().y());
//        if (newOnViewPosition.x() > 0 && newOnViewPosition.x() < DISPLAYED_GRID_SIZE - 1 && newOnViewPosition.y() > 0 && newOnViewPosition.y() < DISPLAYED_GRID_SIZE - 1)
//            onViewPosition.set(newOnViewPosition);
//        onTablePosition.set(newOnTablePosition);
//        refreshTilesViewModels();
//    }
//
//
//
//    public void onKeyPressed(KeyEvent event) {
//            switch (event.getCode()) {
//                case W, UP: moveSelection(0, -1); break;
//                case S, DOWN: moveSelection(0, 1); break;
//                case A, LEFT: moveSelection(-1, 0); break;
//                case D, RIGHT: moveSelection(1, 0); break;
//                case R: rotateNextTile(); break;
//                case SPACE:
//                        placeTile();
//                        break;
//                    }
//            }
//    }
}
