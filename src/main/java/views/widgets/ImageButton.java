package views.widgets;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageButton extends Button {
    public ImageButton(String text, Image icon) {
        super(text, new ImageView(icon));
        this.setCursor(Cursor.HAND);
    }
}
