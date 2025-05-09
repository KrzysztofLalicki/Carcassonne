package app.model;

public class Table {
    public static final int TABLE_DIMENSIONS = 143;
    public static final int STARTING_TILE_POSITION = 71;

    Tile[][] tiles;
    public Table() {
        tiles = new Tile[TABLE_DIMENSIONS][TABLE_DIMENSIONS];
        tiles[STARTING_TILE_POSITION][STARTING_TILE_POSITION] = new Tile(new int[]{0, 2, 1, 2, 0, 0}, "/app/img/tiles/D.png");
    }
    public void putTile(Tile tile, int x, int y) {
        if (canPlace(tile, x, y)) {
            tiles[x][y] = tile;
        }
    }
    public boolean canPlace(Tile tile, int x, int y) {
        if (tiles[x][y] != null) return false;
        if (tiles[x - 1][y] == null && tiles[x + 1][y] == null && tiles[x][y - 1] == null && tiles[x][y + 1] == null) return false;
        if (tiles[x - 1][y] != null && tile.left != tiles[x - 1][y].right) return false;
        if (tiles[x + 1][y] != null && tile.right != tiles[x + 1][y].left) return false;
        if (tiles[x][y - 1] != null && tile.up != tiles[x][y - 1].down) return false;
        if (tiles[x][y + 1] != null && tile.down != tiles[x][y + 1].up) return false;
        return true;
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}
