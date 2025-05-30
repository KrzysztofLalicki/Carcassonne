package app.model;

public class Tile {

    private Table table;
    private int x, y;
    short up, left, centre, right, down;
    boolean pennant;
    private final Segment[][] segments;
    private final String symbol;
    private Integer rotation = 0;

    public Tile(short[] terrain, String symbol) {
        up = terrain[0];
        left = terrain[1];
        centre = terrain[2];
        right = terrain[3];
        down = terrain[4];
        pennant = terrain[5] == 1;
        this.symbol = symbol;
        segments = new Segment[3][3];
    }

    public void rotate() {
        short temp = up;
        up = left;
        left = down;
        down = right;
        right = temp;
        rotation = (rotation + 90) % 360;
    }
    public String getSymbol() {
        return symbol;
    }
    public Integer getRotation() {
        return rotation;
    }
    public void placeOnTable(Table table, int x, int y) {
        this.table = table;
        this.x = x;
        this.y = y;
    }
    public void generateSegments() {
        if (left != 1 || up != 1) {
            Field field = new Field(this);
            segments[0][0] = new Segment(field);
            if (left != 1 && table.getTiles()[x - 1][y] != null) {
                table.getTiles()[x - 1][y].getSegments()[2][0].getArea().mergeWith(field);
            }
            if (up != 1 && table.getTiles()[x][y - 1] != null) {
                table.getTiles()[x][y - 1].getSegments()[0][2].getArea().mergeWith(field);
            }
        }
        if (up != 1 || right != 1) {
            Field field = new Field(this);
            segments[2][0] = new Segment(new Field(this));
            if (up != 1 && table.getTiles()[x][y - 1] != null) {
                table.getTiles()[x][y - 1].getSegments()[2][2].getArea().mergeWith(field);
            }
            if (right != 1 && table.getTiles()[x + 1][y] != null) {
                table.getTiles()[x + 1][y].getSegments()[0][0].getArea().mergeWith(field);
            }
        }
        if (left != 1 || down != 1) {
            Field field = new Field(this);
            segments[0][2] = new Segment(new Field(this));
            if (left != 1 && table.getTiles()[x - 1][y] != null) {
                table.getTiles()[x - 1][y].getSegments()[2][2].getArea().mergeWith(field);
            }
            if (down != 1 && table.getTiles()[x][y + 1] != null) {
                table.getTiles()[x][y + 1].getSegments()[0][0].getArea().mergeWith(field);
            }
        }
        if (down != 1 || right != 1) {
            Field field = new Field(this);
            segments[2][2] = new Segment(new Field(this));
            if (down != 1 && table.getTiles()[x][y + 1] != null) {
                table.getTiles()[x][y + 1].getSegments()[2][0].getArea().mergeWith(field);
            }
            if (right != 1 && table.getTiles()[x + 1][y] != null) {
                table.getTiles()[x + 1][y].getSegments()[0][2].getArea().mergeWith(field);
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
                if (table.getTiles()[x][y - 1] != null) {
                    table.getTiles()[x][y - 1].getSegments()[1][2].getArea().mergeWith(city);
                }
            }
            if (left == 1) {
                segments[0][1] = new Segment(city);
                if (table.getTiles()[x - 1][y] != null) {
                    table.getTiles()[x - 1][y].getSegments()[2][1].getArea().mergeWith(city);
                }
            }
            if (right == 1) {
                segments[2][1] = new Segment(city);
                if (table.getTiles()[x + 1][y] != null) {
                    table.getTiles()[x + 1][y].getSegments()[0][1].getArea().mergeWith(city);
                }
            }
            if (down == 1) {
                segments[1][2] = new Segment(city);
                if (table.getTiles()[x][y + 1] != null) {
                    table.getTiles()[x][y + 1].getSegments()[1][0].getArea().mergeWith(city);
                }
            }
        }
        else {
            if (up == 1) {
                City city = new City(this);
                segments[1][0] = new Segment(city);
                if (table.getTiles()[x][y - 1] != null) {
                    table.getTiles()[x][y - 1].getSegments()[1][2].getArea().mergeWith(city);
                }
            }
            if (left == 1) {
                City city = new City(this);
                segments[0][1] = new Segment(city);
                if (table.getTiles()[x - 1][y] != null) {
                    table.getTiles()[x - 1][y].getSegments()[2][1].getArea().mergeWith(city);
                }
            }
            if (right == 1) {
                City city = new City(this);
                segments[2][1] = new Segment(city);
                if (table.getTiles()[x + 1][y] != null) {
                    table.getTiles()[x + 1][y].getSegments()[0][1].getArea().mergeWith(city);
                }
            }
            if (down == 1) {
                City city = new City(this);
                segments[1][2] = new Segment(city);
                if (table.getTiles()[x][y + 1] != null) {
                    table.getTiles()[x][y + 1].getSegments()[1][0].getArea().mergeWith(city);
                }
            }
        }
        if (centre == 2) {
            if (up == 2) {
                Road road = new Road(this);
                segments[1][0] = new Segment(road);
                if (table.getTiles()[x][y - 1] != null) {
                    table.getTiles()[x][y - 1].getSegments()[1][2].getArea().mergeWith(road);
                }
            }
            if (left == 2) {
                Road road = new Road(this);
                segments[0][1] = new Segment(road);
                if (table.getTiles()[x - 1][y] != null) {
                    table.getTiles()[x - 1][y].getSegments()[2][1].getArea().mergeWith(road);
                }
            }
            if (right == 2) {
                Road road = new Road(this);
                segments[2][1] = new Segment(road);
                if (table.getTiles()[x + 1][y] != null) {
                    table.getTiles()[x + 1][y].getSegments()[0][1].getArea().mergeWith(road);
                }
            }
            if (down == 2) {
                Road road = new Road(this);
                segments[1][2] = new Segment(road);
                if (table.getTiles()[x][y + 1] != null) {
                    table.getTiles()[x][y + 1].getSegments()[1][0].getArea().mergeWith(road);
                }
            }
        }
        else {
            Road road = new Road(this);
            if (up == 2) {
                segments[1][0] = new Segment(road);
                if (table.getTiles()[x][y - 1] != null) {
                    table.getTiles()[x][y - 1].getSegments()[1][2].getArea().mergeWith(road);
                }
            }
            if (left == 2) {
                segments[0][1] = new Segment(road);
                if (table.getTiles()[x - 1][y] != null) {
                    table.getTiles()[x - 1][y].getSegments()[2][1].getArea().mergeWith(road);
                }
            }
            if (right == 2) {
                segments[2][1] = new Segment(road);
                if (table.getTiles()[x + 1][y] != null) {
                    table.getTiles()[x + 1][y].getSegments()[0][1].getArea().mergeWith(road);
                }
            }
            if (down == 2) {
                segments[1][2] = new Segment(road);
                if (table.getTiles()[x][y + 1] != null) {
                    table.getTiles()[x][y + 1].getSegments()[1][0].getArea().mergeWith(road);
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
    public boolean canPlace(int x, int y) {
        return segments[x][y] != null && segments[x][y].getArea().isFree();
    }
    public boolean placeFollower(Follower follower) {
        return false;
    }
}
