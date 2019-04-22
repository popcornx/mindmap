package util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="Map")
public class SavableMap {
    private List<SavableNode> nodes;
    private List<SavableConnection> connections;

    public SavableMap() {}

    public SavableMap(List<SavableNode> nodes, List<SavableConnection> connections){
        this.nodes = nodes;
        this.connections = connections;
    }


    @XmlElementWrapper(name="Nodes")
    @XmlElement(name="Node")
    public List<SavableNode> getNodes() {
        return nodes;
    }

    @XmlElementWrapper(name="Connections")
    @XmlElement(name="Connection")
    public List<SavableConnection> getConnections() {
        return connections;
    }

}
