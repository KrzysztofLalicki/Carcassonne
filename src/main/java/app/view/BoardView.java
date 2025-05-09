package app.view;

import app.Table;
import app.model.Position;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class BoardView extends GridPane {
    private static final int DISPLAYED_GRID_SIZE = 11;

    private TileView[][] tiles = new TileView[DISPLAYED_GRID_SIZE][DISPLAYED_GRID_SIZE];
    private Position onViewPosition;

    private Table table;
    private Position onTablePosition;


    public BoardView(Table table) {
        tiles = new TileView[DISPLAYED_GRID_SIZE][DISPLAYED_GRID_SIZE];
        for(int i = 0; i < DISPLAYED_GRID_SIZE; i++)
            for(int j = 0; j < DISPLAYED_GRID_SIZE; j++) {
                tiles[i][j] = new TileView();
                add(tiles[i][j], i, j);
            }
        onViewPosition = new Position(DISPLAYED_GRID_SIZE / 2, DISPLAYED_GRID_SIZE / 2);
        tiles[onViewPosition.x][onViewPosition.y].select();

        this.table = table;
        onTablePosition = new Position(71 - DISPLAYED_GRID_SIZE / 2, 71 - DISPLAYED_GRID_SIZE / 2);

        updateTilesImages();
    }

    private void updateTilesImages() {
        for(int i = 0; i < DISPLAYED_GRID_SIZE; i++)
            for(int j = 0; j < DISPLAYED_GRID_SIZE; j++) {
                if (table.getTiles()[i + onTablePosition.x][j + onTablePosition.y] != null)
                    tiles[i][j].setTileImage(new Image(getClass().getResource(table.getTiles()[i + onTablePosition.x][j + onTablePosition.y].get_image_path()).toExternalForm()));
                else
                    tiles[i][j].setTileImage(null);
            }
    }


    public void setOnTablePosition(Position newOnTablePosition) {
        //Position diff = new Position(newOnTablePosition.x - onTablePosition.x, newOnTablePosition.y - onTablePosition.y);
        Position newOnViewPosition = new Position(onViewPosition.x + newOnTablePosition.x - onTablePosition.x, onViewPosition.y + newOnTablePosition.y - onTablePosition.y);
        if(newOnViewPosition.x <= 0 || newOnViewPosition.x >= DISPLAYED_GRID_SIZE || newOnViewPosition.y <= 0 || newOnViewPosition.y >= DISPLAYED_GRID_SIZE) {
            onTablePosition = newOnTablePosition;
            tiles[onViewPosition.x][onViewPosition.y].select();
            onViewPosition = new Position(DISPLAYED_GRID_SIZE / 2, DISPLAYED_GRID_SIZE / 2);
            tiles[onViewPosition.x][onViewPosition.y].select();
            onTablePosition = newOnTablePosition;
            updateTilesImages();
        }
        else {
            tiles[onViewPosition.x][onViewPosition.y].select();
            onViewPosition = newOnViewPosition;
            tiles[onViewPosition.x][onViewPosition.y].select();
            onTablePosition = newOnTablePosition;
        }
    }
}
