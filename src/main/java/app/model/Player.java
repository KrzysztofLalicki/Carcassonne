package app.model;

import javafx.scene.paint.Color;

public class Player {
    private String name;
    private Color color;
    private int points;
    public Player(String name) {
        this.name = name;
        points = 0;
    }
    public void rename(String name) {
        this.name = name;
    }
    public void add_points(int points) {
        this.points += points;
    }
    public void set_color(Color color) {
        this.color = color;
    }
    public String get_name() {
        return name;
    }
    public Color get_color() {
        return color;
    }
    public int get_points() {
        return points;
    }
}
