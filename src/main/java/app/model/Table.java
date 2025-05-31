package app.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Table {

    public static final int TABLE_DIMENSIONS = 145;
    public static final int STARTING_TILE_POSITION = 72;
    private static final String STARTING_TILE_SYMBOL = "D";

    private final Game game;
    private final ObjectProperty<Tile>[][] tiles;

    public Table(Game game) {
        this.game = game;
        tiles = new ObjectProperty[TABLE_DIMENSIONS][TABLE_DIMENSIONS];
        for(int i = 0; i < TABLE_DIMENSIONS; i++) {
            for(int j = 0; j < TABLE_DIMENSIONS; j++) {
                tiles[i][j] = new SimpleObjectProperty(null);
            }
        }
        Tile startingTile = new Tile(new short[]{1, 2, 0, 2, 0, 0}, STARTING_TILE_SYMBOL);
        startingTile.placeOnTable(this, STARTING_TILE_POSITION, STARTING_TILE_POSITION);
        startingTile.generateSegments();
        tiles[STARTING_TILE_POSITION][STARTING_TILE_POSITION].set(startingTile);
    }

    public boolean canPlace(Tile tile, int x, int y) {
        if (tile == null) return false;
        if (tiles[x - 1][y].get() == null && tiles[x + 1][y].get() == null && tiles[x][y - 1].get() == null && tiles[x][y + 1].get() == null) return false;
        if (tiles[x - 1][y].get() != null && tile.left != tiles[x - 1][y].get().right) return false;
        if (tiles[x + 1][y].get() != null && tile.right != tiles[x + 1][y].get().left) return false;
        if (tiles[x][y - 1].get() != null && tile.up != tiles[x][y - 1].get().down) return false;
        if (tiles[x][y + 1].get() != null && tile.down != tiles[x][y + 1].get().up) return false;
        return tiles[x][y].get() == null;
    }
    public boolean canPlaceAnywhere(Tile tile) {
        for (int x = 1; x < TABLE_DIMENSIONS - 1; x++) {
            for (int y = 1; y < TABLE_DIMENSIONS - 1; y++) {
                for (int r = 0; r < 4; r++) {
                    if (canPlace(tile, x, y)) return true;
                    tile.rotate();
                }
            }
        }
        return false;
    }
    public void placeTile(Tile tile, int x, int y) {
        if (canPlace(tile, x, y)) {
            tiles[x][y].set(tile);
            tile.placeOnTable(this, x, y);
            tile.generateSegments();
            game.nextPlayer();
        }
    }
    public Tile getTile(int x, int y) {
        if(tiles[x][y] == null)
            return null;
        return tiles[x][y].get();
    }
    public ObjectProperty<Tile> getTileProperty(int x, int y) {
        return tiles[x][y];
    }
}
