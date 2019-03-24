package model;

import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<StackPane> nodes = new ArrayList<>();

    public Map() {
    }

    public void addNode(StackPane node) {
        nodes.add(node);
    }

    public List<StackPane> getNodes () {
        return nodes;
    }





}
