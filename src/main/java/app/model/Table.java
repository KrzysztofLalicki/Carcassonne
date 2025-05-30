package app.model;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Arrays;

public class Table {
    public static final int TABLE_DIMENSIONS = 143;
    public static final int STARTING_TILE_POSITION = 71;

    private final Game game;
    private final ObjectProperty<Tile>[][] tiles;



    public Table(Game game) {
        this.game = game;

        tiles = new ObjectProperty[TABLE_DIMENSIONS][TABLE_DIMENSIONS];
        for(int i = 0; i < TABLE_DIMENSIONS; i++)
            for(int j = 0; j < TABLE_DIMENSIONS; j++)
                tiles[i][j] = new SimpleObjectProperty(null);

        Tile startingTile = new Tile(new short[]{1, 2, 0, 2, 0, 0}, "/app/img/tiles/D.png");
        startingTile.placeOnTable(this, STARTING_TILE_POSITION, STARTING_TILE_POSITION);
        startingTile.generateSegments();
        tiles[STARTING_TILE_POSITION][STARTING_TILE_POSITION].set(startingTile);
    }
    public void placeTile(Tile tile, int x, int y) {
        if (canPlace(tile, x, y)) {
            tiles[x][y].set(tile);
            tile.placeOnTable(this, x, y);
            tile.generateSegments();
            // TODO: player should be able to place a follower in their turn
            game.nextPlayer();
        }
    }
    public boolean canPlace(Tile tile, int x, int y) {
        if (tiles[x - 1][y].get() == null && tiles[x + 1][y].get() == null && tiles[x][y - 1].get() == null && tiles[x][y + 1].get() == null) return false;
        if (tiles[x - 1][y].get() != null && tile.left != tiles[x - 1][y].get().right) return false;
        if (tiles[x + 1][y].get() != null && tile.right != tiles[x + 1][y].get().left) return false;
        if (tiles[x][y - 1].get() != null && tile.up != tiles[x][y - 1].get().down) return false;
        if (tiles[x][y + 1].get() != null && tile.down != tiles[x][y + 1].get().up) return false;
        return tiles[x][y] == null;
    }
    public ObjectProperty<Tile>[][] getTiles() {
        return tiles;
    }
}
