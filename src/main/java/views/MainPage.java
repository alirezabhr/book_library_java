package views;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import views.entities_view.StudentView;

public class MainPage {
    public static Scene getMainScene() {
        TabPane tabPane = new TabPane();

        Tab studentTab = StudentView.getTab();
        Tab bookTab = new Tab("Books"  , new Label("Show all books"));
        Tab recordTab = new Tab("Records" , new Label("Show all records"));

        tabPane.getTabs().add(studentTab);
        tabPane.getTabs().add(bookTab);
        tabPane.getTabs().add(recordTab);

        VBox vBox = new VBox(tabPane);

        return new Scene(vBox, 900, 550);
    }
}
