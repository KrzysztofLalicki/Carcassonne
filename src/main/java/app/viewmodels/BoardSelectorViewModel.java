package app.viewmodels;

import app.model.Table;
import app.model.Tile;
import app.utils.Position;
import app.view.BoardSelector;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;


import java.util.function.Consumer;

import static app.view.AbstractBoardView.DISPLAYED_GRID_SIZE;

public class BoardSelectorViewModel {
    private BoardViewModel boardViewModel;

    public ObjectProperty<Position> onViewPosition;
    public ObjectProperty<Position> onTablePosition;// = new SimpleObjectProperty<>();
    public ObjectProperty<Tile> onViewTile  = new SimpleObjectProperty<>();
    public ObjectProperty<BoardSelector.Outline> outlineProperty = new SimpleObjectProperty<>(BoardSelector.Outline.NONE);

    private Tile tile;

    public BoardSelectorViewModel(BoardViewModel boardViewModel) {
        this.boardViewModel = boardViewModel;

        this.onViewPosition = boardViewModel.onViewPositionProperty;
        this.onTablePosition = boardViewModel.onTablePositionProperty;

        updateViewProperties();
    }

    private void updateViewProperties() {
        if(boardViewModel.table.getTile(onTablePosition.get().x(), onTablePosition.get().y()) != null)
            onViewTile.set(null);
        else
            onViewTile.set(tile);

//        if(onViewPosition.get() == null || onViewTile.get() == null) {
//            outlineProperty.set(BoardSelector.Outline.NONE);
//            return;
//        }
        if(boardViewModel.table.canPlace(tile, onTablePosition.get().x(), onTablePosition.get().y()))
            outlineProperty.set(BoardSelector.Outline.GREEN);
        else
            outlineProperty.set(BoardSelector.Outline.RED);
    }

    public void placeTile(Tile tile) {
        this.tile = tile;
        KeyboardManager.getInstance().register(keyEventHandler);
    }

    public void moveSelection(int x, int y) {
        setOnTablePosition(new Position(onTablePosition.get().x() + x, onTablePosition.get().y() + y));
    }

    private void setOnTablePosition(Position newOnTablePosition) {
        if(newOnTablePosition.x() < 0 || newOnTablePosition.x() >= Table.TABLE_DIMENSIONS || newOnTablePosition.y() < 0 || newOnTablePosition.y() >= Table.TABLE_DIMENSIONS)
            return;
        Position newOnViewPosition = new Position(onViewPosition.get().x() + newOnTablePosition.x() - onTablePosition.get().x(), onViewPosition.get().y() + newOnTablePosition.y() - onTablePosition.get().y());
        if (newOnViewPosition.x() > 0 && newOnViewPosition.x() < DISPLAYED_GRID_SIZE - 1 && newOnViewPosition.y() > 0 && newOnViewPosition.y() < DISPLAYED_GRID_SIZE - 1)
            onViewPosition.set(newOnViewPosition);
        onTablePosition.set(newOnTablePosition);
    }

    private final Consumer<KeyEvent> keyEventHandler = new Consumer<KeyEvent>() {
        @Override
        public void accept(KeyEvent event) {
            switch (event.getCode()) {
                case W, UP -> moveSelection(0, -1);
                case S, DOWN -> moveSelection(0, 1);
                case A, LEFT -> moveSelection(-1, 0);
                case D, RIGHT -> moveSelection(1, 0);
                case R -> tile.rotate();
                case SPACE ->  {
                    if(boardViewModel.table.canPlace(tile, onTablePosition.get().x(), onTablePosition.get().y())) {
                        KeyboardManager.getInstance().remove(this);
                        boardViewModel.table.placeTile(tile, onTablePosition.get().x(), onTablePosition.get().y());
                    }
                }
            }
            updateViewProperties();
        }
    };
}
