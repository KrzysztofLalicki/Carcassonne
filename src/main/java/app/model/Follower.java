package app.model;

public class Follower {

    private final Player player;
    private Tile tile;
    int x, y;

    public Follower(Player player) {
        this.player = player;
        tile = null;
    }

    public Player getPlayer() {
        return player;
    }
    public void placeOnTile(Tile tile, int x, int y) {
        if (tile.canPlace(x, y)) {
            this.tile = tile;
            this.x = x;
            this.y = y;
            tile.getSegments()[x][y].addFollower(this);
        }
    }
    public void remove() {
        tile = null;
    }
    public Tile getTile() {
        return tile;
    }
    public boolean isFree() {
        return tile == null;
    }
}
