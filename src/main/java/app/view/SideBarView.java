package app.view;

import app.model.Tile;
import app.viewmodels.PlayerViewModel;
import app.viewmodels.SideBarViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SideBarView extends BorderPane {
    private StackPane tilePreview;

    private BooleanProperty isActive = new SimpleBooleanProperty(false);
    private ObjectProperty<Tile> onViewTile  = new SimpleObjectProperty<>();
    private ObjectProperty<BoardSelector.Outline> outlineProperty = new SimpleObjectProperty<>(BoardSelector.Outline.NONE);

    public SideBarView(SideBarViewModel viewModel) {
        isActive = viewModel.getIsActive();
        onViewTile = viewModel.getOnViewTile();
        outlineProperty = viewModel.getOutlineProperty();

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
        setBottom(tilePreview);

        isActive.addListener((_, _, _) -> updateTilePreview());
        onViewTile.addListener((_, _, _) -> updateTilePreview());
        outlineProperty.addListener((_, _, _) -> updateTilePreview());

        updateTilePreview();
    }

    public void updateTilePreview() {
        getChildren().remove(tilePreview);
        if(isActive.get()) {
            tilePreview = new StackPane(BoardSelector.selectedTileView(onViewTile.get(), outlineProperty.get()));
            tilePreview.setAlignment(Pos.CENTER);
            setBottom(tilePreview);
        }
    }
}