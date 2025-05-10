package app.model;

public class Tile {
    public enum Terrain{CITY, FIELD, ROAD}
    Terrain up, right, down, left;
    boolean cloister, pennant;
    private final String image_path;
    private Integer rotation = 0;
    public Tile(int[] terrain, String image_path) {
        up = terrain[0] == 0 ? Terrain.CITY : terrain[0] == 1 ? Terrain.FIELD : Terrain.ROAD;
        right = terrain[1] == 0 ? Terrain.CITY : terrain[1] == 1 ? Terrain.FIELD : Terrain.ROAD;
        down = terrain[2] == 0 ? Terrain.CITY : terrain[2] == 1 ? Terrain.FIELD : Terrain.ROAD;
        left = terrain[3] == 0 ? Terrain.CITY : terrain[3] == 1 ? Terrain.FIELD : Terrain.ROAD;
        cloister = terrain[4] == 1;
        pennant = terrain[5] == 1;
        this.image_path = image_path;
    }
    public void rotate() {
        Terrain temp = up;
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
}
