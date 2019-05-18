package model;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import util.IdGenerator;
import util.Position;
import view.Main;

import java.util.ArrayList;
import java.util.List;

import static util.Position.TOP;


/**
 * Class for generating Nodes, class is extended from a Pane
 * to put the textarea and the ellipse together.
 */
public class Node extends Pane {
    private int idNode;
    private Ellipse ellipse;
    private Label text;
    private Anchor anchorL;
    private Anchor anchorR;
    private Anchor anchorT;
    private Anchor anchorB;
    private SimpleDoubleProperty x;
    private SimpleDoubleProperty y;
    private Color color;
    private TextField textField = new TextField();
    private String nodeText = "";
    private boolean edit = false;
    private boolean active = false;
    private Anchor activeAnchor;
    private List<Anchor> anchors = new ArrayList<>();
    private double maxWidth = 1024;
    private double maxHeight = 950;
    public void setBorderWidth(double maxWidth) {
        this.maxWidth = maxWidth;
    }
    public void setBorderHeight(double maxHeight) {
        this.maxHeight = maxHeight;
    }
    private final double radiusAnchor = 14;
    private final double radiusShapeX = 100;
    private final double radiusShapeY = 50;
    private final double textSize = 15;
    //Helpers for drag and drop
    private double orgX, orgY;
    private double orgTranslateX, orgTranslateY;
    /**
     * @param ellipse ellipse
     * @param text text
     * @param x x-Coordinate
     * @param y y-Coordinate
     * @param color Color of the ellipse
     */
    public Node(Ellipse ellipse, Label text, double x, double y, Color color) {
        super(ellipse, text);
        this.setPrefSize(radiusShapeX+1,radiusShapeY+1);
        this.idNode = IdGenerator.id.incrementAndGet();
        this.ellipse = ellipse;
        this.text = text;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.color = color;
        styleNode();
    }
    /**
     * Styles the Ellipse after creation to Standard Values, also defines all MouseListeners of the
     * Object.
     */
    private void styleNode(){
        this.setLayoutX(this.x.getValue());
        this.setLayoutY(this.y.getValue());
        this.ellipse.setRadiusX(radiusShapeX);
        this.ellipse.setRadiusY(radiusShapeY);
        this.ellipse.setStroke(this.color);
        this.ellipse.setStrokeWidth(2);
        this.ellipse.setFill(Color.WHITE);
        textField.setLayoutX(radiusShapeX*-1);
        textField.setLayoutY(-10);
        text.setLayoutX(radiusShapeX*-1);
        text.setLayoutY(-10);
        text.setPrefWidth(radiusShapeX*2);
        text.setFont(Font.font(textSize));
        text.setAlignment(Pos.CENTER);

        this.setOnMousePressed(e ->{
            orgX = e.getSceneX();
            orgY = e.getSceneY();
            orgTranslateX = this.getTranslateX();
            orgTranslateY = this.getTranslateY();
        });

        this.setOnMouseDragged(e -> {
            double offsetX = e.getSceneX() - orgX;
            double offsetY = e.getSceneY() - orgY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            if(layoutYProperty().getValue()+newTranslateY>70 &&
                    layoutYProperty().getValue()+newTranslateY < maxHeight - this.ellipse.getRadiusY()){
                this.setTranslateY(newTranslateY);
            }
            if (layoutXProperty().getValue()+newTranslateX>0+this.ellipse.getRadiusX() &&
                    layoutXProperty().getValue()+newTranslateX < maxWidth - this.ellipse.getRadiusX()){
                this.setTranslateX(newTranslateX);
            }
            setPosition(newTranslateX, newTranslateY);
        });

        text.setOnMouseClicked(e-> {
            if (e.getButton().equals(MouseButton.PRIMARY)){
                if (e.getClickCount()==2){
                    this.getChildren().remove(text);
                    this.getChildren().add(textField);
                    edit = true;
                }
            }
        });
        this.setOnMouseClicked(e-> {
            if(e.getButton().equals(MouseButton.SECONDARY)){
                if(edit) {
                    text.setText(textField.getText());
                    this.getChildren().remove(textField);
                    this.getChildren().add(text);
                    edit = false;
                }
            }
            if (e.getButton().equals(MouseButton.PRIMARY)){
                Main.mainController.nodeSelected(this);
            }
        });

        anchor();
    }
    /**
     * Method used to give the Node the Anchors for Connection
     */
    private void anchor(){
        this.anchorR = new Anchor(radiusAnchor, Position.RIGHT);
        this.anchorL = new Anchor(radiusAnchor, Position.LEFT);
        this.anchorT = new Anchor(radiusAnchor, TOP);
        this.anchorB = new Anchor(radiusAnchor, Position.BOTTOM);

        anchorB.setLayoutY(ellipse.getRadiusY());
        anchorT.setLayoutY(ellipse.getRadiusY()*-1);
        anchorR.setLayoutX(ellipse.getRadiusX());
        anchorL.setLayoutX(ellipse.getRadiusX()*-1);

        this.getChildren().addAll(anchorB,anchorL,anchorR,anchorT);

        anchorR.helpCenterX.bind(this.getX().add(ellipse.getRadiusX()));
        anchorR.helpCenterY.bind(this.getY());

        anchorB.helpCenterX.bind(this.getX());
        anchorB.helpCenterY.bind(this.getY().add(ellipse.getRadiusY()));

        anchorL.helpCenterX.bind(this.getX().add(ellipse.getRadiusX()*-1));
        anchorL.helpCenterY.bind(this.getY());

        anchorT.helpCenterX.bind(this.getX());
        anchorT.helpCenterY.bind(this.getY().add(ellipse.getRadiusY()*-1));

        anchors.add(anchorB);
        anchors.add(anchorL);
        anchors.add(anchorR);
        anchors.add(anchorT);

        for (Anchor anchor : anchors) {
            anchor.setOnMouseClicked(e->{
                setActiveAnchor(anchor);
            });
        }
    }

