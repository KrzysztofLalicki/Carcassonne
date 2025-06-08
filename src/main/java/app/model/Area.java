package app.model;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Area {

    HashSet<Tile> tiles;
    ArrayList<Segment> segments;
    ArrayList<Follower> followers;

    public Area(Tile tile) {
        tiles = new HashSet<>();
        tiles.add(tile);
        segments = new ArrayList<>();
        followers = new ArrayList<>();
    }

    public void mergeWith(Area area) {
        if (area.getClass() == getClass() && area != this) {
            for (Segment segment : area.segments) {
                segment.setArea(this);
            }
            tiles.addAll(area.tiles);
            followers.addAll(area.followers);
        }
    }

    public void addSegment(Segment segment) {
        segments.add(segment);
    }
    public void addFollower(Follower follower) {
        followers.add(follower);
    }
    public boolean isFree() {
        return followers.isEmpty();
    }
    public boolean isFinished() { return false; }
    public void finish() {}
}
