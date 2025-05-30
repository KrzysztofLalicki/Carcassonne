package app.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Tile {

    private Table table;
    private int x, y;
    short up, left, centre, right, down;
    boolean pennant;
    private final Segment[][] segments;

    private final String image_path;
    private final IntegerProperty rotation = new SimpleIntegerProperty(0);

    public Integer getRotation() {
        return rotation.get();
    }

    public void setRotation(Integer rotation) {
        this.rotation.set(rotation);
    }

    public IntegerProperty getRotationPropert() {
        return rotation;
    }


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

        setRotation((getRotation()+90)%360);
//        rotation = (rotation + 90) % 360;
    }
    public String getImagePath() {
        return image_path;
    }
    public void generateSegments() {
        if (centre == 1) {
            City city = new City();
            if (up == 1) {
                segments[0][1] = new Segment(city);
                if (table.getTiles()[x][y - 1] != null) {
                    city.mergeWith(table.getTiles()[x][y - 1].get().getSegments()[2][1].getArea());
                }
            }
            if (left == 1) {
                segments[1][0] = new Segment(city);
                if (table.getTiles()[x - 1][y] != null) {
                    city.mergeWith(table.getTiles()[x - 1][y].get().getSegments()[1][2].getArea());
                }
            }
            if (right == 1) {
                segments[1][2] = new Segment(city);
                if (table.getTiles()[x + 1][y] != null) {
                    city.mergeWith(table.getTiles()[x + 1][y].get().getSegments()[1][0].getArea());
                }
            }
            if (down == 1) {
                segments[2][1] = new Segment(city);
                if (table.getTiles()[x][y + 1] != null) {
                    city.mergeWith(table.getTiles()[x][y + 1].get().getSegments()[0][1].getArea());
                }
            }
        }
        else {
            if (up == 1) {
                City city = new City();
                segments[0][1] = new Segment(city);
                if (table.getTiles()[x][y - 1] != null) {
                    city.mergeWith(table.getTiles()[x][y - 1].get().getSegments()[2][1].getArea());
                }
            }
            if (left == 1) {
                City city = new City();
                segments[1][0] = new Segment(city);
                if (table.getTiles()[x - 1][y] != null) {
                    city.mergeWith(table.getTiles()[x - 1][y].get().getSegments()[1][2].getArea());
                }
            }
            if (right == 1) {
                City city = new City();
                segments[1][2] = new Segment(city);
                if (table.getTiles()[x + 1][y] != null) {
                    city.mergeWith(table.getTiles()[x + 1][y].get().getSegments()[1][0].getArea());
                }
            }
            if (down == 1) {
                City city = new City();
                segments[2][1] = new Segment(city);
                if (table.getTiles()[x][y + 1] != null) {
                    city.mergeWith(table.getTiles()[x][y + 1].get().getSegments()[0][1].getArea());
                }
            }
        }
        if (centre == 2) {
            if (up == 1) {
                Road road = new Road();
                segments[0][1] = new Segment(road);
                if (table.getTiles()[x][y - 1] != null) {
                    road.mergeWith(table.getTiles()[x][y - 1].get().getSegments()[2][1].getArea());
                }
            }
            if (left == 1) {
                Road road = new Road();
                segments[1][0] = new Segment(road);
                if (table.getTiles()[x - 1][y] != null) {
                    road.mergeWith(table.getTiles()[x - 1][y].get().getSegments()[1][2].getArea());
                }
            }
            if (right == 1) {
                Road road = new Road();
                segments[1][2] = new Segment(road);
                if (table.getTiles()[x + 1][y] != null) {
                    road.mergeWith(table.getTiles()[x + 1][y].get().getSegments()[1][0].getArea());
                }
            }
            if (down == 1) {
                Road road = new Road();
                segments[2][1] = new Segment(road);
                if (table.getTiles()[x][y + 1] != null) {
                    road.mergeWith(table.getTiles()[x][y + 1].get().getSegments()[0][1].getArea());
                }
            }
        }
        else {
            Road road = new Road();
            if (up == 1) {
                segments[0][1] = new Segment(road);
                if (table.getTiles()[x][y - 1] != null) {
                    road.mergeWith(table.getTiles()[x][y - 1].get().getSegments()[2][1].getArea());
                }
            }
            if (left == 1) {
                segments[1][0] = new Segment(road);
                if (table.getTiles()[x - 1][y] != null) {
                    road.mergeWith(table.getTiles()[x - 1][y].get().getSegments()[1][2].getArea());
                }
            }
            if (right == 1) {
                segments[1][2] = new Segment(road);
                if (table.getTiles()[x + 1][y] != null) {
                    road.mergeWith(table.getTiles()[x + 1][y].get().getSegments()[1][0].getArea());
                }
            }
            if (down == 1) {
                segments[2][1] = new Segment(road);
                if (table.getTiles()[x][y + 1] != null) {
                    road.mergeWith(table.getTiles()[x][y + 1].get().getSegments()[0][1].getArea());
                }
            }
        }
        if (centre == 3) {
            segments[1][1] = new Segment(new Cloister());
            segments[1][1].getArea().addTile(this);
        }
    }
    public void placeOnTable(Table table, int x, int y) {
        this.table = table;
        this.x = x;
        this.y = y;
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
