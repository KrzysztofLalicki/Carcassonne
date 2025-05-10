package app.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Box {
    private final ArrayList<Tile> tiles;
    public Box(){
        tiles = new ArrayList<>();
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get("tiles.txt"));
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        int[] tile = new int[6];
        for (String line : lines) {
            Scanner scanner = new Scanner(line);
            String image_code = scanner.next();
            for (int j = 0; j < 6; j++) {
                tile[j] = scanner.nextInt();
            }
            int tiles = scanner.nextInt();
            for (int j = 0; j < tiles; j++) {
                this.tiles.add(new Tile(tile, "/app/img/tiles/" + image_code + ".png"));
            }
        }
    }
    public Tile giveTile() {
        Random r = new Random();
        return tiles.remove(r.nextInt(tiles.size()));
    }
    public boolean isEmpty() {
        return tiles.isEmpty();
    }
}
