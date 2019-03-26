package model;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<Node> nodes = new ArrayList<>();

    public Map() {
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public List<Node> getNodes () {
        return nodes;
    }





}
