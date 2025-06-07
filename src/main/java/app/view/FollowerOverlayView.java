package app.view;

import app.utils.Position;
import app.viewmodels.FollowerOverlayViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static app.view.TileView.TILE_SIZE;

public class FollowerOverlayView extends GridPane {
    private ObjectProperty<Color> colorProperty;
    private ObjectProperty<Position> followerPositionProperty;

    public FollowerOverlayView(FollowerOverlayViewModel followerOverlayViewModel) {
        initView();
        colorProperty = followerOverlayViewModel.getColorProperty();
        followerPositionProperty = followerOverlayViewModel.getFollowerPositionProperty();

        colorProperty.addListener((_, _, _) -> updateView());
        followerPositionProperty.addListener((_, _, _) -> updateView());

        updateView();
    }

    private void updateView() {
        getChildren().clear();
        if(colorProperty.get() != null && followerPositionProperty.get() != null) {
            Circle circle = new Circle();
            circle.radiusProperty().bind(TILE_SIZE.divide(6).multiply(0.8));
            circle.setFill(colorProperty.get());
            add(circle, followerPositionProperty.get().x(), followerPositionProperty.get().y());
        }
    }

    private void initView() {
        minWidthProperty().bind(TILE_SIZE);
        prefWidthProperty().bind(TILE_SIZE);
        maxWidthProperty().bind(TILE_SIZE);

        minHeightProperty().bind(TILE_SIZE);
        prefHeightProperty().bind(TILE_SIZE);
        maxHeightProperty().bind(TILE_SIZE);


        setHgap(0);
        setVgap(0);
        setAlignment(Pos.CENTER);
        for (int i = 0; i < 3; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100.0 / 3);
            column.setFillWidth(true);
            getColumnConstraints().add(column);
        }
        for (int i = 0; i < 3; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / 3);
            row.setFillHeight(true);
            getRowConstraints().add(row);
        }
    }
}
