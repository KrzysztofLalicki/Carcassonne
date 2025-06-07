package app.view;

import app.utils.Position;
import app.viewmodels.TileViewModel;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class TileView extends StackPane {
    public final static String TILE_IMAGE_DIRECTORY = "/app/img/tiles/";
    public final static String TILE_IMAGE_EXTENSION = ".png";

    public static final IntegerProperty TILE_SIZE = new SimpleIntegerProperty(80);

    public TileView(TileViewModel tileViewModel) {
        minWidthProperty().bind(TILE_SIZE);
        prefWidthProperty().bind(TILE_SIZE);
        maxWidthProperty().bind(TILE_SIZE);

        minHeightProperty().bind(TILE_SIZE);
        prefHeightProperty().bind(TILE_SIZE);
        maxHeightProperty().bind(TILE_SIZE);

        String path = TILE_IMAGE_DIRECTORY + tileViewModel.getSymbol() + TILE_IMAGE_EXTENSION;
        Image image = new Image(getClass().getResource(path).toExternalForm());

        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(false);
        imageView.setSmooth(true);
        imageView.fitWidthProperty().bind(widthProperty());
        imageView.fitHeightProperty().bind(heightProperty());
        imageView.rotateProperty().bind(tileViewModel.getRotationProperty());

        FollowerOverlayView followerOverlayView = new FollowerOverlayView(tileViewModel.getFollowerOverlayViewModel());

        getChildren().add(imageView);
        getChildren().add(followerOverlayView);
    }
}