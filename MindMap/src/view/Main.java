package view;

import controller.MainController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Main extends Application {
    public static MainController mainController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Main.fxml"));
        Parent root = loader.load();
        mainController = loader.getController();
        mainController.setStage(primaryStage);
        mainController.setRoot(root);
        primaryStage.setTitle("MindMap");
        primaryStage.setScene(new Scene(root, 1024, 1024));
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
    }
