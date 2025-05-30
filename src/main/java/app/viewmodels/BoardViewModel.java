package app.viewmodels;

import app.model.Table;
import app.utils.Position;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


public class BoardViewModel {
    public static final int DISPLAYED_GRID_SIZE = 13;

    private TileViewModel[][] tileViewModels = new TileViewModel[143][143];

    private ObjectProperty<Position> onTablePosition = new SimpleObjectProperty<>(new Position(Table.STARTING_TILE_POSITION, Table.STARTING_TILE_POSITION));
    public Position getOnTablePosition() {
        return onTablePosition.get();
    }
    private void setOnTablePosition(Position newOnTablePosition) {
        onViewPosition.set(new Position(getOnViewPosition().x() + newOnTablePosition.x() - getOnTablePosition().x(), getOnViewPosition().y() + newOnTablePosition.y() - getOnTablePosition().y()));
        onTablePosition.set(newOnTablePosition);
    }
    public ObjectProperty<Position> getOnTablePositionProperty() {
        return onTablePosition;
    }

    private ObjectProperty<Position> onViewPosition = new SimpleObjectProperty<>(new Position(DISPLAYED_GRID_SIZE / 2, DISPLAYED_GRID_SIZE / 2));
    public Position getOnViewPosition() {
        return onViewPosition.get();
    }
    public ObjectProperty<Position> getOnViewPositionProperty() {
        return onViewPosition;
    }

    private TileViewModel selectionTileViewModel;

    public TileViewModel getSelectionTileViewModel() {
        return selectionTileViewModel;
    }

    public BoardViewModel(Table table) {
        for (int i = 0; i < 143; i++)
            for (int j = 0; j < 143; j++)
                tileViewModels[i][j] = new TileViewModel(table.getTiles()[i][j]);
        selectionTileViewModel = new TileViewModel(table.getTiles()[71][71]);
    }

    public TileViewModel getTileViewModel(int x, int y) {
        return tileViewModels[x][y];
    }
}
