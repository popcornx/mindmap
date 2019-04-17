package controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.Light;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import model.Anchor;
import model.Connection;
import model.Map;
import model.Node;

import java.awt.*;
import java.awt.geom.Point2D;

public class GuiController {
    @FXML
    private ToggleButton BtnNode;
    @FXML
    private ToggleButton BtnConnection;
    @FXML
    private Button BtnSubNode;
    @FXML
    private Button BtnOrder;
    @FXML
    private ColorPicker ColorSwitch;
    @FXML
    private AnchorPane pane;
    //to be implemented different
    private Map map = new Map();
    private Node selectedNode;
    private Node Start;
    private Node End;
    private Boolean connectionMode = false;


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
            pane.getChildren().remove(selectedNode);
            //delete from map
        }
    }
    public void initialize() {
        //DefaultValue
        ColorSwitch.setValue(Color.BLACK);


        pane.setOnMouseClicked(e -> {
            if (BtnNode.isSelected()) {
                Ellipse ellipse = new Ellipse();
                Text text = new Text("Text");

                Node node = new Node(ellipse, text,e.getSceneX(),e.getSceneY(),ColorSwitch.getValue());
                pane.getChildren().add(node);
                map.addNode(node);
                node.connectionMode(false);
            }
            if (BtnConnection.isSelected()) {
                int count = 0;
                for (Node node : map.getNodes()){
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
                    Connection connection = new Connection();
                    connection.startYProperty().bind(Start.aAnchor().helpCenterYProperty());
                    connection.startXProperty().bind(Start.aAnchor().helpCenterXProperty());
                    connection.endXProperty().bind(End.aAnchor().helpCenterXProperty());
                    connection.endYProperty().bind(End.aAnchor().helpCenterYProperty());
                    pane.getChildren().add(connection);
                }
            }
            if(e.getButton().equals(MouseButton.SECONDARY) && selectedNode != null) {
                selectedNode.getEllipse().setStrokeWidth(2);
                selectedNode = null;
            }
        });

        BtnConnection.setOnAction(e-> {
            if(connectionMode){
                for(Node node : map.getNodes()){
                    node.connectionMode(false);
                    connectionMode = false;
                }
            }else {
                for(Node node : map.getNodes()){
                    node.connectionMode(true);
                    connectionMode = true;
                }
            }

        });

        ColorSwitch.setOnAction(e-> {
          if (selectedNode!=null){
              selectedNode.changeColor(ColorSwitch.getValue());
          }
        });

        //To be Implemented!!
        BtnOrder.setOnAction(e-> {
            for (Node node : map.getNodes()){
                System.out.println(node.getIdNode());
            }
        });

        //To be Implemented!!
        BtnSubNode.setOnAction(e-> {
            for (Node node : map.getNodes()){
                System.out.println(node.getIdNode());
            }
        });
    }

}