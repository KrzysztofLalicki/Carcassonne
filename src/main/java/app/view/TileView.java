package app.view;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class TileView extends StackPane {
    ImageView tileImage;

    Rectangle selectionRectangle = new Rectangle(60, 60);
    {
        selectionRectangle.setFill(Color.TRANSPARENT);
        selectionRectangle.setStroke(Color.TRANSPARENT);
        selectionRectangle.setStrokeWidth(5);
        selectionRectangle.setStrokeType(StrokeType.INSIDE);
    }

    public void setOutline(Outline outline) {
        selectionRectangle.setStroke(outline.color);
    }

    public enum Outline {
        NONE(Color.TRANSPARENT),
        RED(Color.RED),
        GREEN(Color.GREEN);

        public final Color color;
        Outline(Color color) {
            this.color = color;
        }
    }

    public TileView() {
        setMinSize(60, 60);
        setMaxSize(60, 60);

        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        tileImage = new ImageView();
        tileImage.setFitWidth(60);
        tileImage.setFitHeight(60);
        tileImage.setPreserveRatio(true);
        tileImage.setSmooth(true);

        getChildren().add(tileImage);
        getChildren().add(selectionRectangle);
    }

    public void setTileImage(Image image) {
        tileImage.setImage(image);
    }

    public void setRotate(int rotation) {
        tileImage.setRotate(rotation);
    }
}
