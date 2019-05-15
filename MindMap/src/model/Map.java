package model;

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
}
