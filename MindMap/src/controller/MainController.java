package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Connection;
import model.Map;
import model.Node;

public class MainController {
    private Map map = new Map();
    private Stage primaryStage;
    private Parent root;
    @FXML private MenubarController menubarController;
    @FXML private ToolbarController toolbarController;
    @FXML private CanvasController canvasController;

    @FXML private void initialize() {
        menubarController.setMainController(this);
        toolbarController.setMainController(this);
        canvasController.setMainController(this);
    }

    public Map getMap() {
        return map;
    }
    public void setMap(Map m){
        this.map = m;
    }
    public Color getColor(){
        return toolbarController.getColor();
    }
    public boolean btnNodeSelected(){
        return toolbarController.btnNodeSelected();
    }
    public boolean btnConnectionSelected(){
        return toolbarController.btnConnectionSelected();
    }
    public boolean btnSubNodeSelected(){
        return toolbarController.btnSubNodeSelected();
    }

    public Node getSelectedNode(){
        return canvasController.getSelectedNode();
    }
    public void nodeSelected(Node node){
        canvasController.nodeSelected(node);
    }
    public Connection getSelectedConnection() {
        return canvasController.getSelectedConnection();
    }
    public void connectionSelected(Connection connection){
        canvasController.connectionSelected(connection);
    }
    public void deleteConnection(){
        map.getConnections().remove(canvasController.getSelectedConnection());
        canvasController.deleteConnection();
    }
    public void deleteNode(){
        map.getNodes().remove(canvasController.getSelectedNode());
        canvasController.deleteNode();
    }

    public void setRoot(Parent root){
        this.root = root;
        root.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.DELETE)) {
                deleteNode();
                deleteConnection();
            }
        });
    }
    
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

    public void drawMap(){
        canvasController.drawMap();
    }
}