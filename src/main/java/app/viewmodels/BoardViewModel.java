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
    private Table table;
    private TableViewModel tableViewModel;
    private BoardSelectorViewModel boardSelectorViewModel;

    private ObjectProperty<Position> onTablePositionProperty = new SimpleObjectProperty<>(new Position(STARTING_TILE_POSITION, STARTING_TILE_POSITION));;
    private ObjectProperty<Position> onViewPositionProperty = new SimpleObjectProperty<>(new Position(DISPLAYED_GRID_SIZE / 2, DISPLAYED_GRID_SIZE / 2));

    public Table getTable() {return table;}
    public TableViewModel getTableViewModel() {return tableViewModel;}
    public BoardSelectorViewModel getBoardSelectorViewModel() {return boardSelectorViewModel;}
    public ObjectProperty<Position> getOnTablePositionProperty() {return onTablePositionProperty;}
    public ObjectProperty<Position> getOnViewPositionProperty() {return onViewPositionProperty;}

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




}
