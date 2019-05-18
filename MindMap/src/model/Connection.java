package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Pair;
import util.Position;
import view.Main;

public class Connection extends Line {
    private Pair<Node, Position> start, end;
    private int lineStyle;

    public Connection(Pair<Node, Position> start, Pair<Node, Position> end, int lineStyle) {
        super();
        this.setStrokeWidth(5);
        this.setStroke(Color.SILVER);
        this.setOnMouseClicked(e->{
            Main.mainController.getCanvasController().connectionSelected(this);
        });
        this.start = start;
        this.end = end;
        this.lineStyle = lineStyle;
        this.changeLineStyle(lineStyle);
        this.setStrokeLineCap(StrokeLineCap.ROUND);
    }

    public Pair<Node, Position> getStart() {
        return start;
    }

    public Pair<Node, Position> getEnd() {
        return end;
    }

    public int getLineStyle(){
        return lineStyle;
    }

    public void changeLineStyle(int s){
        if(s == 2) {
            this.getStrokeDashArray().addAll(25d, 20d);
        } else {
            this.getStrokeDashArray().clear();
        }
        this.lineStyle = s;
    }
}
