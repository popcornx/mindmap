package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.Map;
import model.Node;

public class MainController {
    private Map map = new Map();
    private Stage primaryStage;
    private Parent root;
    @FXML private MenubarController menubarController;
    @FXML private ToolbarController toolbarController;
    @FXML private CanvasController canvasController;
    /**
     * Injects the MainController into the other Controllers
     */
    @FXML private void initialize() {
        menubarController.setMainController(this);
        toolbarController.setMainController(this);
        canvasController.setMainController(this);
    }

    /**
     * @return Returns the menubarController
     */
    MenubarController getMenubarController(){
        return menubarController;
    }
    /**
     * @return Returns the toolbarController
     */
    ToolbarController getToolbarController(){
        return  toolbarController;
    }
    /**
     * @return Returns the canvasController
     */
    public CanvasController getCanvasController(){
        return  canvasController;
    }
    /**
     * @return Returns the current Map
     */
    public Map getMap() {
        return map;
    }

    /**
     * @param m Sets the current Map after loading
     */
    void setMap(Map m){
        this.map = m;
    }

    /**
     * deletes the currently selected connection
     */
    private void deleteConnection(){
        map.getConnections().remove(canvasController.getSelectedConnection());
        canvasController.deleteConnection();
    }

    /**
     * deletes the currently selected Node
     */
    private void deleteNode(){
        map.getNodes().remove(canvasController.getSelectedNode());
        canvasController.deleteNode();
    }

    /**
     * @param root sets the Parent from the Main.class,
     * and adds the delete Function to it.
     */
    public void setRoot(Parent root){
        this.root = root;
        root.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.DELETE)) {
                deleteNode();
                deleteConnection();
            }
        });
    }

    /**
     * @param primaryStage sets the Stage from the Main.class,
     * and adds the Observer for the Window size.
     */
    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                for (Node node : map.getNodes()) {
                    node.setBorderWidth(primaryStage.widthProperty().getValue());
                }
            }
        });
        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                for (Node node : map.getNodes()) {
                    node.setBorderHeight(primaryStage.heightProperty().getValue() - 100);
                }
            }
        });
    }
}