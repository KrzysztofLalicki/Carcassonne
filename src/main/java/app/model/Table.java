package app.model;

public class Table {

    public static final int TABLE_DIMENSIONS = 143;
    public static final int STARTING_TILE_POSITION = 71;
    private static final String STARTING_TILE_SYMBOL = "D";

    private final Game game;
    private final Tile[][] tiles;

    public Table(Game game) {
        this.game = game;

        tiles = new Tile[TABLE_DIMENSIONS][TABLE_DIMENSIONS];
        Tile startingTile = new Tile(new short[]{1, 2, 0, 2, 0, 0}, STARTING_TILE_SYMBOL);
        startingTile.placeOnTable(this, STARTING_TILE_POSITION, STARTING_TILE_POSITION);
        startingTile.generateSegments();
        tiles[STARTING_TILE_POSITION][STARTING_TILE_POSITION] = startingTile;
    }

    public boolean canPlace(Tile tile, int x, int y) {
        if (tiles[x - 1][y] == null && tiles[x + 1][y] == null && tiles[x][y - 1] == null && tiles[x][y + 1] == null) return false;
        if (tiles[x - 1][y] != null && tile.left != tiles[x - 1][y].right) return false;
        if (tiles[x + 1][y] != null && tile.right != tiles[x + 1][y].left) return false;
        if (tiles[x][y - 1] != null && tile.up != tiles[x][y - 1].down) return false;
        if (tiles[x][y + 1] != null && tile.down != tiles[x][y + 1].up) return false;
        return tiles[x][y] == null;
    }
    public boolean canPlaceAnywhere(Tile tile) {
        for (int x = 0; x < TABLE_DIMENSIONS; x++) {
            for (int y = 0; y < TABLE_DIMENSIONS; y++) {
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
            tiles[x][y] = tile;
            tile.placeOnTable(this, x, y);
            tile.generateSegments();
            // TODO: player should be able to place a follower in their turn
            game.nextPlayer();
        }
    }
    public Tile[][] getTiles() {
        return tiles;
    }
}
