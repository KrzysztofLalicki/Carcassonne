package app.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Box {
    private final ArrayList<Tile> tiles;
    private ObjectProperty<Tile> nextTile = new SimpleObjectProperty<>();

    public ObjectProperty<Tile> getNextTileProperty(){
        return nextTile;
    }

    public Box(){
        tiles = new ArrayList<>();
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get("src/main/resources/tiles.txt"));
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        short[] tile = new short[6];
        for (String line : lines) {
            Scanner scanner = new Scanner(line);
            String image_code = scanner.next();
            for (int j = 0; j < 6; j++) {
                tile[j] = scanner.nextShort();
            }
            int tiles = scanner.nextInt();
            for (int j = 0; j < tiles; j++) {
                this.tiles.add(new Tile(tile, "/app/img/tiles/" + image_code + ".png"));
            }
        }
    }

    public void giveTile() {
        if (isEmpty()) {
            nextTile.set(null);
            return;
        }
        Random r = new Random();
        nextTile.set(tiles.remove(r.nextInt(tiles.size())));
    }
    public boolean isEmpty() {
        return tiles.isEmpty();
    }
}
