package app.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.List;

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
        Tile startingTile = new Tile(new short[]{1, 2, 0, 2, 0, 0}, STARTING_TILE_SYMBOL, game);
        startingTile.placeOnTable(this, STARTING_TILE_POSITION, STARTING_TILE_POSITION);
        startingTile.generateSegments();
        tiles[STARTING_TILE_POSITION][STARTING_TILE_POSITION].set(startingTile);
        notifyOnTableChangedListeners();
    }

    public boolean canPlace(Tile tile, int x, int y) {
        if(x <= 0 || x >= TABLE_DIMENSIONS - 1) return false;
        if(y <= 0 || y >= TABLE_DIMENSIONS - 1) return false;

        if (tile == null) return false;
        if (tiles[x - 1][y].get() == null && tiles[x + 1][y].get() == null && tiles[x][y - 1].get() == null && tiles[x][y + 1].get() == null) return false;
        if (tiles[x - 1][y].get() != null && tile.left != tiles[x - 1][y].get().right) return false;
        if (tiles[x + 1][y].get() != null && tile.right != tiles[x + 1][y].get().left) return false;
        if (tiles[x][y - 1].get() != null && tile.up != tiles[x][y - 1].get().down) return false;
        if (tiles[x][y + 1].get() != null && tile.down != tiles[x][y + 1].get().up) return false;
        return tiles[x][y].get() == null;
    }
    public void placeTile(Tile tile, int x, int y) {
        if (canPlace(tile, x, y)) {
            tiles[x][y].set(tile);
            tile.placeOnTable(this, x, y);
            notifyOnTableChangedListeners();
            tile.generateSegments();
            game.notifyPlaceFollowerListeners(tile, game.getCurrentPlayer().getFollower());
        }
    }
    public Tile getTile(int x, int y) {
        if(tiles[x][y] == null)
            return null;
        return tiles[x][y].get();
    }

    public void endGame() {
        for (int x = 0; x < TABLE_DIMENSIONS; x++) {
            for (int y = 0; y < TABLE_DIMENSIONS; y++) {
                if(getTile(x, y) != null) {
                    for (int xx = 0; xx < 3; xx++) {
                        for (int yy = 0; yy < 3; yy++) {
                            if (getTile(x, y).getSegments()[xx][yy] != null) {
                                Area a = getTile(x, y).getSegments()[xx][yy].getArea();
                                if (a instanceof City c) {
                                    c.finish();
                                }
                                if (a instanceof Road r) {
                                    r.finish();
                                }
                                if (a instanceof Field f) {
                                    f.finish();
                                }
                                if (a instanceof Cloister c) {
                                    c.finish();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public ObjectProperty<Tile> getTileProperty(int x, int y) {
        return tiles[x][y];
    }

    private List<TableChangeListener> onTableChangedListeners = new ArrayList<>();
    public void addOnTableChangedListener(TableChangeListener onTableChangedListener) {
        onTableChangedListeners.add(onTableChangedListener);
    }
    public void removeOnTableChangedListener(TableChangeListener onTableChangedListener) {
        onTableChangedListeners.remove(onTableChangedListener);
    }
    public void notifyOnTableChangedListeners() {
        for(TableChangeListener listener : onTableChangedListeners)
            listener.onTableChanged();
    }
}
