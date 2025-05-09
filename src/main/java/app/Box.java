package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Box {
    ArrayList<Tile> tiles;
    public Box() throws FileNotFoundException {
        tiles = new ArrayList<>();
        String file = "tiles.txt";
        Scanner scanner = new Scanner(new File(file));
        int[] tile = new int[6];
        while(scanner.hasNext()) {
            for (int i = 0; i < 6; i++) {
                tile[i] = scanner.nextInt();
            }
            int tiles = scanner.nextInt();
            for (int i = 0; i < tiles; i++) {
                this.tiles.add(new Tile(tile));
            }
        }
    }
    public void add_tile(Tile tile) {
        tiles.add(tile);
    }
    public Tile give_tile() {
        Random r = new Random();
        return tiles.remove(r.nextInt(tiles.size()));
    }
}
