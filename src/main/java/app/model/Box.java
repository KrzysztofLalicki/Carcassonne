package app.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Box {
    private static final String TILE_SET_PATH = "src/main/resources/tiles.txt";
    public final static int NUMBER_OF_TILES = 72;

    private final ArrayList<Tile> tiles;

    public Box(Game game){

        tiles = new ArrayList<>();
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(TILE_SET_PATH));
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        short[] tile = new short[6];
        for (String line : lines) {
            Scanner scanner = new Scanner(line);
            String symbol = scanner.next();
            for (int j = 0; j < 6; j++) {
                tile[j] = scanner.nextShort();
            }
            int tiles = scanner.nextInt();
            for (int j = 0; j < tiles; j++) {
                this.tiles.add(new Tile(tile, symbol, game));
            }
        }
    }

    public Tile giveTile() {
        if (tiles.isEmpty()) {
            return null;
        }
        Random r = new Random();
        return tiles.remove(r.nextInt(tiles.size()));
    }

    public void takeTiles(ArrayList<Tile> tiles) {
        this.tiles.addAll(tiles);
    }
    public boolean isEmpty() {
        return tiles.isEmpty();
    }
}