package views.widgets;

import constant.MyConst;
import javafx.scene.image.Image;

public class ButtonCreator {
    public static ImageButton getSearchButton() {
        Image searchIcon = new Image(MyConst.constSearchImagePathName);
        return new ImageButton("Search", searchIcon);
    }
    public static ImageButton getCreateButton() {
        Image createIcon = new Image(MyConst.constAddImagePathName);
        return new ImageButton("Create", createIcon);
    }
    public static ImageButton getEditButton() {
        Image editIcon = new Image(MyConst.constEditImagePathName);
        return new ImageButton("Edit", editIcon);
    }
    public static ImageButton getDeleteButton() {
        Image deleteIcon = new Image(MyConst.constDeleteImagePathName);
        return new ImageButton("Delete", deleteIcon);
    }
    public static ImageButton getRefreshButton() {
        Image refreshIcon = new Image(MyConst.constRefreshImagePathName);
        return new ImageButton("Refresh", refreshIcon);
    }
    public static ImageButton getSaveButton() {
        Image saveIcon = new Image(MyConst.constSaveImagePathName);
        return new ImageButton("Save", saveIcon);
    }
}
