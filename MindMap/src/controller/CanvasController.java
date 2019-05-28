package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.util.Pair;
import model.Connection;
import model.Map;
import model.Node;

public class CanvasController {
    @FXML
    private BorderPane canvas;
    @FXML
    private TextArea nodeText;
    private MainController mainController;
    private Node selectedNode;
    private Connection selectedConnection;
    private Node Start;
    private Node End;

    /**
     * Initializes the Canvas controller and sets the event handling
     */
    @FXML
    private void initialize() {
        nodeText.setVisible(false);
        canvas.setOnMouseClicked(e -> {
            if (mainController.getToolbarController().btnNodeSelected()) {
                Ellipse ellipse = new Ellipse();
                Label text = new Label("Text");
                Node node = new Node(ellipse, text,e.getSceneX(),e.getSceneY(),mainController.getToolbarController().getColor(),
                        mainController.getMenubarController().getScale());
                canvas.getChildren().add(node);
                mainController.getMap().addNode(node);
                node.connectionMode(false);
            }
            if (mainController.getToolbarController().btnConnectionSelected()) {
                int count = 0;
                for (Node node : mainController.getMap().getNodes()){
                    if (node.activeNode()){
                        if (count == 0) {
                            Start = node;
                        }else {
                            End = node;
                        }
                        count++;
                    }
                }
                if (count == 2 ) {
                    connectNodes();
                }
            }

            if (mainController.getToolbarController().btnSubNodeSelected()){
                  for (Node node : mainController.getMap().getNodes()){
                    if(node.activeNode()){
                        Start = node;
                        End = new Node (new Ellipse(), new Label("Text"), Start.getX().getValue(),
                                Start.getY().getValue(),mainController.getToolbarController().getColor(),
                                mainController.getMenubarController().getScale());
                        canvas.getChildren().add(End);
                        double scale = mainController.getMenubarController().getScale();
                        switch (Start.getActiveAnchor().getPos()) {
                            case TOP:
                                End.setTranslateY(-200*scale);
                                End.setPosition(0,-200*scale);
                                End.setActiveAnchor(End.getAnchorB());
                                break;
                            case LEFT:
                                End.setTranslateX(-300*scale);
                                End.setPosition(-300*scale,0);
                                End.setActiveAnchor(End.getAnchorR());
                                break;
                            case RIGHT:
                                End.setTranslateX(300*scale);
                                End.setPosition(300*scale,0);
                                End.setActiveAnchor(End.getAnchorL());
                                break;
                            case BOTTOM:
                                End.setTranslateY(200*scale);
                                End.setPosition(0,200*scale);
                                End.setActiveAnchor(End.getAnchorT());
                                break;
                        }
                        connectNodes();
                    }
                  }
                  if (End != null){
                      mainController.getMap().addNode(End);
                  }
            }



            if(e.getButton().equals(MouseButton.SECONDARY)) {
                if (selectedNode != null){
                    selectedNode.getEllipse().setStrokeWidth(2);
                    selectedNode.setNodeText(nodeText.getText());
                    selectedNode = null;
                    nodeText.setVisible(false);
                }
                if (selectedConnection != null){
                    selectedConnection.setStroke(Color.SILVER);
                    selectedConnection = null;
                }
            }
        });

    }
    /**
     * Creates a new Connection, between the currently 2 active Anchors
     */
    private void connectNodes(){
        Connection connection = new Connection(new Pair<>(Start,Start.getActiveAnchor().getPos()), new Pair<>(End,End.getActiveAnchor().getPos()),1);
        connection.startYProperty().bind(Start.getActiveAnchor().helpCenterYProperty());
        connection.startXProperty().bind(Start.getActiveAnchor().helpCenterXProperty());
        connection.endXProperty().bind(End.getActiveAnchor().helpCenterXProperty());
        connection.endYProperty().bind(End.getActiveAnchor().helpCenterYProperty());
        canvas.getChildren().add(connection);
        mainController.getMap().addConnection(connection);
        Start.deactivate();
        End.deactivate();
        Start.getActiveAnchor().deactivate();
        End.getActiveAnchor().deactivate();
    }


    /**
     * @param mainController injects the mainController
     */
    void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    /**
     * @param node handles the selection and unselection of a Node
     */
    public void nodeSelected(Node node){
        if(selectedNode != null) {
            selectedNode.getEllipse().setStrokeWidth(2);
            selectedNode.setNodeText(nodeText.getText());
        }
        selectedNode = node;
        selectedNode.getEllipse().setStrokeWidth(10);
        nodeText.setVisible(true);
        nodeText.setText(selectedNode.getNodeText());
    }
    /**
     * deletes the currently selected node
     */
    void deleteNode() {
        if(selectedNode != null) {
            canvas.getChildren().remove(selectedNode);
            selectedNode = null;
        }
    }
    /**
     * @param connection handles the selection and unselection of a Connection
     */
    public void connectionSelected (Connection connection){
        if(selectedConnection != null) {
            selectedConnection.setStroke(Color.SILVER);
        }
        selectedConnection = connection;
        selectedConnection.setStroke(Color.RED);
    }
    /**
     * deletes the currently selected connection
     */
    void deleteConnection() {
        if(selectedConnection != null) {
            canvas.getChildren().remove(selectedConnection);
        }
        selectedConnection = null;
    }
    /**
     * generates the map and places its objects when loading a file
     */
    void drawMap(){
        canvas.getChildren().clear();
        for(Node n : mainController.getMap().getNodes()){
            canvas.getChildren().add(n);
            n.connectionMode(false);
        }
        for(Connection c : mainController.getMap().getConnections()) {
            canvas.getChildren().add(c);
            Node start = c.getStart().getKey();
            Node end = c.getEnd().getKey();
            c.startYProperty().bind(start.getAnchor(c.getStart().getValue()).helpCenterYProperty());
            c.startXProperty().bind(start.getAnchor(c.getStart().getValue()).helpCenterXProperty());
            c.endXProperty().bind(end.getAnchor(c.getEnd().getValue()).helpCenterXProperty());
            c.endYProperty().bind(end.getAnchor(c.getEnd().getValue()).helpCenterYProperty());
        }
        canvas.getChildren().add(nodeText);
    }

    /**
     * draws a new empty canvas
     */
    void drawNew() {
        canvas.getChildren().clear();
        canvas.getChildren().add(nodeText);
        mainController.getMenubarController().setScale(1d);
        mainController.setMap(new Map());
    }

    /**
     * @return selectedNode
     */
    Node getSelectedNode() {
        return selectedNode;
    }

    /**
     * @return selectedConnection
     */
    Connection getSelectedConnection() {
        return selectedConnection;
    }
}
