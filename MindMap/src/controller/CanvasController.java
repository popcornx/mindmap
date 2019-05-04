package controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.util.Pair;
import model.Connection;
import model.Node;


public class CanvasController {
    @FXML
    private AnchorPane canvas;
    private MainController mainController;
    private Node selectedNode;
    private Connection selectedConnection;
    private Node Start;
    private Node End;
    @FXML
    public void initialize() {
        canvas.setOnMouseClicked(e -> {
            if (mainController.btnNodeSelected()) {
                Ellipse ellipse = new Ellipse();
                Text text = new Text("Text");

                Node node = new Node(ellipse, text,e.getSceneX(),e.getSceneY(),mainController.getColor());
                canvas.getChildren().add(node);
                mainController.getMap().addNode(node);
                node.connectionMode(false);
            }
            if (mainController.btnConnectionSelected()) {
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

            if (mainController.btnSubNodeSelected()){
                  for (Node node : mainController.getMap().getNodes()){
                    if(node.activeNode()){
                        Start = node;
                        End = new Node (new Ellipse(), new Text("Text"), Start.getX().getValue(),
                                Start.getY().getValue(),mainController.getColor());
                        canvas.getChildren().add(End);
                        switch (Start.getActiveAnchor().getPos()) {
                            case TOP:
                                End.setTranslateY(-275);
                                End.setPosition(0,-275);
                                End.setActiveAnchor(End.getAnchorB());
                                break;
                            case LEFT:
                                End.setTranslateX(-350);
                                End.setPosition(-350,0);
                                End.setActiveAnchor(End.getAnchorR());
                                break;
                            case RIGHT:
                                End.setTranslateX(350);
                                End.setPosition(350,0);
                                End.setActiveAnchor(End.getAnchorL());
                                break;
                            case BOTTOM:
                                End.setTranslateY(275);
                                End.setPosition(0,275);
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
                    selectedNode = null;
                }
                if (selectedConnection != null){
                    selectedConnection.setStroke(Color.SILVER);
                    selectedConnection = null;
                }
            }
        });

    }

    private void connectNodes(){
        Connection connection = new Connection(new Pair<>(Start,Start.getActiveAnchor().getPos()), new Pair<>(End,End.getActiveAnchor().getPos()));
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


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    /**
     * @param node Node
     */
    public void nodeSelected(Node node){
        if(selectedNode != null) {
            selectedNode.getEllipse().setStrokeWidth(2);
        }
        selectedNode = node;
        selectedNode.getEllipse().setStrokeWidth(10);
    }
    public void deleteNode() {
        if(selectedNode != null) {
            canvas.getChildren().remove(selectedNode);
            selectedNode = null;
        }
    }

    public void connectionSelected (Connection connection){
        if(selectedConnection != null) {
            selectedConnection.setStroke(Color.SILVER);
        }
        selectedConnection = connection;
        selectedConnection.setStroke(Color.RED);
    }


    public void deleteConnection() {
        if(selectedConnection != null) {
            canvas.getChildren().remove(selectedConnection);
        }
        selectedConnection = null;
    }

    public void drawMap(){
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
    }

    public Node getSelectedNode() {
        return selectedNode;
    }

    public Connection getSelectedConnection() {
        return selectedConnection;
    }
}
