package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

public class Controller {
    @FXML
    private ToggleButton BtnNode;
    @FXML
    private ToggleButton BtnConnection;
    @FXML
    private Button BtnSubNode;
    @FXML
    private Button BtnOrder;
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker ColorSwitch;

    private Ellipse node = new Ellipse();
    private Line connection = new Line();


    public void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(2);

        //Draws Cricles and Lines just for Tests
        canvas.setOnMouseClicked(e -> {
            if (BtnNode.isSelected()) {
                node.setCenterX(e.getX());
                node.setCenterY(e.getY());
                node.setRadiusX(150);
                node.setRadiusY(100);
                gc.strokeOval(node.getCenterX(), node.getCenterY(), node.getRadiusX(), node.getRadiusY());
            }

            if (BtnConnection.isSelected()) {
                connection.setStartX(e.getX());
                connection.setStartY(e.getY());
                connection.setEndX(connection.getStartX()+100);
                connection.setEndY(connection.getStartY());

                gc.strokeLine(connection.getStartX(), connection.getStartY(), connection.getEndX(), connection.getEndY());

            }
        });

        // Updates Current Stroke Color
        ColorSwitch.setOnAction(e->{
            gc.setStroke(ColorSwitch.getValue());
        });


    }

}