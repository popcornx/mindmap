package model;

import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    public Node getNode(int id) {
        for(Node n : nodes){
            if(n.getIdNode() == id){
                return n;
            }
        }
        return null;
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
    }

    public List<Connection> getConnections () {
        return connections;
    }

    public SavableMap saveMap(){
        List<SavableNode> saveNodes = new ArrayList<>();
        List<SavableConnection> saveConnections = new ArrayList<>();
        for(Node n : nodes){
            java.awt.Color c = new java.awt.Color((float)n.getColor().getRed(),(float) n.getColor().getGreen(),(float) n.getColor().getBlue());
            saveNodes.add(new SavableNode(n.getIdNode(), n.getText().getText() , n.getX().get(), n.getY().get(), c));
        }

        for(Connection c : connections){
            saveConnections.add(new SavableConnection(
                    new util.Pair(c.getStart().getKey().getIdNode(), c.getStart().getValue()),
                    new util.Pair(c.getEnd().getKey().getIdNode(), c.getEnd().getValue()),
                    c.getLineStyle()
            ));
        }

        return new SavableMap(saveNodes,saveConnections);
    }

    public Map loadMap(SavableMap load) {
        Map map = new Map();
        int high = 0;
        for(SavableNode n : load.getNodes()) {
            javafx.scene.paint.Color c = javafx.scene.paint.Color.rgb(n.getColor().getRed(), n.getColor().getGreen(), n.getColor().getBlue());
            Node node = new Node(new Ellipse(), new Text(n.getText()), n.getX(), n.getY(), c);
            node.setIdNode(n.getId());
            map.addNode(node);
            high = (node.getIdNode() > high) ? node.getIdNode() : high;
        }
        IdGenerator.id = new AtomicInteger(high);
        for(SavableConnection c : load.getConnections()) {
            map.addConnection(new Connection(
                    new javafx.util.Pair<>(map.getNode(c.getStart().getI()), c.getStart().getP()),
                    new javafx.util.Pair<>(map.getNode(c.getEnd().getI()), c.getEnd().getP()),
                    c.getLineStyle()
            ));
        }
        return map;
    }
}
