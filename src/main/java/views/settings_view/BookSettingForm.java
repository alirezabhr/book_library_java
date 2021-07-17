package views.settings_view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import views.widgets.CustomTextField;

public class BookSettingForm extends BaseSettingForm {
    protected CustomTextField nameSizeField;
    protected CustomTextField authorSizeField;
    protected CustomTextField publisherSizeField;

    // constructors
    public BookSettingForm() {
        this.formTitle = "<Book Config Settings>";
    }

    // methods
    protected HBox getMainRow() {
        VBox nameCol = this.getNameCol();
        VBox authorCol = this.getAuthorCol();
        VBox publisherCol = this.getPublisherCol();

        HBox mainRow = new HBox(nameCol, authorCol, publisherCol);
        mainRow.setSpacing(80);
        mainRow.setPadding(new Insets(20));
        return mainRow;
    }
    private VBox getNameCol() {
        Label nameLabel = new Label("Book Name Size: ");
        this.nameSizeField = new CustomTextField("name size...");

        VBox nameCol = new VBox(nameLabel, this.nameSizeField);
        nameCol.setSpacing(5);
        return nameCol;
    }
    private VBox getAuthorCol() {
        Label authorLabel = new Label("Book Author Size: ");
        this.authorSizeField = new CustomTextField("author size...");

        VBox authorCol = new VBox(authorLabel, this.authorSizeField);
        authorCol.setSpacing(5);
        return authorCol;
    }
    private VBox getPublisherCol() {
        Label publisherLabel = new Label("Book Publisher Size: ");
        this.publisherSizeField = new CustomTextField("publisher size...");

        VBox publisherCol = new VBox(publisherLabel, this.publisherSizeField);
        publisherCol.setSpacing(5);
        return publisherCol;
    }
}