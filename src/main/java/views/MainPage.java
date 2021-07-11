package views;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import views.entities_view.BookView;
import views.entities_view.RecordView;
import views.entities_view.StudentView;

public class MainPage {
    public static Scene getMainScene() {
        TabPane tabPane = new TabPane();

        Tab studentTab = StudentView.getTab();
        Tab bookTab = BookView.getTab();
        Tab recordTab = RecordView.getTab();

        tabPane.getTabs().add(studentTab);
        tabPane.getTabs().add(bookTab);
        tabPane.getTabs().add(recordTab);

        VBox vBox = new VBox(tabPane);

        return new Scene(vBox, 900, 550);
    }
}
