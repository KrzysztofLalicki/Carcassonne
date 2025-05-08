public class Player {
    String name;
    int points;
    Player(String name) {
        this.name = name;
        points = 0;
    }
    void rename(String name) {
        this.name = name;
    }
}
