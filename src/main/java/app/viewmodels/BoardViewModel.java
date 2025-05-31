package app.viewmodels;

import app.model.Game;
import app.model.Table;
import app.model.Tile;
import app.utils.Position;
import app.view.TileView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


public class BoardViewModel {
    public static final int DISPLAYED_GRID_SIZE = 13;

    private Game game;

    private TileViewModel[][] tilesViewModels = new TileViewModel[Table.TABLE_DIMENSIONS][Table.TABLE_DIMENSIONS];
    private ObjectProperty<TileViewModel>[][] onViewTilesViewModels = new ObjectProperty[DISPLAYED_GRID_SIZE][DISPLAYED_GRID_SIZE];

    private ObjectProperty<Position> onTablePosition = new SimpleObjectProperty<Position>(new Position(Table.STARTING_TILE_POSITION, Table.STARTING_TILE_POSITION));
    private ObjectProperty<Position> onViewPosition = new SimpleObjectProperty<Position>(new Position(DISPLAYED_GRID_SIZE / 2, DISPLAYED_GRID_SIZE / 2));
    private Position prevOnViewPosition = null;
    private Position prevOnTablePosition = null;

    public ObjectProperty<TileViewModel> getOnTableTilesViewModelsProperties(int x, int y) {return onViewTilesViewModels[x][y];}

    private void setOnTablePosition(Position newOnTablePosition) {
        if(newOnTablePosition.x() < 0 || newOnTablePosition.x() >= Table.TABLE_DIMENSIONS || newOnTablePosition.y() < 0 || newOnTablePosition.y() >= Table.TABLE_DIMENSIONS)
            return;
        Position newOnViewPosition = new Position(onViewPosition.get().x() + newOnTablePosition.x() - onTablePosition.get().x(), onViewPosition.get().y() + newOnTablePosition.y() - onTablePosition.get().y());
        if (newOnViewPosition.x() > 0 && newOnViewPosition.x() < DISPLAYED_GRID_SIZE - 1 && newOnViewPosition.y() > 0 && newOnViewPosition.y() < DISPLAYED_GRID_SIZE - 1)
            onViewPosition.set(newOnViewPosition);
        onTablePosition.set(newOnTablePosition);
        refreshTilesViewModels();
    }

    public void moveSelection(int x, int y) {
        setOnTablePosition(new Position(onTablePosition.get().x() + x, onTablePosition.get().y() + y));
    }

    private ObjectProperty<Tile> nextTile = new SimpleObjectProperty<>();
    private TileViewModel nextTileViewModel = new TileViewModel(nextTile);

    public BoardViewModel(Game game) {
        this.game = game;

        for (int i = 0; i < 143; i++)
            for (int j = 0; j < 143; j++)
                tilesViewModels[i][j] = new TileViewModel(game.getTable().getTileProperty(i, j));
        refreshTilesViewModels();

        nextTile.bind(game.getBox().getNextTileProperty());
        updateSelection();

        nextTile.addListener((_, _, _) -> updateSelection());
    }

    public void rotateNextTile() {
        nextTile.get().rotate();
        updateSelection();
    }

    public void placeTile() {
        game.getTable().placeTile(nextTile.get(), onTablePosition.get().x(), onTablePosition.get().y());
    }

    private void refreshTilesViewModels() {
        for(int xTable = onTablePosition.get().x() - onViewPosition.get().x(), xView = 0; xView < DISPLAYED_GRID_SIZE; xTable++, xView++)
            for(int yTable = onTablePosition.get().y() - onViewPosition.get().y(), yView = 0; yView < DISPLAYED_GRID_SIZE; yTable++, yView++)
                if(xTable >= 0 && xTable < Table.TABLE_DIMENSIONS && yTable >= 0 && yTable < Table.TABLE_DIMENSIONS){
                    if(onViewTilesViewModels[xView][yView] == null)
                        onViewTilesViewModels[xView][yView] = new SimpleObjectProperty<>(tilesViewModels[xTable][yTable]);
                    else {
                        tilesViewModels[xTable][yTable].setOutline(TileView.Outline.NONE);
                        onViewTilesViewModels[xView][yView].set(tilesViewModels[xTable][yTable]);
                    }
                }
        updateSelection();
    }

    private void updateSelection() {
        if(prevOnViewPosition != null && prevOnTablePosition != null) {
            onViewTilesViewModels[prevOnViewPosition.x()][prevOnViewPosition.y()].get().setOutline(TileView.Outline.NONE);
            onViewTilesViewModels[prevOnViewPosition.x()][prevOnViewPosition.y()].set(tilesViewModels[prevOnTablePosition.x()][prevOnTablePosition.y()]);
        }

        prevOnViewPosition = new Position(onViewPosition.get().x(), onViewPosition.get().y());
        prevOnTablePosition = new Position(onTablePosition.get().x(), onTablePosition.get().y());

        if(game.getTable().getTile(onTablePosition.get().x(), onTablePosition.get().y()) == null)
            onViewTilesViewModels[onViewPosition.get().x()][onViewPosition.get().y()].set(nextTileViewModel);
        else
            onViewTilesViewModels[onViewPosition.get().x()][onViewPosition.get().y()].set(tilesViewModels[prevOnTablePosition.x()][prevOnTablePosition.y()]);

        if(game.getTable().canPlace(nextTile.get(), onTablePosition.get().x(), onTablePosition.get().y()))
            onViewTilesViewModels[onViewPosition.get().x()][onViewPosition.get().y()].get().setOutline(TileView.Outline.GREEN);
        else
            onViewTilesViewModels[onViewPosition.get().x()][onViewPosition.get().y()].get().setOutline(TileView.Outline.RED);
    }
}
