package app;

public class Tile {
    public enum Terrain{CITY, FIELD, ROAD}
    Terrain up, right, down, left;
    boolean cloister, pennant;
    private String image_path;
    public Tile(int[] terrain, String image_path) {
        up = terrain[0] == 0 ? Terrain.CITY : terrain[0] == 1 ? Terrain.FIELD : Terrain.ROAD;
        right = terrain[1] == 0 ? Terrain.CITY : terrain[1] == 1 ? Terrain.FIELD : Terrain.ROAD;
        down = terrain[2] == 0 ? Terrain.CITY : terrain[2] == 1 ? Terrain.FIELD : Terrain.ROAD;
        left = terrain[3] == 0 ? Terrain.CITY : terrain[3] == 1 ? Terrain.FIELD : Terrain.ROAD;
        cloister = terrain[4] == 1;
        pennant = terrain[5] == 1;
        this.image_path = image_path;
    }
    public void rotate_left() {
        Terrain temp = up;
        up = right;
        right = down;
        down = left;
        left = temp;
    }
    public void rotate_right() {
        Terrain temp = up;
        up = left;
        left = down;
        down = right;
        right = temp;
    }
    public String get_image_path() {
        return image_path;
    }
}
