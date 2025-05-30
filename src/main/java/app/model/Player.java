package app.model;

import javafx.beans.property.*;
import javafx.scene.paint.Color;

public class Player {

    private final StringProperty name = new SimpleStringProperty();
    private ObjectProperty<Color> color = new SimpleObjectProperty<>();
    private IntegerProperty points = new SimpleIntegerProperty(0);
    private final Follower[] followers;

    public Player(String name, Color color) {
        this.name.set(name);
        this.color.set(color);
        followers = new Follower[7];
        for (Follower follower : followers) {
            follower = new Follower(this);
        }
    }

    /* PROBABLY USELESS
    public void rename(String name) {
        this.name.set(name);
    }
    */
    public String getName() {return name.get();}
    public StringProperty getNameProperty() {return name; }

    public void setColor(Color color) {
        this.color.set(color);
    }
    public Color getColor() {
        return this.color.get();
    }

    public void addPoints(int points) {
        this.points.add(points);
    }
    public int getPoints() {return points.get();}
    public IntegerProperty getPointsProperty() { return points; }

    public boolean hasFollower() {
        for (Follower follower : followers) {
            if (follower.isFree()) {
                return true;
            }
        }
        return false;
    }
    public Follower getFollower() {
        for (Follower follower : followers) {
            if (follower.isFree()) {
                return follower;
            }
        }
        return null;
    }

    public void doTurn(Tile tile, Table table) {}
}
