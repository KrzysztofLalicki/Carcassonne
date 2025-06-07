package app.model;

public interface GameActionListener {
    void placeTile(Tile tile);
    void placeFollower(Tile tile, Follower follower);
}
