import java.util.ArrayList;
import java.util.Random;

public class Box {
    ArrayList<Tile> tiles;
    public Box() {
        tiles = new ArrayList<>();
    }
    public void add_tile(Tile tile) {
        tiles.add(tile);
    }
    public Tile give_tile() {
        Random r = new Random();
        return tiles.remove(r.nextInt(tiles.size()));
    }
}
