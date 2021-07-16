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

        Tab studentTab = new StudentView().getTab("Student");
        Tab bookTab = new BookView().getTab("Book");
        Tab recordTab = new RecordView().getTab("Record");

        tabPane.getTabs().add(studentTab);
        tabPane.getTabs().add(bookTab);
        tabPane.getTabs().add(recordTab);
        studentTab.setClosable(false);
        bookTab.setClosable(false);
        recordTab.setClosable(false);

        VBox vBox = new VBox(tabPane);

        return new Scene(vBox, 900, 550);
    }
}
