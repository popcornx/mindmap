package model;

import util.Pair;
import util.SavableConnection;
import util.SavableMap;
import util.SavableNode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<Node> nodes = new ArrayList<>();
    private List<Connection> connections = new ArrayList<>();

    public Map() {
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public List<Node> getNodes () {
        return nodes;
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
    }

    public SavableMap saveMap(){
        List<SavableNode> saveNodes = new ArrayList<>();
        List<SavableConnection> saveConnections = new ArrayList<>();
        for(Node n : nodes){
            Color c = new Color((float)n.getColor().getRed(),(float) n.getColor().getGreen(),(float) n.getColor().getBlue());
            saveNodes.add(new SavableNode(n.getIdNode(), n.getText().getText() , n.getX().get(), n.getY().get(), c));
        }

        for(Connection c : connections){
            saveConnections.add(new SavableConnection(
                    new Pair(c.getStart().getKey().getIdNode(), c.getStart().getValue()),
                    new Pair(c.getEnd().getKey().getIdNode(), c.getEnd().getValue())
            ));
        }

        return new SavableMap(saveNodes,saveConnections);
    }
}
