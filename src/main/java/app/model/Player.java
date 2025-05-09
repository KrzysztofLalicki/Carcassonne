package app.model;

import javafx.scene.paint.Color;

public class Player {
    private String name;
    private Color color;
    private int points;
    Player(String name) {
        this.name = name;
        points = 0;
    }
    void rename(String name) {
        this.name = name;
    }
    void add_points(int points) {
        this.points += points;
    }
    void set_color(Color color) {
        this.color = color;
    }
    String get_name() {
        return name;
    }
    Color get_color() {
        return color;
    }
    int get_points() {
        return points;
    }
}
