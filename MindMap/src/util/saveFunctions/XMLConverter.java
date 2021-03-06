package util.saveFunctions;

import controller.MenubarController;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.shape.Ellipse;
import model.Connection;
import model.Map;
import model.Node;
import util.saveFunctions.Pair;
import util.saveFunctions.SavableConnection;
import util.saveFunctions.SavableMap;
import util.saveFunctions.SavableNode;
import util.staticFunctions.IdGenerator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class to convert Map Object to XML text.
 */
public abstract class XMLConverter {

    public static double scale;

    /**
     * Method first converts Map to savable Object then writes XML file.
     * @param map Map
     * @param stream OutputStream
     * @throws JAXBException Problems during the conversion to XML text
     */
    public static void saveMap(Map map, OutputStream stream, double scale) throws JAXBException {
        List<SavableNode> saveNodes = new ArrayList<>();
        for(Node n : map.getNodes()){
            java.awt.Color c = new java.awt.Color((float)n.getColor().getRed(),(float) n.getColor().getGreen(),(float) n.getColor().getBlue());
            saveNodes.add(new SavableNode(n.getIdNode(), n.getText().getText() , n.getX().get(), n.getY().get(), c, n.getNodeText()));
        }
        List<SavableConnection> saveConnections = new ArrayList<>();
        for(Connection c : map.getConnections()){
            saveConnections.add(new SavableConnection(
                    new Pair(c.getStart().getKey().getIdNode(), c.getStart().getValue()),
                    new Pair(c.getEnd().getKey().getIdNode(), c.getEnd().getValue()),
                    c.getLineStyle()
            ));
        }

        SavableMap savableMap = new SavableMap(saveNodes, saveConnections, scale);

        JAXBContext context = JAXBContext.newInstance(SavableMap.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(savableMap, stream);
    }

    /**
     * Method first reads the file then converts savable Objects back to UI objects.
     * @param file File
     * @return Map
     * @throws JAXBException Problems when reading file, format or syntax Errors
     */
    public static Map loadMap(File file) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(SavableMap.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        SavableMap savableMap = (SavableMap) unmarshaller.unmarshal(file);

        Map map = new Map();
        int high = 0;
        for(SavableNode n : savableMap.getNodes()) {
            javafx.scene.paint.Color c = javafx.scene.paint.Color.rgb(n.getColor().getRed(), n.getColor().getGreen(), n.getColor().getBlue());
            Node node = new Node(new Ellipse(), new Label(n.getText()), n.getX(), n.getY(), c, 1.0);
            node.setIdNode(n.getId());
            node.setNodeText(n.getDesc());
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
        scale = savableMap.getScale();
        return map;
    }
}
