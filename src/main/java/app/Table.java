public class Table {
    Tile[][] tiles;
    public Table() {
        tiles = new Tile[143][143];
        tiles[71][71] = new Tile(new int[]{0, 2, 1, 2, 0, 0});
    }
    public void put_tile(Tile tile, int x, int y) {
        if (tiles[x - 1][y] != null && tiles[x - 1][y].right != tile.left)
            throw new IllegalArgumentException("You cannot place this tile here!");
        if (tiles[x][y - 1] != null && tiles[x][y - 1].down != tile.up)
            throw new IllegalArgumentException("You cannot place this tile here!");
        if (tiles[x + 1][y] != null && tiles[x + 1][y].left != tile.right)
            throw new IllegalArgumentException("You cannot place this tile here!");
        if (tiles[x][y + 1] != null && tiles[x][y + 1].up != tile.down)
            throw new IllegalArgumentException("You cannot place this tile here!");
        tiles[x][y] = tile;
    }
}
