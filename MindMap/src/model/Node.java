package model;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
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
    private Text text;
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

    private final double radius = 20;

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
    public Node(Ellipse ellipse, Text text, double x, double y, Color color) {
        super(ellipse, text);
        this.setPrefSize(151,151);
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
        this.ellipse.setRadiusX(150);
        this.ellipse.setRadiusY(100);
        this.ellipse.setStroke(this.color);
        this.ellipse.setStrokeWidth(2);
        this.ellipse.setFill(Color.WHITE);

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
            this.setTranslateX(newTranslateX);
            this.setTranslateY(newTranslateY);
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
        this.anchorR = new Anchor(radius, Position.RIGHT);
        this.anchorL = new Anchor(radius, Position.LEFT);
        this.anchorT = new Anchor(radius, TOP);
        this.anchorB = new Anchor(radius, Position.BOTTOM);

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

    public Color getColor() {
        return color;
    }

    public Text getText() {
        return text;
    }

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
    public Anchor getAnchorL() {
        return anchorL;
    }

    public Anchor getAnchorR() {
        return anchorR;
    }

    public Anchor getAnchorT() {
        return anchorT;
    }

    public Anchor getAnchorB() {
        return anchorB;
    }

    public void setNodeText(String nodeText) {
        this.nodeText = nodeText;
    }

    public String getNodeText() {
        return nodeText;
    }
}

