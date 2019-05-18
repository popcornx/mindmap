package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import util.Position;

public class Anchor extends Circle {
    public SimpleDoubleProperty helpCenterX = new SimpleDoubleProperty();
    public SimpleDoubleProperty helpCenterY = new SimpleDoubleProperty();
    private Position pos;

    /**
     * @param radius Radius for the Anchor Size
     * @param pos Position of the Anchor(Top,Left,Bottom,Right)
     */
    public Anchor(double radius, Position pos) {
        super(radius);
        super.setFill(Color.ORANGE);
        this.pos = pos;
    }

    /**
     * @return SimpleDoubleProperty helpCenterXProperty to observe the position
     */
    public SimpleDoubleProperty helpCenterXProperty() {
        return helpCenterX;
    }

    /**
     * @return SimpleDoubleProperty helpCenterYProperty to observe the position
     */
    public SimpleDoubleProperty helpCenterYProperty() {
        return helpCenterY;
    }

    /**
     * Shows visualy which Anchor is active
     */
    public void setActive(){
        this.setFill(Color.SILVER);
    }
    /**
     * Deactivates the Anchor visualy
     */
    public void deactivate(){
        this.setFill(Color.ORANGE);
    }

    /**
     * @return Position of the Anchor(Top,Left,Bottom,Right)
     */
    public Position getPos(){
        return pos;
    }
}

