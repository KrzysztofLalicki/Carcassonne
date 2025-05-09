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
    boolean selected = false;

    Rectangle selectionRectangle = new Rectangle(100, 100);
    {
        selectionRectangle.setFill(Color.TRANSPARENT);
        selectionRectangle.setStroke(Color.TRANSPARENT);
        selectionRectangle.setStrokeWidth(5);
        selectionRectangle.setStrokeType(StrokeType.INSIDE);
    }

    public TileView() {
        setMinSize(100, 100);
        setMaxSize(100, 100);

        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        tileImage = new ImageView();
        tileImage.setFitWidth(100);
        tileImage.setFitHeight(100);
        tileImage.setPreserveRatio(true);
        tileImage.setSmooth(true);

        getChildren().add(tileImage);
        getChildren().add(selectionRectangle);
    }

    public void setTileImage(Image image) {
        tileImage.setImage(image);
    }

    public void rotate() {
        tileImage.setRotate(tileImage.getRotate() + 90);
    }

    //select/unselect rectangle
    public boolean select() {
        if(selected) {
            selectionRectangle.setStroke(Color.TRANSPARENT);
            selected = false;
            return false;
        }
        selectionRectangle.setStroke(Color.RED);
        selected = true;
        return false;
    }

    public boolean isEmpty() {
        return tileImage.getImage() == null;
    }
}