    /**
     * @param color color
     */
    public void changeColor(Color color) {
        this.color = color;
        this.ellipse.setStroke(this.color);
    }

    /**
     * @return Observable X Value
     */
    public SimpleDoubleProperty getX() {
        return x;
    }
    /**
     * @return Observable Y Value
     */
    public SimpleDoubleProperty getY() {
        return y;
    }
    /**
     * @param newTranslateX Updates X Value
     * @param newTranslateY Updates Y Value
     */
    public void setPosition(double newTranslateX, double newTranslateY) {
        x.setValue(layoutXProperty().getValue()+newTranslateX);
        y.setValue(layoutYProperty().getValue()+newTranslateY);
    }

    /**
     * @return Ellipse
     */
    public Ellipse getEllipse() {
        return ellipse;
    }

    /**
     * @param mode sets if Anchors are visible
     */
    public void connectionMode(Boolean mode){
        anchorT.setVisible(mode);
        anchorR.setVisible(mode);
        anchorB.setVisible(mode);
        anchorL.setVisible(mode);
    }

    /**
     * Deactivates the Node, used for the Connect
     */
    public void deactivate(){
        active = false;
    }

    /**
     * @return returns if Node is active for Connection
     */
    public boolean activeNode(){
        return active;
    }

    public void setActiveAnchor(Anchor anchor) {
        if(activeAnchor == null){
            activeAnchor = anchor;
        }else {
            activeAnchor.deactivate();
            activeAnchor = anchor;
            activeAnchor.setActive();
            active = true;
        }
    }

    /**
     * @return returns the active Anchor of the Node
     */
    public Anchor getActiveAnchor(){
        return activeAnchor;
    }

    /**
     * @return IdNode
     */
    public int getIdNode() {return idNode; }

    /**
     * @return Color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return Text
     */
    public Label getText() {
        return text;
    }

    /**
     * @param p Position of the Anchor
     * @return Position
     */
    public Anchor getAnchor(Position p){
        switch (p) {
            case TOP:
                return anchorT;
            case RIGHT:
                return anchorR;
            case BOTTOM:
                return anchorB;
            case LEFT:
                return anchorL;
            default:
                return null;
        }

    }

    public void setScale(Double scale) {
        this.ellipse.setRadiusX(radiusShapeX*scale);
        this.ellipse.setRadiusY(radiusShapeY*scale);
        anchorB.setLayoutY(radiusShapeY*scale);
        anchorT.setLayoutY(radiusShapeY*-1*scale);
        anchorR.setLayoutX(radiusShapeX*scale);
        anchorL.setLayoutX(radiusShapeX*-1*scale);
        text.setFont(Font.font(textSize*scale));
    }


    /**
     * @return getLeftAnchor
     */
    public Anchor getAnchorL() {
        return anchorL;
    }

    /**
     * @return getRightAnchor
     */
    public Anchor getAnchorR() {
        return anchorR;
    }

    /**
     * @return getTopAnchor
     */
    public Anchor getAnchorT() {
        return anchorT;
    }

    /**
     * @return getBottomAnchor
     */
    public Anchor getAnchorB() {
        return anchorB;
    }

    /**
     * @param nodeText Sets NodeText
     */
    public void setNodeText(String nodeText) {
        this.nodeText = nodeText;
    }

    /**
     * @return get Node Text
     */
    public String getNodeText() {
        return nodeText;
    }

    /**
     * @param i Set Node id
     */
    public void setIdNode(int i) {
        this.idNode = i;
    }
}

