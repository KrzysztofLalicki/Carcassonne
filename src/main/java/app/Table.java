package app;

public class Table {
    public Tile[][] tiles;
    public Table() {
        tiles = new Tile[143][143];
        tiles[71][71] = new Tile(new int[]{0, 2, 1, 2, 0, 0}, "/img/tiles/mock/blue.png");
    }
    public void put_tile(Tile tile, int x, int y) {
        tiles[x][y] = tile;
    }
}
