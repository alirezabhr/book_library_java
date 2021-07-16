import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import constant.MyConst;
import views.MainPage;
import controller.utils;

import java.util.Objects;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            utils.getAllData();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return;
        }

        Scene scene = MainPage.getMainScene();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("stylesheet.css")).toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(650);
        primaryStage.setTitle("Library Program");
        primaryStage.getIcons().add(new Image(MyConst.constAppIconPathName));

        try {
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("in exception");
            e.printStackTrace();
        }
    }

}