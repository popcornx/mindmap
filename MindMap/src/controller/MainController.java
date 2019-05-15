package controller;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import model.Connection;
import model.Map;
import model.Node;

public class MainController {
    private Map map = new Map();

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
    public void drawMap(){
        canvasController.drawMap();
    }
}