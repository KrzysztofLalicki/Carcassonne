package app.view;

import app.viewmodels.PlayerViewModel;
import app.viewmodels.SideBarViewModel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SideBarView extends BorderPane {

    public SideBarView(SideBarViewModel viewModel) {
        setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(0, 3, 0, 0))));

        VBox playersBox = new VBox(4);
        playersBox.setAlignment(Pos.TOP_LEFT);

        Label titleLabel = new Label("PLAYERS");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("Monospaced", 14));
        titleLabel.setUnderline(true);
        playersBox.getChildren().add(titleLabel);

        for (PlayerViewModel pvm : viewModel.getPlayersViewModels()) {
            PlayerView playerView = new PlayerView(pvm);
            playersBox.getChildren().add(playerView);
        }

        setTop(playersBox);
    }
}