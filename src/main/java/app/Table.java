package app;

public class Table {
    Tile[][] tiles;
    public Table() {
        tiles = new Tile[143][143];
        tiles[71][71] = new Tile(new int[]{0, 2, 1, 2, 0, 0}, "D.png");
    }
    public void put_tile(Tile tile, int x, int y) {
        if (can_place(tile, x, y)) {
            tiles[x][y] = tile;
        }
    }
    public boolean can_place(Tile tile, int x, int y) {
        if (tiles[x][y] != null) return false;
        if (tiles[x - 1][y] == null && tiles[x + 1][y] == null && tiles[x][y - 1] == null && tiles[x][y + 1] == null) return false;
        if (tiles[x - 1][y] != null && tile.left != tiles[x - 1][y].right) return false;
        if (tiles[x + 1][y] != null && tile.right != tiles[x + 1][y].left) return false;
        if (tiles[x][y - 1] != null && tile.up != tiles[x][y - 1].down) return false;
        if (tiles[x][y + 1] != null && tile.down != tiles[x][y + 1].up) return false;
        return true;
    }
}
