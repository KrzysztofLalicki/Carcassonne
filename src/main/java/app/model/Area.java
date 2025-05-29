package app.model;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Area {

    HashSet<Tile> tiles;
    ArrayList<Segment> segments;
    ArrayList<Follower> followers;

    public Area() {
        tiles = new HashSet<>();
        segments = new ArrayList<>();
        followers = new ArrayList<>();
    }

    public void mergeWith(Area area) {
        for (Segment segment : area.segments) {
            segment.setArea(this);
        }
        tiles.addAll(area.tiles);
        segments.addAll(area.segments);
        followers.addAll(area.followers);
    }

    public void addTile(Tile tile) {
        tiles.add(tile);
    }
    public void addFollower(Follower follower) {
        followers.add(follower);
    }
    public boolean isFree() {
        return followers.isEmpty();
    }
}
