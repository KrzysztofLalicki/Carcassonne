package app.model;

import app.utils.Position;
import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private final Game game;
    private Table table;
    private int x, y;
    short up, left, centre, right, down;
    boolean pennant;
    private final Segment[][] segments;
    private final StringProperty symbol = new SimpleStringProperty();
    private final IntegerProperty rotation = new SimpleIntegerProperty();
    private Follower follower;

    public int getRotation() {
        return rotation.get();
    }

    public String getSymbol() {
        return symbol.get();
    }

    public Tile(short[] terrain, String symbol, Game game) {
        this.game = game;
        up = terrain[0];
        left = terrain[1];
        centre = terrain[2];
        right = terrain[3];
        down = terrain[4];
        pennant = terrain[5] == 1;
        this.symbol.set(symbol);
        rotation.set(0);
        segments = new Segment[3][3];
    }

    public void rotate() {
        short temp = up;
        up = left;
        left = down;
        down = right;
        right = temp;
        rotation.set((rotation.get() + 90) % 360);
        notifyOnRotationChangedListeners();
    }
    public StringProperty getSymbolProperty() {
        return symbol;
    }
    public IntegerProperty getRotationProperty() {
        return rotation;
    }
    public void generateSegments() {
        if (left != 1 || centre != 1 || up != 1) {
            Field field = new Field(this);
            segments[0][0] = new Segment(field);
            if (left != 1 && table.getTile(x - 1, y) != null) {
                table.getTile(x - 1, y).getSegments()[2][0].getArea().mergeWith(field);
            }
            if (up != 1 && table.getTile(x, y - 1) != null) {
                table.getTile(x, y - 1).getSegments()[0][2].getArea().mergeWith(field);
            }
        }
        if (up != 1 || centre != 1 || right != 1) {
            Field field = new Field(this);
            segments[2][0] = new Segment(field);
            if (up != 1 && table.getTile(x, y - 1) != null) {
                table.getTile(x, y - 1).getSegments()[2][2].getArea().mergeWith(field);
            }
            if (right != 1 && table.getTile(x + 1, y) != null) {
                table.getTile(x + 1, y).getSegments()[0][0].getArea().mergeWith(field);
            }
        }
        if (left != 1 || centre != 1 || down != 1) {
            Field field = new Field(this);
            segments[0][2] = new Segment(field);
            if (left != 1 && table.getTile(x - 1, y) != null) {
                table.getTile(x - 1, y).getSegments()[2][2].getArea().mergeWith(field);
            }
            if (down != 1 && table.getTile(x, y + 1) != null) {
                table.getTile(x, y + 1).getSegments()[0][0].getArea().mergeWith(field);
            }
        }
        if (down != 1 || centre != 1 || right != 1) {
            Field field = new Field(this);
            segments[2][2] = new Segment(field);
            if (down != 1 && table.getTile(x, y + 1) != null) {
                table.getTile(x, y + 1).getSegments()[2][0].getArea().mergeWith(field);
            }
            if (right != 1 && table.getTile(x + 1, y) != null) {
                table.getTile(x + 1, y).getSegments()[0][2].getArea().mergeWith(field);
            }
        }
        if (up == 0 || (up == 1 && centre != 1)) {
            segments[0][0].getArea().mergeWith(segments[2][0].getArea());
        }
        if (left == 0 || (left == 1 && centre != 1)) {
            segments[0][0].getArea().mergeWith(segments[0][2].getArea());
        }
        if (right == 0 || (right == 1 && centre != 1)) {
            segments[2][0].getArea().mergeWith(segments[2][2].getArea());
        }
        if (down == 0 || (down == 1 && centre != 1)) {
            segments[0][2].getArea().mergeWith(segments[2][2].getArea());
        }
        if (centre == 1) {
            City city = new City(this);
            if (up == 1) {
                segments[1][0] = new Segment(city);
                if (table.getTile(x, y - 1) != null) {
                    city.mergeWith(table.getTile(x, y - 1).getSegments()[1][2].getArea());
                }
            }
            if (left == 1) {
                segments[0][1] = new Segment(city);
                if (table.getTile(x - 1, y) != null) {
                    city.mergeWith(table.getTile(x - 1, y).getSegments()[2][1].getArea());
                }
            }
            if (right == 1) {
                segments[2][1] = new Segment(city);
                if (table.getTile(x + 1, y) != null) {
                    city.mergeWith(table.getTile(x + 1, y).getSegments()[0][1].getArea());
                }
            }
            if (down == 1) {
                segments[1][2] = new Segment(city);
                if (table.getTile(x, y + 1) != null) {
                    city.mergeWith(table.getTile(x, y + 1).getSegments()[1][0].getArea());
                }
            }
        }
        else {
            if (up == 1) {
                City city = new City(this);
                segments[1][0] = new Segment(city);
                if (table.getTile(x, y - 1) != null) {
                    table.getTile(x, y - 1).getSegments()[1][2].getArea().mergeWith(city);
                }
            }
            if (left == 1) {
                City city = new City(this);
                segments[0][1] = new Segment(city);
                if (table.getTile(x - 1, y) != null) {
                    table.getTile(x - 1, y).getSegments()[2][1].getArea().mergeWith(city);
                }
            }
            if (right == 1) {
                City city = new City(this);
                segments[2][1] = new Segment(city);
                if (table.getTile(x + 1, y) != null) {
                    table.getTile(x + 1, y).getSegments()[0][1].getArea().mergeWith(city);
                }
            }
            if (down == 1) {
                City city = new City(this);
                segments[1][2] = new Segment(city);
                if (table.getTile(x, y + 1) != null) {
                    table.getTile(x, y + 1).getSegments()[1][0].getArea().mergeWith(city);
                }
            }
        }
        if (centre > 1) {
            if (up == 2) {
                Road road = new Road(this);
                road.end();
                segments[1][0] = new Segment(road);
                if (table.getTile(x, y - 1) != null) {
                    table.getTile(x, y - 1).getSegments()[1][2].getArea().mergeWith(road);
                }
            }
            if (left == 2) {
                Road road = new Road(this);
                road.end();
                segments[0][1] = new Segment(road);
                if (table.getTile(x - 1, y) != null) {
                    table.getTile(x - 1, y).getSegments()[2][1].getArea().mergeWith(road);
                }
            }
            if (right == 2) {
                Road road = new Road(this);
                road.end();
                segments[2][1] = new Segment(road);
                if (table.getTile(x + 1, y) != null) {
                    table.getTile(x + 1, y).getSegments()[0][1].getArea().mergeWith(road);
                }
            }
            if (down == 2) {
                Road road = new Road(this);
                road.end();
                segments[1][2] = new Segment(road);
                if (table.getTile(x, y + 1) != null) {
                    table.getTile(x, y + 1).getSegments()[1][0].getArea().mergeWith(road);
                }
            }
        }
        else {
            Road road = new Road(this);
            if (up == 2) {
                segments[1][0] = new Segment(road);
                if (table.getTile(x, y - 1) != null) {
                    road.mergeWith(table.getTile(x, y - 1).getSegments()[1][2].getArea());
                }
            }
            if (left == 2) {
                segments[0][1] = new Segment(road);
                if (table.getTile(x - 1, y) != null) {
                    road.mergeWith(table.getTile(x - 1, y).getSegments()[2][1].getArea());
                }
            }
            if (right == 2) {
                segments[2][1] = new Segment(road);
                if (table.getTile(x + 1, y) != null) {
                    road.mergeWith(table.getTile(x + 1, y).getSegments()[0][1].getArea());
                }
            }
            if (down == 2) {
                segments[1][2] = new Segment(road);
                if (table.getTile(x, y + 1) != null) {
                    road.mergeWith(table.getTile(x, y + 1).getSegments()[1][0].getArea());
                }
            }
        }
        if (centre == 3) {
            segments[1][1] = new Segment(new Cloister(this));
        }
    }
    public Segment[][] getSegments() {
        return segments;
    }
    public void count_points() {
        for (Segment[] ss : segments) {
            for (Segment s : ss) {
                if (s != null && s.getArea().isFinished()) {
                    s.getArea().finish();
                }
            }
        }
    }
    public void placeOnTable(Table table, int x, int y) {
        this.table = table;
        this.x = x;
        this.y = y;
    }
    public boolean canPlace(int x, int y) {
        return segments[x][y] != null && segments[x][y].getArea().isFree();
    }

    public Position getOnTablePosition() {
        return new Position(x, y);
    }

    /**
     * Adds follower to this tile
     * @param follower newly placed follower or {code @null} if follower placement was skipped
     */
    public void placeFollower(Follower follower, Position position) {
        if(follower != null && position != null) {
            follower.placeOnTile(this, position.x(), position.y());
            this.follower = follower;
            follower.getPlayer().notifyOnFollowerNumberChangeListeners();
            notifyOnFollowerChangedListeners();
        }
        game.nextPlayer();
    }

    public Follower getFollower() {
        return follower;
    }

    private final List<TileChangeListener> onTileChangedListeners = new ArrayList<>();
    public void addOnTileChangedListener(TileChangeListener onTileChangedListener) {
        onTileChangedListeners.add(onTileChangedListener);
    }
    public void removeOnTileChangedListener(TileChangeListener onTileChangedListener) {
        onTileChangedListeners.remove(onTileChangedListener);
    }
    public void notifyOnRotationChangedListeners() {
        for(TileChangeListener listener : onTileChangedListeners) {
            listener.onRotationChanged();
        }
    }
    public void notifyOnFollowerChangedListeners() {
        for(TileChangeListener listener : onTileChangedListeners)
            listener.onFollowerChanged();
    }
}
