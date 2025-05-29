package app.model;

import java.util.ArrayList;

public class Tile {

    int x, y;
    short up, left, centre, right, down;
    boolean pennant;
    private final ArrayList<Segment> segments;

    private final String image_path;
    private Integer rotation = 0;

    public Tile(short[] terrain, String image_path) {
        up = terrain[0];
        left = terrain[1];
        centre = terrain[2];
        right = terrain[3];
        down = terrain[4];
        pennant = terrain[5] == 1;
        this.image_path = image_path;
        segments = new ArrayList<>();
    }

    public void rotate() {
        short temp = up;
        up = left;
        left = down;
        down = right;
        right = temp;
        rotation = (rotation + 90) % 360;
    }
    public String getImagePath() {
        return image_path;
    }
    public Integer getRotation() {
        return rotation;
    }
    public void generateSegments() {
        if (centre == 3) {
            segments.add(new Cloister(this));
        }
    }
    public ArrayList<Segment> getSegments() {
        return segments;
    }
    public boolean placeFollower(Follower follower) {
        for (Segment segment : segments) {
            if (segment instanceof Cloister) {
                follower.putOnTile(this);
                segment.addFollower(follower);
                return true;
            }
        }
        return false;
    }
}
