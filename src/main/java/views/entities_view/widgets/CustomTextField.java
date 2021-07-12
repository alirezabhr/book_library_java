package views.entities_view.widgets;

import javafx.scene.control.TextField;

public class CustomTextField extends TextField {
    public CustomTextField(String hint) {
        super();
        this.setPromptText(hint);
        this.setFocusTraversable(false);
        this.setMaxWidth(150);
    }

    public CustomTextField(String hint, String defaultValue) {
        super(defaultValue);
        this.setPromptText(hint);
        this.setFocusTraversable(false);
        this.setMaxWidth(150);
    }
}
