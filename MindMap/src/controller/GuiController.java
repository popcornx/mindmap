package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import model.Map;
import model.Node;

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
    private Map map = new Map();


    public void initialize() {
        ColorSwitch.setValue(Color.BLACK);
        pane.setOnMouseClicked(e -> {
            if (BtnNode.isSelected()) {
                Ellipse ellipse = new Ellipse();
                Text text = new Text("Text");
                Node node = new Node(ellipse, text);
                node.setLayoutX(e.getX());
                node.setLayoutY(e.getY());
                node.getEllipse().setRadiusX(150);
                node.getEllipse().setRadiusY(100);
                node.getEllipse().setStroke(ColorSwitch.getValue());
                node.getEllipse().setStrokeWidth(2);
                node.getEllipse().setFill(Color.WHITE);
                pane.getChildren().add(node);
                map.addNode(node);
            }

            if (BtnConnection.isSelected()) {
                Line connection = new Line();
                connection.setStartX(e.getX());
                connection.setStartY(e.getY());
                connection.setEndX(connection.getStartX()+100);
                connection.setEndY(connection.getStartY());
                pane.getChildren().add(connection);
            }
        });
    }

}