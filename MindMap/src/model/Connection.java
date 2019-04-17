package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Connection extends Line {
    public Connection() {
        super();
        this.setStrokeWidth(5);
        this.setStroke(Color.SILVER);
    }

    public Connection(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);

    }


}
