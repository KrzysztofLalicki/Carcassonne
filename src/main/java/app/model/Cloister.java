package app.model;

public class Cloister extends Segment {
    int surroundings;
    public Cloister(Tile tile) {
        super(tile);
        surroundings = 0;
    }
    public void finish() {
        follower.getPlayer().addPoints(9);
        follower.putOnTile(null);
        follower = null;
        removeFollower();
    }
}
