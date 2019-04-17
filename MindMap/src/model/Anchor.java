package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Anchor extends Circle {
    public SimpleDoubleProperty helpCenterX = new SimpleDoubleProperty();
    public SimpleDoubleProperty helpCenterY = new SimpleDoubleProperty();
    private boolean active = false;

    public Anchor() {
    }
    public Anchor(double radius) {
        super(radius);
        super.setFill(Color.ORANGE);
        this.setOnMouseClicked(e-> {
            setActive();
        });
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
        if (active) {
            active = false;
            setFill(Color.ORANGE);
        }else {
            setFill(Color.SILVER);
            active = true;
        }
    }
}
