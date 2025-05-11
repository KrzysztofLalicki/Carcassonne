package app.view;

import app.model.Player;
import app.model.Tile;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SideBarView extends BorderPane {
    private Map<Player, Label> players = new HashMap<>();

    private Label titleLabel;
    private VBox playersBox;

    private TileView nextTileView;

    private Player prevSelected = null;

    public SideBarView(List<Player> players) {
        setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 3, 0, 0))));

        playersBox = new VBox(4);
        playersBox.setAlignment(Pos.TOP_LEFT);

        titleLabel = new Label("PLAYERS");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("Monospaced",14));
        titleLabel.setUnderline(true);
        playersBox.getChildren().add(titleLabel);

        for(Player p : players) {
            Label playerLabel = new Label(p.getName());
            playerLabel.setTextFill(Color.WHITE);
            titleLabel.setFont(Font.font("Monospaced"));
            playersBox.getChildren().add(playerLabel);
            this.players.put(p, playerLabel);
        }

        setTop(playersBox);

        nextTileView = new TileView();
        setBottom(nextTileView);
    }

    public void setNextTile(Tile nextTile) {
        try {
            nextTileView.setTileImage(new Image(getClass().getResource(nextTile.getImagePath()).toExternalForm()));
            nextTileView.setRotate(nextTile.getRotation());
        } catch(NullPointerException | IndexOutOfBoundsException e) {
            nextTileView.setTileImage(null);
        }
    }

    public void setNextTileOutline(TileView.Outline outline) {
        nextTileView.setOutline(outline);
    }

    public void selectPlayer(Player player) {
        if(prevSelected != null)
            players.get(prevSelected).setTextFill(Color.WHITE);
        players.get(player).setTextFill(Color.YELLOW);
        prevSelected = player;
    }

}