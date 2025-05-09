package app.view;

import app.model.Table;
import app.model.Position;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class BoardView extends GridPane {
    private static final int DISPLAYED_GRID_SIZE = 11;

    private TileView[][] tiles;
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
        onTablePosition = new Position(0, 0);

        updateTilesImages();
    }

    private void updateTilesImages() {
        for(int xTable = onTablePosition.x - onViewPosition.x, xView = 0; xView < DISPLAYED_GRID_SIZE; xTable++, xView++)
            for(int yTable = onTablePosition.y - onViewPosition.y, yView = 0;  yView < DISPLAYED_GRID_SIZE; yTable++, yView++) {
                try {
                    tiles[xView][yView].setTileImage(new Image(getClass().getResource(table.getTiles()[xTable][yTable].get_image_path()).toExternalForm()));
                } catch(NullPointerException | IndexOutOfBoundsException e) {
                    tiles[xView][yView].setTileImage(null);
                }
            }
    }


    public void setOnTablePosition(Position newOnTablePosition) {
        Position newOnViewPosition = new Position(onViewPosition.x + newOnTablePosition.x - onTablePosition.x, onViewPosition.y + newOnTablePosition.y - onTablePosition.y);

        if(newOnViewPosition.x <= 0 || newOnViewPosition.x >= DISPLAYED_GRID_SIZE - 1 || newOnViewPosition.y <= 0 || newOnViewPosition.y >= DISPLAYED_GRID_SIZE - 1) {
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
