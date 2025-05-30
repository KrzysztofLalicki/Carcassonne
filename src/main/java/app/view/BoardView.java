package app.view;

import app.model.Table;
import app.utils.Position;
import app.model.Tile;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class BoardView extends GridPane {
    private static final int DISPLAYED_GRID_SIZE = 13;
    private final static String TILE_IMAGE_DIRECTORY = "/app/img/tiles/";
    private final static String TILE_IMAGE_EXTENSION = ".png";

    private final TileView[][] tiles;
    private Position onViewPosition;
    private TileView.Outline outline = TileView.Outline.RED;

    private final Tile[][] table;
    private Position onTablePosition;


    public BoardView(Table table) {
        tiles = new TileView[DISPLAYED_GRID_SIZE][DISPLAYED_GRID_SIZE];
        for(int i = 0; i < DISPLAYED_GRID_SIZE; i++)
            for(int j = 0; j < DISPLAYED_GRID_SIZE; j++) {
                tiles[i][j] = new TileView();
                add(tiles[i][j], i, j);
            }
        onViewPosition = new Position(DISPLAYED_GRID_SIZE / 2, DISPLAYED_GRID_SIZE / 2);
        tiles[onViewPosition.x()][onViewPosition.y()].setOutline(outline);

        this.table = table.getTiles();
        onTablePosition = new Position(0, 0);

        updateTilesImages();
    }



    private void updateTilesImages() {
        for(int xTable = onTablePosition.x() - onViewPosition.x(), xView = 0; xView < DISPLAYED_GRID_SIZE; xTable++, xView++)
            for(int yTable = onTablePosition.y() - onViewPosition.y(), yView = 0; yView < DISPLAYED_GRID_SIZE; yTable++, yView++) {
                try {
                    tiles[xView][yView].setTileImage(new Image(getClass().getResource(TILE_IMAGE_DIRECTORY + table[xTable][yTable].getSymbol() + TILE_IMAGE_EXTENSION).toExternalForm()));
                    tiles[xView][yView].setRotate(table[xTable][yTable].getRotation());
                } catch(NullPointerException | IndexOutOfBoundsException e) {
                    tiles[xView][yView].setTileImage(null);
                }
            }
    }

    public void setSelectionOutline(TileView.Outline outline) {
        this.outline = outline;
        tiles[onViewPosition.x()][onViewPosition.y()].setOutline(outline);
    }

    public void setImageOnSelectedTile(Tile tile){
        try {
            tiles[onViewPosition.x()][onViewPosition.y()].setTileImage(new Image(getClass().getResource(TILE_IMAGE_DIRECTORY + tile.getSymbol() + TILE_IMAGE_EXTENSION).toExternalForm()));
            tiles[onViewPosition.x()][onViewPosition.y()].setRotate(tile.getRotation());
        } catch(NullPointerException | IndexOutOfBoundsException e) {
            tiles[onViewPosition.x()][onViewPosition.y()].setTileImage(null);
        }
    }


    public void setOnTablePosition(Position newOnTablePosition) {
        Position newOnViewPosition = new Position(onViewPosition.x() + newOnTablePosition.x() - onTablePosition.x(), onViewPosition.y() + newOnTablePosition.y() - onTablePosition.y());
        if(newOnViewPosition.x() > 0 && newOnViewPosition.x() < DISPLAYED_GRID_SIZE - 1 && newOnViewPosition.y() > 0 && newOnViewPosition.y() < DISPLAYED_GRID_SIZE - 1) {
            tiles[onViewPosition.x()][onViewPosition.y()].setOutline(TileView.Outline.NONE);
            onViewPosition = newOnViewPosition;
            tiles[onViewPosition.x()][onViewPosition.y()].setOutline(outline);
        }
        onTablePosition = newOnTablePosition;
        updateTilesImages();
    }
}
