package util;

import javafx.scene.control.Label;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import model.Connection;
import model.Map;
import model.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class XMLConverter {

    public static void saveMap(Map map, OutputStream stream) throws JAXBException {
        List<SavableNode> saveNodes = new ArrayList<>();
        for(Node n : map.getNodes()){
            java.awt.Color c = new java.awt.Color((float)n.getColor().getRed(),(float) n.getColor().getGreen(),(float) n.getColor().getBlue());
            saveNodes.add(new SavableNode(n.getIdNode(), n.getText().getText() , n.getX().get(), n.getY().get(), c));
        }
        List<SavableConnection> saveConnections = new ArrayList<>();
        for(Connection c : map.getConnections()){
            saveConnections.add(new SavableConnection(
                    new util.Pair(c.getStart().getKey().getIdNode(), c.getStart().getValue()),
                    new util.Pair(c.getEnd().getKey().getIdNode(), c.getEnd().getValue()),
                    c.getLineStyle()
            ));
        }

        SavableMap savableMap = new SavableMap(saveNodes, saveConnections);

        JAXBContext context = JAXBContext.newInstance(SavableMap.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(savableMap, stream);
    }

    public static Map loadMap(File file) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(SavableMap.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        SavableMap savableMap = (SavableMap) unmarshaller.unmarshal(file);

        Map map = new Map();
        int high = 0;
        for(SavableNode n : savableMap.getNodes()) {
            javafx.scene.paint.Color c = javafx.scene.paint.Color.rgb(n.getColor().getRed(), n.getColor().getGreen(), n.getColor().getBlue());
            Node node = new Node(new Ellipse(), new Label(n.getText()), n.getX(), n.getY(), c);
            node.setIdNode(n.getId());
            map.addNode(node);
            high = (node.getIdNode() > high) ? node.getIdNode() : high;
        }
        IdGenerator.id = new AtomicInteger(high);
        for(SavableConnection c : savableMap.getConnections()) {
            map.addConnection(new Connection(
                    new javafx.util.Pair<>(map.getNode(c.getStart().getI()), c.getStart().getP()),
                    new javafx.util.Pair<>(map.getNode(c.getEnd().getI()), c.getEnd().getP()),
                    c.getLineStyle()
            ));
        }
        return map;
    }
}
