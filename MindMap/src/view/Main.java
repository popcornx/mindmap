package view;

import controller.GuiController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class Main extends Application {
    public static GuiController controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/sample.fxml"));
        Parent root = loader.load();
        controller = (GuiController)loader.getController();
        primaryStage.setTitle("MindMap");
        primaryStage.setScene(new Scene(root, 1024, 1024));
        primaryStage.show();

        //This should not be here...
        root.setOnKeyPressed(e->{
            if(e.getCode().equals(KeyCode.DELETE)){
                controller.deleteNode();
            }
        });
    }
    public static void main(String[] args) {

        launch(args);

    }
}
