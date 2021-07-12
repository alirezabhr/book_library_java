package views.widgets;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MessageLabel extends Label {
    public MessageLabel(String text, String color) {
        super(text);
        this.setTextFill(Color.valueOf(color));
        this.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    }
}
