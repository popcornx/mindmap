package controller;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Connection;
import model.Map;
import model.Node;
import util.SavableMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;

public class MainController {
    private Map map = new Map();

    @FXML private MenubarController menubarController;
    @FXML private ToolbarController toolbarController;
    @FXML private CanvasController canvasController;


    public void save(){
        try {
            FileChooser fc = new FileChooser();
            fc.setTitle("Save Mindmap");
            fc.setInitialFileName("map.xml");
            File file = fc.showSaveDialog(new Stage());

            JAXBContext context = JAXBContext.newInstance(SavableMap.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(map.saveMap(), new FileOutputStream(file));
//            output to console for quicker result evaluation, disable filechooser
//            marshaller.marshal(map.saveMap(), System.out);
        }
        catch (Exception exe) {
            exe.printStackTrace();
        }
    }

    public void load() {
        try {
            FileChooser fc = new FileChooser();
            fc.setTitle("Open Mindmap");
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("XML Files (*.xml)", "*.xml");
            fc.getExtensionFilters().add(filter);
            File file = fc.showOpenDialog(new Stage());

            JAXBContext context = JAXBContext.newInstance(SavableMap.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            SavableMap savableMap = (SavableMap) unmarshaller.unmarshal(file);

            map = map.loadMap(savableMap);
            canvasController.drawMap();

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(map.saveMap(),System.out);
        }
        catch (Exception exe) {
            exe.printStackTrace();
        }
    }

    @FXML private void initialize() {
        menubarController.setMainController(this);
        toolbarController.setMainController(this);
        canvasController.setMainController(this);
    }

    public Map getMap() {
        return map;
    }
    public Color getColor(){
        return toolbarController.getColor();
    }
    public boolean btnNodeSelected(){
        return toolbarController.btnNodeSelected();
    }
    public boolean btnConnectionSelected(){
        return toolbarController.btnConnectionSelected();
    }
    public boolean btnSubNodeSelected(){
        return toolbarController.btnSubNodeSelected();
    }

    public Node getSelectedNode(){
        return canvasController.getSelectedNode();
    }
    public void nodeSelected(Node node){
        canvasController.nodeSelected(node);
    }
    public void connectionSelected(Connection connection){
        canvasController.connectionSelected(connection);
    }
    public void deleteConnection(){
        map.getConnections().remove(canvasController.getSelectedConnection());
        canvasController.deleteConnection();
    }
    public void deleteNode(){
        map.getNodes().remove(canvasController.getSelectedNode());
        canvasController.deleteNode();
    }
}