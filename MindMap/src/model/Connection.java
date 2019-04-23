package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Pair;
import util.Position;
import view.Main;

public class Connection extends Line {
    private Pair<Node, Position> start, end;

    public Connection(Pair<Node, Position> start, Pair<Node, Position> end) {
        super();
        this.setStrokeWidth(5);
        this.setStroke(Color.SILVER);
        this.setOnMouseClicked(e->{
            Main.controller.conectionSelected(this);
        });
        this.start = start;
        this.end = end;
    }

    public Pair<Node, Position> getStart() {
        return start;
    }

    public Pair<Node, Position> getEnd() {
        return end;
    }
}
