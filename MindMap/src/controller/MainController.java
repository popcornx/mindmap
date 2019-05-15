package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Connection;
import model.Map;
import model.Node;
import util.SavableMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainController {
    private Map map = new Map();
    private Stage primaryStage;
    private Parent root;
    @FXML private MenubarController menubarController;
    @FXML private ToolbarController toolbarController;
    @FXML private CanvasController canvasController;


    public void save(){

        FileChooser fc = new FileChooser();
        fc.setTitle("Save Mindmap");
        fc.setInitialFileName("map.xml");
        File file = fc.showSaveDialog(new Stage());

        if (file != null) {
            try (FileOutputStream stream = new FileOutputStream(file)) {

                JAXBContext context = JAXBContext.newInstance(SavableMap.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(map.saveMap(), stream);
//                output to console for quicker result evaluation, disable filechooser
//                marshaller.marshal(map.saveMap(), System.out);
            } catch (FileNotFoundException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Saving Error", ButtonType.OK);
                alert.setTitle("Error");
                alert.setResizable(true);
                alert.setContentText("An error occurred when trying to save the Mindmap.");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
            } catch (Exception exe) {
                exe.printStackTrace();
            }
        }
    }

    public void load() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Mindmap");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("XML Files (*.xml)", "*.xml");
        fc.getExtensionFilters().add(filter);
        File file = fc.showOpenDialog(new Stage());

        if(file != null) {
            try {

                JAXBContext context = JAXBContext.newInstance(SavableMap.class);

                Unmarshaller unmarshaller = context.createUnmarshaller();
                SavableMap savableMap = (SavableMap) unmarshaller.unmarshal(file);

                map = map.loadMap(savableMap);
                canvasController.drawMap();
            }
            catch (UnmarshalException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Loading Error", ButtonType.OK);
                alert.setTitle("Error");
                alert.setResizable(true);
                alert.setContentText("An error occurred when trying to load the Mindmap.\n" +
                        "This file is either corrupted or not a Mindmap.");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
            }
            catch (Exception exe) {
                exe.printStackTrace();
            }
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
    public Connection getSelectedConnection() {
        return canvasController.getSelectedConnection();
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

    public void setRoot(Parent root){
        this.root = root;
        root.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.DELETE)) {
                deleteNode();
                deleteConnection();
            }
        });
    }
    public void setStage(Stage primaryStage){
        this.primaryStage = primaryStage;
        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                for (Node node : map.getNodes()){
                    node.setBorderWidth(primaryStage.widthProperty().getValue());
                }
            }
        });
        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                for (Node node : map.getNodes()){
                    node.setBorderHeight(primaryStage.heightProperty().getValue()-100);
                }
            }
        });
    }
}