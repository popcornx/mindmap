package view;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application which starts the Application and passes
 * its Information to the Main Controller
 */
public class Main extends Application {
    public static MainController mainController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXML/Main.fxml"));
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
