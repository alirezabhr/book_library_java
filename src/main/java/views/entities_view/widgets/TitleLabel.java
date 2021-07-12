package views.entities_view.widgets;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TitleLabel extends Label {
    public TitleLabel(String text) {
        super(text);
        this.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    }
}
