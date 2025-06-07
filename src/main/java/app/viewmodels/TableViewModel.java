package app.viewmodels;

import app.model.Follower;
import app.model.TableChangeListener;
import app.model.Table;
import app.model.Tile;
import app.utils.Position;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.HashMap;
import java.util.Map;

public class TableViewModel implements TableChangeListener {

    private BoardViewModel boardViewModel;

    private BooleanProperty tableChangedEvent = new SimpleBooleanProperty(false);

    private ObjectProperty<Position> onTablePositionProperty;
    private ObjectProperty<Position> onViewPositionProperty;

    private Map<Position, TileViewModel> tileViewModels = new HashMap<>();

    public BooleanProperty tableChangedEvent() { return tableChangedEvent; }
    public Table getTable() { return boardViewModel.table; }

    public ObjectProperty<Position> getOnTablePositionProperty() {
        return boardViewModel.onTablePositionProperty;
    }
    public ObjectProperty<Position> getOnViewPositionProperty() {
        return boardViewModel.onViewPositionProperty;
    }
    public Map<Position, TileViewModel> getTileViewModels() {return tileViewModels;}

    public TableViewModel(BoardViewModel boardViewModel) {
        this.boardViewModel = boardViewModel;
        boardViewModel.table.addOnTableChangedListener(this);
    }

    public void placeFollower(Tile tile, Follower follower) {
        tileViewModels.get(tile.getOnTablePosition()).placeFollower(follower);
    }

    @Override
    public void onTableChanged() {
        tableChangedEvent.set(!tableChangedEvent.get());
    }
}
