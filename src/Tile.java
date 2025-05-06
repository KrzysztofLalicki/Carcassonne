public class Tile {
    int[] terrain;
    public Tile(int[] terrain) {
        this.terrain = new int[5];
        System.arraycopy(terrain, 0, this.terrain, 0, 5);
    }
    public void rotate_left() {
        int temp = terrain[0];
        terrain[0] = terrain[1];
        terrain[1] = terrain[2];
        terrain[2] = terrain[3];
        terrain[3] = temp;
    }
    public void rotate_right() {
        int temp = terrain[3];
        terrain[3] = terrain[2];
        terrain[2] = terrain[1];
        terrain[1] = terrain[0];
        terrain[0] = temp;
    }
}
