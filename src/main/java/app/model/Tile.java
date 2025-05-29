package app.model;

public class Tile {

    short up, left, centre, right, down;
    boolean pennant;
    private final Segment[][] segments;

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
        segments = new Segment[3][3];
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
            segments[1][1] = new Segment(new Cloister());
            segments[1][1].getArea().addTile(this);
        }
    }
    public Segment[][] getSegments() {
        return segments;
    }
    public boolean canPlace(int x, int y) {
        return segments[x][y] != null && segments[x][y].getArea().isFree();
    }
    public boolean placeFollower(Follower follower) {
        return false;
    }
}
