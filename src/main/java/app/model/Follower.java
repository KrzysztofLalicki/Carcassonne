package app.model;

public class Follower {

    private final Player player;
    private Tile tile;

    public Follower(Player player) {
        this.player = player;
        tile = null;
    }

    public Player getPlayer() {
        return player;
    }
    public void putOnTile(Tile tile) {
        this.tile = tile;
    }
    public Tile getTile() {
        return tile;
    }
    public boolean isFree() {
        return tile == null;
    }
}
