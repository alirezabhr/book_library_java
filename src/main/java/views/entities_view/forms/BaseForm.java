package views.entities_view.forms;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import constant.MyConst;

public abstract class BaseForm {

    // abstract methods
    public abstract VBox createFormDetail();
    public abstract VBox editFormDetail();
    public abstract VBox deleteFormDetail();
    protected abstract void createObject(Object... objects);
    protected abstract void editObject(int uniqueId, Object... objects);
    protected abstract void deleteObject(int uniqueId);

    // methods
    public void showForm(String formTitle, VBox root) {
        Scene scene = new Scene(root);
        Stage formStage = new Stage();
        formStage.setScene(scene);
        formStage.setTitle(formTitle);
        formStage.setHeight(450);
        formStage.setWidth(320);
        formStage.getIcons().add(new Image(MyConst.constAppIconPathName));
        formStage.show();
    }
}
