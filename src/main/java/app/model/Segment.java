package app.model;

public abstract class Segment {

    Tile tile;
    Follower follower;

    public Segment(Tile tile) {
        this.tile = tile;
        follower = null;
    }

    public void addFollower(Follower follower) {
        this.follower = follower;
    }
    public void removeFollower() {
        follower = null;
    }
}
