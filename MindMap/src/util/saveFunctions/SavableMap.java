package util.saveFunctions;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Map Object savable through JAXB
 */
@XmlRootElement(name="Map")
public class SavableMap {
    private List<SavableNode> nodes;
    private List<SavableConnection> connections;

    /**
     * Default constructor for JAXB
     */
    public SavableMap() {}

    /**
     * @param nodes List of SavableNode
     * @param connections List of SavableConnection
     */
    public SavableMap(List<SavableNode> nodes, List<SavableConnection> connections){
        this.nodes = nodes;
        this.connections = connections;
    }


    /**
     * @return List of SavableNode
     */
    @XmlElementWrapper(name="Nodes")
    @XmlElement(name="Node")
    public List<SavableNode> getNodes() {
        return nodes;
    }

    /**
     * @return List of SavableConnection
     */
    @XmlElementWrapper(name="Connections")
    @XmlElement(name="Connection")
    public List<SavableConnection> getConnections() {
        return connections;
    }

    /**
     * @param nodes List of SavableNode
     */
    public void setNodes(List<SavableNode> nodes) {
        this.nodes = nodes;
    }

    /**
     * @param connections List of SavableConnection
     */
    public void setConnections(List<SavableConnection> connections) {
        this.connections = connections;
    }
}
