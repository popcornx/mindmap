package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import util.Position;

public class Anchor extends Circle {
    public SimpleDoubleProperty helpCenterX = new SimpleDoubleProperty();
    public SimpleDoubleProperty helpCenterY = new SimpleDoubleProperty();
    private boolean active = false;
    private Position pos;

    public Anchor() {
    }
    public Anchor(double radius, Position pos) {
        super(radius);
        super.setFill(Color.ORANGE);
        this.pos = pos;
    }

    public double getHelpCenterX() {
        return helpCenterX.get();
    }

    public SimpleDoubleProperty helpCenterXProperty() {
        return helpCenterX;
    }

    public double getHelpCenterY() {
        return helpCenterY.get();
    }

    public SimpleDoubleProperty helpCenterYProperty() {
        return helpCenterY;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(){
        active = true;
        this.setFill(Color.SILVER);
    }
    public void deactivate(){
        active = false;
        this.setFill(Color.ORANGE);
    }

    public Position getPos(){
        return pos;
    }

}

