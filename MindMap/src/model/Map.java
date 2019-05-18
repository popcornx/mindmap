package model;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<Node> nodes = new ArrayList<>();
    private List<Connection> connections = new ArrayList<>();

    public Map() {
    }
    /**
     * @param node adds a Node to the List
     */
    public void addNode(Node node) {
        nodes.add(node);
    }

    /**
     * @return List<Node> all nodes in the List
     */
    public List<Node> getNodes () {
        return nodes;
    }

    /**
     * @param id Id of the searched node
     * @return the searched node, if not found null
     */
    public Node getNode(int id) {
        for(Node n : nodes){
            if(n.getIdNode() == id){
                return n;
            }
        }
        return null;
    }
    /**
     * @param connection adds the connection to the List
     */
    public void addConnection(Connection connection) {
        connections.add(connection);
    }

    /**
     * @return List<Connection> all connections in the List
     */
    public List<Connection> getConnections () {
        return connections;
    }
}
